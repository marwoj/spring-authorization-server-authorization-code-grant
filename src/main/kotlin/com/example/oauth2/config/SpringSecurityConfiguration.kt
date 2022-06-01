package com.example.oauth2.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
class SpringSecurityConfiguration {
    @Bean
    fun configureSecurityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests.anyRequest().authenticated()
            }
            .formLogin(Customizer.withDefaults())
        return http.build()
    }

    @Bean
    fun users(): UserDetailsService? {
        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        val user: UserDetails = User.withUsername("username")
            .password(encoder.encode("password"))
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }
}