package uz.pdp.WebAuto.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import uz.pdp.WebAuto.filters.JwtTokenFilter;
import uz.pdp.WebAuto.security.CustomUserDetailsService;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final UserDetailsService userDetailsService;
    private static final String[] WHITE_LIST = {
            "/auth/**",
            "/user/**",
            "/car/**",

            "/auth/**",
            "/error",

            "/user/get/**",
            "user/home/**",
            "category/**",

            "/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",

            "/file/**"
    };

    @Lazy
    public SecurityConfig(@Qualifier("urlBasedCorsConfig") CorsConfigurationSource corsConfigurationSource,
                          CustomUserDetailsService customUserDetailsService,
                          AuthenticationEntryPoint authenticationEntryPoint,
                          AccessDeniedHandler accessDeniedHandler) {
        this.corsConfigurationSource = corsConfigurationSource;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.userDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource))

                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(WHITE_LIST).permitAll() // Ochiq so'rovlar
                        .anyRequest().authenticated()) // Boshqa barcha so'rovlar autentifikatsiya qilinishi kerak

                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessiyalar

                .exceptionHandling((exception) -> exception
                        .authenticationEntryPoint(authenticationEntryPoint) // Kirish uchun xatolik
                        .accessDeniedHandler(accessDeniedHandler)) // Kirish uchun ruxsat etilmagan xatolik

                .userDetailsService(userDetailsService) // Custom UserDetailsService
                .passwordManagement(password -> passwordEncoder()) // PasswordEncoder qo'shish

                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT token filtri qo'shish

        return http.build();
    }


    @Bean("urlBasedCorsConfig")
    public CorsConfigurationSource corsConfig() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // 13.60.215.5:8080
        configuration.setAllowedMethods(List.of("*")); // GET, POST, PUT,DELETE, OPTION
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
