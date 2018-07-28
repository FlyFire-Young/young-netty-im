package org.young.base.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.young.base.entity.User;
import org.young.base.impl.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(getPermitAllMatchers()).permitAll()
                .antMatchers(getUserMatchers()).hasAnyAuthority(User.ROLE_USER)
                .antMatchers(getAdminMatchers()).hasAnyAuthority(User.ROLE_ADMIN)
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(loginUrlAuthenticationEntryPointImpl())
                .and()
                .formLogin()
                .usernameParameter("y_username")
                .passwordParameter("y_password")
                .loginPage("/y/login")
                .loginProcessingUrl("/loginValid")
                .failureHandler(simpleUrlAuthenticationFailureHandler())
                .successHandler(simpleUrlAuthenticationSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .deleteCookies("remove")
                .invalidateHttpSession(false)
                .logoutUrl("/userLogout")
                .logoutSuccessHandler(simpleUrlLogoutSuccessHandler())
                .permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new Md5PasswordEncoder());
    }

    @Override
    @Bean("authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/yg/**", "/j-b-1/**", "/y-im-cus/**");
    }

    @Bean
    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPointImpl() {
        return new LoginUrlAuthenticationEntryPointImpl("/y/login");
    }

    @Bean
    public AuthenticationFailureHandlerImpl simpleUrlAuthenticationFailureHandler() {
        return new AuthenticationFailureHandlerImpl();
    }

    @Bean
    public AuthenticationSuccessHandlerImpl simpleUrlAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandlerImpl();
    }


    @Bean
    public LogoutSuccessHandler simpleUrlLogoutSuccessHandler() {
        return new LogoutSuccessHandlerImpl();
    }

    private String[] getPermitAllMatchers() {
        return new String[]{
                "/y/**",
                "/"
        };
    }

    private String[] getUserMatchers() {
        return new String[]{
                "/auth/**",
                "/**"
        };
    }

    private String[] getAdminMatchers() {
        return new String[]{
                "/**"
        };
    }
}
