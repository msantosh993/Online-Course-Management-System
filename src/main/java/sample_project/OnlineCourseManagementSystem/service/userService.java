package sample_project.OnlineCourseManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sample_project.OnlineCourseManagementSystem.dto.UserDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.UserAlreadyExist;
import sample_project.OnlineCourseManagementSystem.model.Users;
import sample_project.OnlineCourseManagementSystem.repo.UsersRepo;

@Service
public class userService {
	@Autowired
	private UsersRepo userRepo;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwtService;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public Users register(UserDto userDto) {
		Users existedUser = userRepo.findByusername(userDto.getUsername());
		if (existedUser != null) {
			throw new UserAlreadyExist("User already existed with this username.Please change your username");
		}
		if(userDto.getRole()==null || (!userDto.getRole().equalsIgnoreCase("ROLE_STUDENT") && !userDto.getRole().equalsIgnoreCase("ROLE_INSTRUCTOR")))
		{
			throw new IllegalArgumentException("Invalid role specified. Allowed roles: ROLE_STUDENT, ROLE_INSTRUCTOR.");
		}
		sample_project.OnlineCourseManagementSystem.enums.Role userRole;
		try 
		{
			userRole=sample_project.OnlineCourseManagementSystem.enums.Role.valueOf(userDto.getRole().toUpperCase());
		}
		catch(IllegalArgumentException e)
		{
			throw new IllegalArgumentException("Invalid role: "+userDto.getRole());
		}
		Users user = new Users();
		user.setUsername(userDto.getUsername());
		user.setPassword(encoder.encode(userDto.getPassword()));
		user.setEmailId(userDto.getEmailId());
		user.setRole(userRole);
		return userRepo.save(user);
	}

	public String verify(UserDto userDto) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
		if (authentication.isAuthenticated()) {
			Users user=userRepo.findByusername(userDto.getUsername());
			return jwtService.generateToken(user.getUsername(),user.getRole().name());
		}
		return "fail";
	}
}
