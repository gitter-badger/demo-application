package com.github.kazuki43zoo.domain.service.password;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.date.DateFactory;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.github.kazuki43zoo.core.message.Message;
import com.github.kazuki43zoo.domain.model.account.Account;
import com.github.kazuki43zoo.domain.model.account.AccountPasswordHistory;
import com.github.kazuki43zoo.domain.repository.account.AccountRepository;

@Transactional
@Service
@lombok.RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class PasswordServiceImpl implements PasswordService {

    private final @lombok.NonNull PasswordEncoder passwordEncoder;

    private final @lombok.NonNull DateFactory dateFactory;

    private final @lombok.NonNull AccountRepository accountRepository;

    private final @lombok.NonNull PasswordSharedService passwordSharedService;

    @Override
    public Account change(String accountId, String rawOldPassword, String rawNewPassword) {
        Account currentAccount = accountRepository.findOneByAccountId(accountId);

        authenticate(currentAccount, rawOldPassword);

        passwordSharedService.validatePassword(rawNewPassword, currentAccount);

        DateTime currentDateTime = dateFactory.newDateTime();

        String encodedNewPassword = passwordEncoder.encode(rawNewPassword);
        currentAccount.setPassword(encodedNewPassword);
        currentAccount.setPasswordModifiedAt(currentDateTime);
        accountRepository.update(currentAccount);
        passwordSharedService.resetPasswordLock(currentAccount);

        accountRepository.createPasswordHistory(new AccountPasswordHistory(currentAccount
                .getAccountUuid(), encodedNewPassword, currentDateTime));

        return currentAccount;

    }

    private void authenticate(Account currentAccount, String rawPassword) {
        if (currentAccount == null) {
            throw new ResourceNotFoundException(Message.SECURITY_ACCOUNT_NOT_FOUND.resultMessages());
        }
        if (!passwordEncoder.matches(rawPassword, currentAccount.getPassword())) {
            throw new ResourceNotFoundException(Message.SECURITY_ACCOUNT_NOT_FOUND.resultMessages());
        }
    }

}
