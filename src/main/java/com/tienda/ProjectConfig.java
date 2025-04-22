package com.tienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
        registry.addViewController("/contacto").setViewName("contacto");
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

   @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	http
			.authorizeHttpRequests((request) -> request
			.requestMatchers("/", "/index", "/errores/**",
					"/carrito/**", "/reportes/**",
					"/registro/**", "/js/**", "/webjars/**", "/error", "/refrescarBoton")
			.permitAll()
			.requestMatchers(
					"/producto/nuevo", "/producto/guardar",
					"/producto/modificar/**", "/producto/eliminar/**",
					"/categoria/nuevo", "/categoria/guardar",
					"/categoria/modificar/**", "/categoria/eliminar/**",
					"/usuario/nuevo", "/usuario/guardar",
					"/usuario/modificar/**", "/usuario/eliminar/**",
					"/reportes/**", "/pruebas/**"
			).hasRole("ADMIN")
			.requestMatchers(
					"/producto/listado",
					"/categoria/listado",
					"/usuario/listado"
			).hasAnyRole("ADMIN", "VENDEDOR")
			.requestMatchers("/facturar/carrito")
			.hasRole("USER")
			)
			.formLogin((form) -> form
			.loginPage("/login").permitAll())
			.logout((logout) -> logout.permitAll());
	return http.build();
}

