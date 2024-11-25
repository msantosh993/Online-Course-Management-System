package sample_project.OnlineCourseManagementSystem.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sample_project.OnlineCourseManagementSystem.service.JwtService;
import sample_project.OnlineCourseManagementSystem.service.MyUserDetailsService;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response,
			jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
		String token = extractJwtFromRequest(request);
		if (token != null) {
			String username = jwtService.extractUserName(token);
			String role = jwtService.extractRole(token);
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
			if (jwtService.validateToken(token, userDetails, role)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

	private String extractJwtFromRequest(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		return null;
	}

}
