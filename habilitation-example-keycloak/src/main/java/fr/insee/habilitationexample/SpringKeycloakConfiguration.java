package fr.insee.habilitationexample;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@EnableGlobalMethodSecurity(prePostEnabled = true, order = Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ComponentScan(basePackageClasses = {org.keycloak.adapters.springsecurity.KeycloakSecurityComponents.class})
@EnableWebSecurity
public class SpringKeycloakConfiguration extends KeycloakWebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        System.out.println(auth.isConfigured());
        auth.authenticationProvider(keycloakAuthenticationProvider());
        System.out.println(auth.isConfigured());
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println(auth.isConfigured());
//        auth.authenticationProvider(keycloakAuthenticationProvider());
//        System.out.println(auth.isConfigured());
//    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        // required for bearer-only applications.
        return new NullAuthenticatedSessionStrategy();
    }

    @Bean
    public KeycloakConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests().anyRequest().authenticated().and()
                .csrf().disable();
    }

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver(KeycloakSpringBootProperties properties) {
        return new MyKeycloakConfigSpringBootResolverConfiguration(properties);
    }


    public static class MyKeycloakConfigSpringBootResolverConfiguration extends KeycloakSpringBootConfigResolver {
        private final KeycloakDeployment keycloakDeployment;

        public MyKeycloakConfigSpringBootResolverConfiguration(KeycloakSpringBootProperties properties) {
            keycloakDeployment = KeycloakDeploymentBuilder.build(properties);
        }

        @Override
        public KeycloakDeployment resolve(HttpFacade.Request facade) {
            return keycloakDeployment;
        }
    }


}
