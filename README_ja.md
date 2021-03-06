# 1. Demo Application

このアプリケーションは、[TERASOLUNA Global Framework](http://terasoluna.org)用のサンプルアプリケーションです。<br>
**現在、このアプリケーションは開発中です。**

このアプリケーションは、[TERASOLUNA Global Framework Development Guideline](http://terasolunaorg.github.io/guideline/)をベースに作成していますが、私個人の考えでカスタマイズしている部分があります。（１００％ガイドラインに準拠しているわけではありません）

# 2. Application Structure

アプリケーション構造は以下の通りです。<br><br>
![alt text](./images/application-structure.png "Application Structure")

| レイヤ | コンポーネント/ライブラリ | 主な役割 |
| :-----: | --------- | --------------------- |
| Client Layer         | [jQuery 2.1.1](http://jquery.com/) | 便利なJavaScript操作を提供します。 |
|                      | [AngularJS 1.2.26](https://angularjs.org/) | JavaScriptのMVW Frameworkのメカニズムを提供します。 |
|                      | [Bootstrap 3.2.0](http://getbootstrap.com/) | 便利でスタイリッシュなCSS定義とクライアントコンポーネント（Alert, メッセージダイアログなど）を提供します。 |
| Server Side Platform | Java SE 7 Java Virtual Machine | Javaアプリケーションの実行環境を提供します。 | 
|                      | Java EE 6 Servlet Container | サーブレット3.0仕様のサーブレットエンジンを提供します。 | 
|                      | [Spring Framework 4.1.1.RELEASE](http://projects.spring.io/spring-framework/) | CDI(Context and Dependency Inject)のメカニズムを提供します。本アプリケーションでは、トランザクション管理やAOPなどのメカニズムも利用しています。|
| Application Layer    | [Spring Security 3.2.5.RELEASE](http://projects.spring.io/spring-security/) | Webアプリケーションのセキュリティ対策のメカニズムを提供します。 |
|                      | [Spring MVC 4.1.1.RELEASE](http://projects.spring.io/spring-framework/) | WebアプリケーションのMVC Frameworkのメカニズムを提供します。 |
|                      | [Bean Validation 1.1(JSR-349)](http://beanvalidation.org/1.1/) | リクエストデータ(FormなどのJavaBean)に対する入力チェックのメカニズムを提供します。本アプリケーションでは、実装プロバイダとして [Hivernate Validator 5.1.2.Final](http://hibernate.org/validator/) を使用しています。|
|                      | Controllers | リクエストをハンドリングし、サービスクラスへビジネスロジックの手続き処理を委譲します。 |
|                      | DTOs(Forms) | アプリケーション層（Web層）で扱うデータ（Formデータ）を保持します。 |
|                      | JSPs | Formデータやドメインオブジェクトなどにアクセスし、プレゼンテーション用のコンポーネント(HTMLなど)を生成します。 |
| Domain Layer         | Domain Objects | ドメインデータの保持と、コアなビジネスロジックを実装・提供します。 |
|                      | Repositories | ドメインオブジェクトに対するCRUD操作(インタフェース)を定義・提供します。 |
|                      | Services | ビジネスロジックの手続の実装・提供及びトランザクション境界を提供します。|
| Infrastructure Layer | [Mybatis 3.3.0-SNAPSHOT](http://mybatis.github.io/mybatis-3/) | データベースへアクセスするためのメカニズムを提供します。本アプリケーションでは、RespoistoryインタフェースとしてMyBatisのMapperインタフェースを使用します。また、SQLはMapper XMLファイルに実装します。 |
| Libraries               | [TERASOLUNA Global Framework Common Library 1.1.0-SNAPSHOT](https://github.com/terasolunaorg/terasoluna-gfw) | エンタープライズアプリケーションを開発する際に役立つ便利な機能（トランザクショントークンチェック、コードリスト、ページネーション、メッセージ表示、例外ハンドリングなど）を提供します。また、開発に役立つ便利なOSSライブラリ（[Dozer](http://dozer.sourceforge.net/), [Joda-Time](http://www.joda.org/joda-time/), [Apache-Commons families](http://commons.apache.org/)など）への依存性を提供します。 |

## 2.1. Dependency libraries for application-specific

以下のライブラリは、TERASOLUNA Global Framewrokと関係がないアプリケーション独自の依存ライブラリです。

| ライブラリ(Group Id:Artifact Id) | バージョン | 説明 |
| ----- | :-----:| --------------------- |
| [org.apache.commons:commons-dbcp2](http://commons.apache.org/proper/commons-dbcp/) | 2.0.1 | データソース（コネクションプーリング）を利用するために依存しています。commons-dbcpの後継ライブラリです。 |
| [com.h2database:h2](http://www.h2database.com/) | 1.4.181 | H2データベースにアクセスするために依存しています。 |
| [org.projectlombok:lombok](http://projectlombok.org/) | 1.14.8 | JavaBeanのメソッド（getter/setterメソッドなど）を自動生成するために依存しています。このライブラリを使うことで、スムーズかつ効率的に開発することができます。<br>**Eclispe, STS(Spring Tool Suite), IDEA, NetBeansなどのIDEを使う場合は、IDEに lombok.jarをインストールしてください。インストール方法の詳細は、[ここ](http://jnb.ociweb.com/jnb/jnbJan2010.html#installation)を参照してください。** (IDEAの場合は、プラグインをインストールしてください) |
| [com.fasterxml.jackson.core:jackson-databind](http://wiki.fasterxml.com/JacksonHome) | 2.4.3 | リクエストデータとレスポンスデータをJSONに変換するために依存しています。jackson1の後継ライブラリです。 |
| [com.fasterxml.jackson.datatype:jackson-datatype-joda](http://wiki.fasterxml.com/JacksonModuleJoda) | 2.4.2 | Jackson上でJoda-Timeを使うために依存しています。 |
| [javax.transaction:javax.transaction-api](http://jta-spec.java.net) | 1.2 | Java標準の `@java.transaction.Transactinal` アノテーションを使用するために依存しています。 |
| [org.springframework.hateoas:spring-hateoas](https://github.com/spring-projects/spring-hateoas) | 0.16.0.RELEASE | REST APIとしてHATEOASをサポートするために依存しています。 |
| [org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1](https://code.google.com/p/log4jdbc-log4j2) | 1.16 | デバッグ用にJDBC操作のログ出力をサポートするために依存しています。 |

## 2.2. Version up of dependency libraries for application-specific  

以下のライブラリは、TERASOLUNA Global Frameworkが依存しているバージョンからバージョンをアップしています。バージョンアップしている理由は、最新バージョンを試すためです。

| ライブラリ | 本アプリケーションでの使用バージョン | TERASOLUNA<br>Global Framework |
| ----- | :-----: | :-----: |
| org.springframework | 4.1.1.RELEASE | 3.2.10.RELEASE |
| org.springframework.data | 1.9.0.RELEASE  | 1.6.4.RELEASE |
| org.aspectj | 1.8.2 | 1.7.4 |
| org.slf4j | 1.7.7 | 1.7.5 |
| ch.qos.logback | 1.1.2 | 1.0.13 |
| org.hibernate.hibernate-validator | 5.1.2.Final | 4.3.1.Final |
| jboss-logging | 3.1.4.GA | 3.1.0.GA |
| org.mybatis | 3.3.0-SNAPSHOT | 3.2.7 |
| joda-time | 2.4 | 2.2 |
| dozer | 5.5.1 | 5.4.0 |
| com.google.guava | 18.0 | 13.0.1 |
| commons-beanutils | 1.9.2 | 1.8.3 |
| commons-lang | 3.3.2 | 3.1 |
| org.apache.tiles | 3.0.4 | 2.2.2 |
| org.codehaus.jackson | exclusion | 1.9.7 |
| commons-dbcp | exclusion | 1.2.2.patch_DBCP264_DBCP372 |
| org.lazyluke.log4jdbc-remix | exclusion | 0.2.7 |

## 2.3. Java package Structure

![alt text](./images/java-package-structure.png "Java package Structure")

Coming soon...

## 2.4. Resources package Structure

![alt text](./images/resources-package-structure.png "Resources package Structure")

Coming soon...

## 2.5. Web application(war) Structure

![alt text](./images/web-application-structure.png "Web application(war) Structure")

Coming soon...

# 3. Application Functionalities

Functionalities of this application is following.<br>

| No | Functionality name | Description |
| :-----: | ----- | ----- |
| 1 | Welcome page | Coming soon... |
| 2 | Authentication | Coming soon... |
| 3 | Authorization | Coming soon... |
| 4 | Security Countermeasure | Coming soon... |
| 5 | Session Management | Coming soon... |
| 6 | Accounts Management | Coming soon... |
| 7 | My Profile Management | Coming soon... |
| 8 | Password Management | Coming soon... |
| 9 | Time Card | Coming soon... |
| 10 | Others... | Coming soon... |


# 4. Authentication
This section describe about authentication in this application.<br>
In this application, authentication(login and logout) processing are implements using Spring Security and Spring MVC.<br>

> ![alt text](./images/info.png "Note")<br>
> **Responsibility of each other are following.** 
>
> * **Spring Security has responsible for the authentication processing.**
> * **Spring MVC has responsible for input values validation and the screen control.**

## 4.1. View the login form

Display processing flow of login form are following.

![alt text](./images/flow-view-login-form.png "Flow of view the login form page")

### Access the protected page when not authenticate

If access the protected page when not authenticate, spring-security redirect to the uri(page) that is defined in `login-page` attribute of `sec:form-login` element.

* `src/main/resources/META-INF/spring/spring-security.xml`

  ```xml
  <sec:http auto-config="true" use-expressions="true">
      <!-- omit -->
      <sec:form-login login-processing-url="/auth/authenticate" login-page="/auth/login?encourage"
          username-parameter="accountId" password-parameter="password"
          authentication-details-source-ref="customAuthenticationDetailsSource"
          authentication-failure-handler-ref="authenticationFailureHandler" />
      <!-- omit -->
  </sec:http>
  ```

### Encourage the login

Request of `GET /auth/login?encourage` and `GET /auth/login` are handled `LoginController`.
In the `LoginController`, set the message and view the login form page.

* `src/main/java/com/github/kazuki43zoo/app/auth/LoginController.java`

  ```java
  @RequestMapping("auth/login")
  @Controller
  public class LoginController {
      // omit

      @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
      @RequestMapping(method = RequestMethod.GET)
      public String showLoginForm() {
          return "auth/loginForm";
      }

      @RequestMapping(method = RequestMethod.GET, params = "encourage")
      public String encourageLogin(RedirectAttributes redirectAttributes) {
          redirectAttributes.addFlashAttribute(Message.AUTH_ENCOURAGE_LOGIN.buildResultMessages());
          return "redirect:/auth/login";
      }

      // omit
  }
  ```
  > ![alt text](./images/info.png "Note")<br>
  > **When view the login form page, global transaction for transaction token check is begun.**


Generate screen data(response data) by the `auth/loginForm` view(JSP). 

* `src/main/webapp/WEB-INF/views/auth/loginForm.jsp`

  ```jsp
  <c:if test="${!param.including}">
      <t:messagesPanel messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" messagesType="danger" />
      <t:messagesPanel />
      <spring:hasBindErrors name="loginForm">
          <spring:nestedPath path="loginForm">
              <div class="alert alert-danger">
                  <form:errors path="*" />
              </div>
          </spring:nestedPath>
      </spring:hasBindErrors>
  </c:if>
  <form:form action="${contextPath}/auth/login" class="navbar-form" method="post" modelAttribute="loginForm">
      <div class="form-group">
          <form:input type="text" path="accountId" class="form-control" placeholder="Account ID" />
      </div>
      <div class="form-group">
          <form:password path="password" class="form-control" placeholder="Password" />
      </div>
      <button class="btn">
          <span class="glyphicon glyphicon-log-in"></span>
      </button>
  </form:form>
  ```
  > ![alt text](./images/info.png "Note")<br>
  > **`loginForm.jsp` is included from the `src/main/webapp/WEB-INF/views/common/layout/topNavbar.jsp`.**
  > **Hence, in this JSP, use the `including` parameter to judge the included / not included.**
  > **When are included from `topNavbar.jsp`, error message does not displayed in this page.** 


### Actual screen(response) are following.

![alt text](./images/screenshot-view-login-form.png "login form")


## 4.2. Login(Authenticate)

Login processing flow are following.

![alt text](./images/flow-authentication.png "Flow of login")

### Submit login request

In this application, parameter name of username and password has change the default settings of Spring Security.

* `src/main/webapp/WEB-INF/views/auth/loginForm.jsp`

  ```jsp
  <form:form action="${contextPath}/auth/login" class="navbar-form" method="post" modelAttribute="loginForm">
      <div class="form-group">
          <form:input type="text" path="accountId" class="form-control" placeholder="Account ID" />
      </div>
      <div class="form-group">
          <form:password path="password" class="form-control" placeholder="Password" />
      </div>
      <button class="btn">
          <span class="glyphicon glyphicon-log-in"></span>
      </button>
  </form:form>
  ```

### Receive the login request by the Spring MVC

In this application, `LoginController` receive the login request, and execute validation of login form data. If not exists violation, `LoginContoller` forward to the authentication processing of Spring Security.

> ![alt text](./images/tip.png "Tip")<br>
> **If not exists requirement as input value validation or re-display, Spring MVC is not required.**

* `src/main/java/com/github/kazuki43zoo/app/auth/LoginController.java`

  ```java
  @RequestMapping("auth/login")
  @Controller
  public class LoginController {
      // omit

      @TransactionTokenCheck
      @RequestMapping(method = RequestMethod.POST)
      public String login(@Validated LoginForm form, BindingResult bindingResult) {

          if (bindingResult.hasErrors()) {
              return showLoginForm();
          }

          return "forward:/auth/authenticate";
      }

      // omit
  }
  ```
  > ![alt text](./images/info.png "Note")<br>
  > **In the login processing , execute global transaction token check. Global transaction are begin when view the welcom page or the login page.**

* `src/main/java/com/github/kazuki43zoo/app/auth/LoginForm.java`

  ```java
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public class LoginForm implements Serializable {
      private static final long serialVersionUID = 1L;
      @NotNull
      private String accountId;
      @NotNull
      private String password;
  }
  ```

![alt text](./images/point.png "Point")<br>
For forward to the authentication processing of Spring Security, as the settings of `SpringSecurityFilterChain`, need add `<dispatcher>FORWARD</dispatcher>` .

* `src/main/webapp/WEB-INF/web.xml`

  ```xml
  <filter>
      <filter-name>SpringSecurityFilterChain</filter-name>
      <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
      <init-param>
          <param-name>targetBeanName</param-name>
          <param-value>springSecurityFilterChain</param-value>
      </init-param>
  </filter>
  <filter-mapping>
      <filter-name>SpringSecurityFilterChain</filter-name>
      <servlet-name>AppServlet</servlet-name>
      <servlet-name>ApiServlet</servlet-name>
      <servlet-name>H2ConsoleServlet</servlet-name>
      <dispatcher>REQUEST</dispatcher>
      <dispatcher>FORWARD</dispatcher>
  </filter-mapping>
  ```
  > ![alt text](./images/tip.png "Tip")<br>
  > **As the way to map to this servlet filter, i will recommend to use the `<servlet-name>` element.**
  > **If specify `<url-pattern>/*</url-pattern>`, this servlet filter would has been applied at the jsp forwarding.**

### Receive the login(authentication) request by the Spring Security

Spring Security execute the authentication processing when was accessed to the url that is defined in `login-processing-url` attribute of `sec:form-login` element.
In this application, `login-processing-url` & `username-parameter` & `password-parameter` attribute of `sec:form-login` element has change the default settings of Spring Security.<br>

> ![alt text](./images/important.png "Important")<br>
> **Reason of changing default settings is to hide the fact that are using the Spring Security as security countermeasure. If occur the security vulnerability in the Spring Security, be able to reduce the risk of attack to this application.**

* `src/main/resources/META-INF/spring/spring-security.xml`

  ```xml
  <sec:http auto-config="true" use-expressions="true">
      <!-- omit -->
      <sec:form-login login-processing-url="/auth/authenticate" login-page="/auth/login?encourage"
          username-parameter="accountId" password-parameter="password"
          authentication-details-source-ref="customAuthenticationDetailsSource"
          authentication-failure-handler-ref="authenticationFailureHandler" />
      <!-- omit -->
  </sec:http>
  ```

### Execute authentication processing

In this application, authentication realize using the `DaoAuthenticationProvider` of Spring Security.<br>
`DaoAuthenticationProvider` has authenticate by using the user information that are loaded from the data store.<br>
In `DaoAuthenticationProvider`, be able to check for the status of loaded user. Actually checking contents are following.

> ![alt text](./images/info.png "Note")<br>
> **User information are loaded as instance of the `CustomUserDetails` (extended class in this application).
In this application, load the user information via the `CustomUserDetailService` (extended class in this application).**


| No | Checking content | Specification in this application |
| :-----: | ----- | ----- |
| 1 | Specified user exists ? | Fetches the record that matches specified account id from the account table. If not exists matched record, occur the authentication error. |
| 2 | Specified user is not lock ? | Fetches the password failure count of fetched account. If it is over the max count of password failure count, occur the authentication error. |
| 3 | Specified user is enable ? | Fetches the enable flag of fetched account. If it is false(disabled), occur the authentication error. |
| 4 | Specified user is not expired ? | In this application, not support this checking. This means that check result is OK at always. |
| 5 | Specified user's password is not expired ? | Fetches the last modified date time of password. If it not modified during the password valid days period, encourage the password changing. |
| 6 | Matches the specified password ? | Fetches the password. If it not matches the specified password, occur the authentication error as bad credential.  |

* `src/main/resources/META-INF/spring/spring-security.xml`

  ```xml
  <sec:authentication-manager>
      <sec:authentication-provider user-service-ref="customUserDetailsService">
          <sec:password-encoder ref="passwordEncoder" />
      </sec:authentication-provider>
  </sec:authentication-manager>
  ```

  > ![alt text](./images/info.png "Note")<br>
  > **`customUserDetailsService` are scan by component-scan.**
  > **`passwordEncoder` are defined in `src/main/resources/META-INF/spring/applicationContext.xml`. In this application, use the `BCryptPasswordEncoder`.**

### Execute authentication success processing

description coming soon...

## 4.3. Handle login error

![alt text](./images/flow-handle-authentication-error.png "Flow of handle the authentication error")

description coming soon...

## 4.4. Handle validation error

![alt text](./images/flow-handle-authentication-validation-error.png "Flow of handle the validation error on login")

description coming soon...

## 4.5. Logout

![alt text](./images/flow-logout.png "Flow of logout")

description coming soon...

## 4.6. Logout by session timeout
Coming soon...

# 5. Authorization
This section describe about authorization in this application.<br>
In this application, authorization(access control of protected page) processing are implements using Spring Security.<br>

Coming soon...

# 6. Other Security Countermeasure
This section describe about other security countermeasure in this application.<br>

## 6.1. Session Fixation Attacks Protection
Coming soon...

* `src/main/resources/META-INF/spring/spring-security.xml`

  ```xml
  <sec:http auto-config="true" use-expressions="true">
      <!-- omit -->
      <sec:session-management invalid-session-url="/error/invalidSession"
          session-fixation-protection="migrateSession"  />
      <!-- omit -->
  </sec:http>
  ```

## 6.2. CSRF Attacks Protection
Coming soon...

* `src/main/resources/META-INF/spring/spring-security.xml`

  ```xml
  <sec:http auto-config="true" use-expressions="true">
      <!-- omit -->
      <sec:csrf request-matcher-ref="csrfRequestMatcher" />
      <!-- omit -->
  </sec:http>
  ```
  ```xml
  <bean id="csrfRequestMatcher" class="org.springframework.security.web.util.matcher.AndRequestMatcher">
      <constructor-arg>
          <list>
              <ref bean="defaultCsrfRequiresMethodMatcher" />
              <bean class="org.springframework.security.web.util.matcher.NegatedRequestMatcher">
                  <constructor-arg ref="csrfExclusionPathMatcher" />
              </bean>
          </list>
      </constructor-arg>
  </bean>
  <bean id="csrfExclusionPathMatcher" class="org.springframework.security.web.util.matcher.OrRequestMatcher">
      <constructor-arg>
          <list>
              <bean id="h2ConsolePathMatcher" class="org.springframework.security.web.util.matcher.AntPathRequestMatcher">
                  <constructor-arg index="0" value="/vendor/h2/**" />
              </bean>
          </list>
      </constructor-arg>
  </bean>
  ```

## 6.3. XSS attacks Protection
Coming soon...

* `src/main/resources/META-INF/spring/spring-security.xml`

  ```xml
  <sec:http auto-config="true" use-expressions="true">
      <!-- omit -->
      <sec:headers>
          <!-- omit -->
          <sec:content-type-options />
          <sec:xss-protection/>
      </sec:headers>
      <!-- omit -->
  </sec:http>
  ```

## 6.4. Clickjacking Attacks Protection
Coming soon...

* `src/main/resources/META-INF/spring/spring-security.xml`

  ```xml
  <sec:http auto-config="true" use-expressions="true">
      <!-- omit -->
      <sec:headers>
          <!-- omit -->
          <sec:frame-options policy="SAMEORIGIN" />
          <!-- omit -->
      </sec:headers>
      <!-- omit -->
  </sec:http>
  ```

## 6.5. Directory Traversal Attacks Protection
Coming soon...


## 6.6. Protected resource not cache

* `src/main/resources/META-INF/spring/spring-security.xml`

  ```xml
  <sec:http auto-config="true" use-expressions="true">
      <!-- omit -->
      <sec:headers>
          <sec:cache-control />
          <!-- omit -->
      </sec:headers>
      <!-- omit -->
  </sec:http>
  ```

## 6.7. HTTP Strict Transport Security (HSTS)
Coming soon...

* `src/main/resources/META-INF/spring/spring-security.xml`

  ```xml
  <sec:http auto-config="true" use-expressions="true">
      <!-- omit -->
      <sec:headers>
          <!-- omit -->
          <sec:hsts />
          <!-- omit -->
      </sec:headers>
      <!-- omit -->
  </sec:http>
  ```


# 7. Session Management
This section describe about session management in this application.<br>

## 7.1. Detect Session timeout
Coming soon...

