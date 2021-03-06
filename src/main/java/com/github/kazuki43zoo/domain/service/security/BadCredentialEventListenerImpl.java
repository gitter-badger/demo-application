package com.github.kazuki43zoo.domain.service.security;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.github.kazuki43zoo.domain.model.account.AccountAuthenticationHistory;
import com.github.kazuki43zoo.domain.model.account.AuthenticationType;
import com.github.kazuki43zoo.domain.service.password.PasswordSharedService;

@Transactional
@Component
@lombok.RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class BadCredentialEventListenerImpl implements
        ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final @lombok.NonNull AuthenticationService authenticationService;

    private final @lombok.NonNull PasswordSharedService passwordSharedService;

    private final @lombok.NonNull Mapper beanMapper;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String failedAccountId = event.getAuthentication().getName();

        passwordSharedService.countUpPasswordFailureCount(failedAccountId);

        AccountAuthenticationHistory authenticationHistory = beanMapper.map(event
                .getAuthentication().getDetails(), AccountAuthenticationHistory.class);
        authenticationService.createAuthenticationFailureHistory(failedAccountId,
                authenticationHistory, AuthenticationType.LOGIN, event.getException().getMessage());
    }

}
