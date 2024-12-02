package sample_project.OnlineCourseManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sample_project.OnlineCourseManagementSystem.dto.InstructorDto;
import sample_project.OnlineCourseManagementSystem.dto.StudentDto;
import sample_project.OnlineCourseManagementSystem.dto.UserDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.UserAlreadyExist;
import sample_project.OnlineCourseManagementSystem.model.Users;
import sample_project.OnlineCourseManagementSystem.repo.UsersRepo;

@Service
public class UserService {
	@Autowired
	private UsersRepo userRepo;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwtService;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public String registerAsInstructor(InstructorDto instructorDto) {
		Users existedUser = userRepo.findByusername(instructorDto.getUsername());
		if (existedUser != null) {
			throw new UserAlreadyExist("User already existed with this username.Please change your username");
		}
		if (instructorDto.getRole() == null || (!instructorDto.getRole().equalsIgnoreCase("ROLE_INSTRUCTOR"))) {
			throw new IllegalArgumentException("Invalid role specified. Allowed role:ROLE_INSTRUCTOR.");
		}
		sample_project.OnlineCourseManagementSystem.enums.Role userRole;
		try {
			userRole = sample_project.OnlineCourseManagementSystem.enums.Role
					.valueOf(instructorDto.getRole().toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid role: " + instructorDto.getRole());
		}
		Users user = new Users();
		user.setUsername(instructorDto.getUsername());
		user.setPassword(encoder.encode(instructorDto.getPassword()));
		user.setEmailId(instructorDto.getEmailId());
		user.setPhoneNumber(instructorDto.getPhoneNumber());
		user.setRole(userRole);
		user.setQualification(instructorDto.getQualification());
		user.setSkills(instructorDto.getSkills());
		user.setExperience(instructorDto.getExperience());
		userRepo.save(user);
		return "Instructor successfully registered with id "+user.getId();
		
	}

	public String registerAsStudent(StudentDto studentDto) {
		Users existedUser = userRepo.findByusername(studentDto.getUsername());
		if (existedUser != null) {
			throw new UserAlreadyExist("User already existed with this username.Please change your username");
		}
		if (studentDto.getRole() == null || (!studentDto.getRole().equalsIgnoreCase("ROLE_STUDENT"))) {
			throw new IllegalArgumentException("Invalid role specified. Allowed role:ROLE_STUDENT.");
		}
		sample_project.OnlineCourseManagementSystem.enums.Role userRole;
		try {
			userRole = sample_project.OnlineCourseManagementSystem.enums.Role
					.valueOf(studentDto.getRole().toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid role: " + studentDto.getRole());
		}
		Users user = new Users();
		user.setUsername(studentDto.getUsername());
		user.setPassword(encoder.encode(studentDto.getPassword()));
		user.setEmailId(studentDto.getEmailId());
		user.setPhoneNumber(studentDto.getPhoneNumber());
		user.setRole(userRole);
		user.setLocation(studentDto.getLocation());
		user.setRegisteredAt(studentDto.getRegisteredAt());
		user.setEducationBackground(studentDto.getEducationBackground());
		userRepo.save(user);
		return "Student successfully registered with id "+user.getId();
	}

	public String verify(UserDto userDto) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
		if (authentication.isAuthenticated()) {
			Users user = userRepo.findByusername(userDto.getUsername());
			return jwtService.generateToken(user.getUsername(), user.getRole().name());
		}
		return "fail";
	}
}
