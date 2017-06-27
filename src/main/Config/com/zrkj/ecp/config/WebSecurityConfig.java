package com.zrkj.ecp.config;

import com.zrkj.ecp.security.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService customUserService(){
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/test/**").permitAll()
                .antMatchers("shopGoodsClassController/jsonTopShopGoodsClassList").permitAll()
                .antMatchers("/expressBillController/jsonSendExpressBillList").permitAll()
                .antMatchers("/UserManagent/mobile/loginCheck").permitAll()
                .antMatchers("/expressBillController/jsonToSendExpressBillList").permitAll()
                .antMatchers("/expressBillController/jsonExpressBillOne").permitAll()
                .antMatchers("/expressBillController/mobile/**").permitAll()
                .antMatchers("/expressFdexController/jsonExpressFdexList").permitAll()
                .antMatchers("/expressFdexController/jsonExpressFdexOne").permitAll()
                .antMatchers("/expressFdexController/mobile/**").permitAll()
                .antMatchers("/expressSendFeeController/").permitAll()
                .antMatchers("/expressSendFeeController/jsonExpressSendFeeList").permitAll()
                .antMatchers("/expressSendFeeController/jsonExpressSendFeeOne").permitAll()
                .antMatchers("/expressSendFeeController/mobile/**").permitAll()
                .antMatchers("/expressLogController/**").permitAll()
                .antMatchers("/expressAbnormalController/**").permitAll()
                .antMatchers("/expressReasoncController/**").permitAll()
                .antMatchers("/memberController/mobile/**").permitAll()
                .antMatchers("/memberController/memberController/getEquipmentList").permitAll()
                .antMatchers("/memberBcController/mobile/**").permitAll()
                .antMatchers("/memberBalacneController/mobile/**").permitAll()
                .antMatchers("/memberBalanceActicityController/jsonBalanceActicityList/**").permitAll()
                .antMatchers("/memberController/getEquipmentList").permitAll()
                .antMatchers("/memberBalanceActicityController/mobile/**").permitAll()
                .antMatchers("/expressCheckRecordController/checkExpress").permitAll()
                .antMatchers("/CompanyManagent/schoollist").permitAll()
                .antMatchers("/parameController/branchesList").permitAll()
                .antMatchers("/memberAddressController/mobile/**").permitAll()
                .antMatchers("/parameController/obtainConfigNameByConfigId").permitAll()
                .antMatchers("/memberController/uploadPhoto").permitAll()
                .antMatchers("/expressOrderController/**").permitAll()
                .antMatchers("/userPhoto/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/adminController/mainPage")
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .failureUrl("/login?error")
                .and()
                    .rememberMe()
                    .tokenValiditySeconds(1209600)
                    .key("myKey")
                .and()
                .logout().permitAll()
                .and()
                    .csrf()
                    .disable()
                    .headers()
                    .frameOptions()
                    .sameOrigin()
                    .disable();

    }

    /**
     * /resources/static/下的静态资源,Spring Security不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }
}
