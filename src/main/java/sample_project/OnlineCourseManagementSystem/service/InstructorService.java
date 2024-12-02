package sample_project.OnlineCourseManagementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sample_project.OnlineCourseManagementSystem.dto.InstructorDetails;
import sample_project.OnlineCourseManagementSystem.dto.InstructorDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.UserNotFound;
import sample_project.OnlineCourseManagementSystem.model.Users;
import sample_project.OnlineCourseManagementSystem.repo.UsersRepo;

@Service
public class InstructorService {

	@Autowired
	private UsersRepo userRepo;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public InstructorDetails findInstructorById(Integer id) {
		Optional<Users> instructor = userRepo.findById(id);
		if (instructor.isPresent() && (instructor.get().getRole().toString()).equals("ROLE_INSTRUCTOR")) {
			Users fetchedInstructor = instructor.get();
			InstructorDetails resultedInstructor = new InstructorDetails();
			resultedInstructor.setInstructorName(fetchedInstructor.getUsername());
			resultedInstructor.setPassword(fetchedInstructor.getPassword());
			resultedInstructor.setEmailId(fetchedInstructor.getEmailId());
			resultedInstructor.setPhoneNumber(fetchedInstructor.getPhoneNumber());
			resultedInstructor.setRole(fetchedInstructor.getRole().toString());
			resultedInstructor.setExperience(fetchedInstructor.getExperience());
			resultedInstructor.setQualification(fetchedInstructor.getQualification());
			resultedInstructor.setSkills(fetchedInstructor.getSkills());
			resultedInstructor.setCourses(fetchedInstructor.getCourses());
			return resultedInstructor;
		} else {
			throw new UserNotFound("Instructor not found with id: " + id);
		}
	}

	public InstructorDetails findInstructorByUserName(String username) {
		Users instructor = userRepo.findByusername(username);
		if (instructor != null && (instructor.getRole().toString()).equals("ROLE_INSTRUCTOR")) {
			InstructorDetails resultedInstructor = new InstructorDetails();
			resultedInstructor.setInstructorName(instructor.getUsername());
			resultedInstructor.setPassword(instructor.getPassword());
			resultedInstructor.setEmailId(instructor.getEmailId());
			resultedInstructor.setPhoneNumber(instructor.getPhoneNumber());
			resultedInstructor.setRole(instructor.getRole().toString());
			resultedInstructor.setExperience(instructor.getExperience());
			resultedInstructor.setQualification(instructor.getQualification());
			resultedInstructor.setSkills(instructor.getSkills());
			resultedInstructor.setCourses(instructor.getCourses());
			return resultedInstructor;
		}
		throw new UserNotFound("Instructor Not Found with username " + username);
	}

	public String updateInstructorById(Integer id, InstructorDto instructorDto) {
		Optional<Users> instructor = userRepo.findById(id);
		if (instructor.isPresent() && (instructor.get().getRole().toString()).equals("ROLE_INSTRUCTOR")) {
			Users existedInstructor = instructor.get();
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
			existedInstructor.setUsername(instructorDto.getUsername());
			existedInstructor.setPassword(encoder.encode(instructorDto.getPassword()));
			existedInstructor.setEmailId(instructorDto.getEmailId());
			existedInstructor.setPhoneNumber(instructorDto.getPhoneNumber());
			existedInstructor.setRole(userRole);
			existedInstructor.setQualification(instructorDto.getQualification());
			existedInstructor.setSkills(instructorDto.getSkills());
			existedInstructor.setExperience(instructorDto.getExperience());
			userRepo.save(existedInstructor);
			return "Instructor with id " + id + " details updated successfully";
		}
		throw new UserNotFound("Instructor Not Found with Id " + id);
	}

	public String deleteInstructor(Integer id) {
		Optional<Users> instructor = userRepo.findById(id);
		if (instructor.isPresent() && (instructor.get().getRole().toString()).equals("ROLE_INSTRUCTOR")) {
			userRepo.delete(instructor.get());
			return "Instructor deleted successfully with id " + id;
		} else {
			throw new UserNotFound("Instructor not found with id: " + id);
		}
	}
}
