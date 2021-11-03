package inhatc.inhatcbaekjoon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //springSecurity 필터가 스프링 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/members/new").permitAll()
                .antMatchers("/view/modify/dataStructure/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .loginProcessingUrl("/login")//login주소가 호출이 되면 시큐리티가 가져가 대신 로그인 진행 -> postmapping필요없음
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);
    }
}
