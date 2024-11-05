package cat.udl.eps.softarch.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Value("${allowed-origins}")
    String[] allowedOrigins;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.GET, "/identity").authenticated()
                        .requestMatchers(HttpMethod.POST, "/apartments").hasAuthority("ROLE_OWNER")
                        .requestMatchers(HttpMethod.POST, "/apartments/").hasAnyRole("OWNER")
                        .requestMatchers(HttpMethod.POST, "/apartments/*").hasAnyRole("OWNER")
                        .requestMatchers(HttpMethod.POST, "/advertisementStatuses").hasAnyRole("OWNER")
                        .requestMatchers(HttpMethod.DELETE, "/advertisementStatuses").hasAnyRole("OWNER")
                        .requestMatchers(HttpMethod.POST, "/users").anonymous()
                        .requestMatchers(HttpMethod.POST, "/users/*").denyAll()
                        .requestMatchers(HttpMethod.POST, "/rooms").authenticated()
                        .requestMatchers(HttpMethod.POST, "/rooms/*").hasAnyRole("OWNER")
                        .requestMatchers(HttpMethod.POST, "/properties").hasAuthority("ROLE_OWNER")
                        .requestMatchers(HttpMethod.PUT, "/properties/*").hasAuthority("ROLE_OWNER")
                        .requestMatchers(HttpMethod.DELETE, "/properties/*").hasAuthority("ROLE_OWNER")
                        .requestMatchers(HttpMethod.POST, "/visits").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/visits/*").authenticated()
                        .requestMatchers(HttpMethod.POST, "/*/*").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/*/*").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/*/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/*/*").authenticated()
                        .anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors((cors) -> cors.configurationSource(corsConfigurationSource()))
                .httpBasic((httpBasic) -> httpBasic.realmName("demo"));
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList(allowedOrigins));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
