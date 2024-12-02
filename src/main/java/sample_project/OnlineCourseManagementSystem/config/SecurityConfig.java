package sample_project.OnlineCourseManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import sample_project.OnlineCourseManagementSystem.filter.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers("api/login", "api/register/instructor", "api/register/student").permitAll()
						.requestMatchers("/api/instructors/**", "/api/courses/createCourse",
								"/api/courses/updateCourse/{courseId}", "/api/courses/deleteCourse/{courseId}",
								"/api/contents/uploadContent", "/api/contents/deleteContent/{contentId}",
								"api/contents/updateContent/{contentId}", "/api/enrollments/getAllEnrollmentsOfaCourse/{courseId}",
								"/api/students/deleteStudentById/{id}", "/api/reviews/getAllReviews/{courseId}")
						.hasRole("INSTRUCTOR")
						.requestMatchers("/api/students/getStudentById/{id}",
								"/api/students/getStudentByUsername/{username}", "/api/students/updateStudentById/{id}",
								"/api/enrollments/enrollStudent", "/api/enrollments/getEnrollment/{enrollmentId}",
								"/api/reviews/postReview", "/api/reviews/getReviewById/{reviewId}",
								"/api/reviews/updateReviewById/{reviewId}", "/api/reviews/deleteReview/{reviewId}")
						.hasRole("STUDENT")
						.requestMatchers("/api/courses/getCourseById/{courseId}",
								"/api/courses/getCourseByTitle/{courseTitle}",
								"/api/contents/downloadContentById/{contentId}",
								"/api/contents/downloadContentByTitle/{contentTitle}",
								"/api/contents/getContentWithoutFile/{contentId}",
								"/api/contents/getAllContentsByCourseIdWithoutFiles/{courseId}")
						.hasAnyRole("INSTRUCTOR", "STUDENT").anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
