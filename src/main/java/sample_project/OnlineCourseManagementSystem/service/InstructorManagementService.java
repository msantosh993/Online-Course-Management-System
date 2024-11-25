package sample_project.OnlineCourseManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample_project.OnlineCourseManagementSystem.dto.InstructorDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.InstructorNotFound;
import sample_project.OnlineCourseManagementSystem.model.Instructor;
import sample_project.OnlineCourseManagementSystem.repo.InstructorRepo;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorManagementService {

	@Autowired
	private InstructorRepo instructorRepo;

	public Instructor createInstructor(InstructorDto instructorDto) {
		Instructor instructor = new Instructor();
		instructor.setInstructorName(instructorDto.getInstructorName());
		instructor.setInstructorPhoneNumber(instructorDto.getInstructorPhoneNumber());
		instructor.setInstructorEmailId(instructorDto.getInstructorEmailId());
		instructor.setInstructorQualification(instructorDto.getInstructorQualification());
		instructor.setInstructorSkills(instructorDto.getInstructorSkills());
		instructor.setInstructorExperience(instructorDto.getInstructorExperience());
		return instructorRepo.save(instructor);
	}

	public List<Instructor> getAllInstructors() {
		return instructorRepo.findAll();
	}

	public Instructor getInstructorById(Integer id) {
		Optional<Instructor> optionalInstructor = instructorRepo.findById(id);
		if (optionalInstructor.isPresent()) {
			return optionalInstructor.get();
		}
		throw new InstructorNotFound("Instructor not found with id " + id);
	}

	public Instructor updateInstructor(Integer id, InstructorDto instructorDto) {
		Optional<Instructor> optionalInstructor = instructorRepo.findById(id);
		if (optionalInstructor.isPresent()) {
			Instructor existingInstructor = optionalInstructor.get();
			existingInstructor.setInstructorName(instructorDto.getInstructorName());
			existingInstructor.setInstructorPhoneNumber(instructorDto.getInstructorPhoneNumber());
			existingInstructor.setInstructorEmailId(instructorDto.getInstructorEmailId());
			existingInstructor.setInstructorQualification(instructorDto.getInstructorQualification());
			existingInstructor.setInstructorSkills(instructorDto.getInstructorSkills());
			existingInstructor.setInstructorExperience(instructorDto.getInstructorExperience());
			return instructorRepo.save(existingInstructor);
		}
		throw new InstructorNotFound("Instructor not found with id " + id);
	}

	public String deleteInstructor(Integer id) {
		Optional<Instructor> optionalInstructor = instructorRepo.findById(id);
		if (optionalInstructor.isPresent()) {
			instructorRepo.delete(optionalInstructor.get());
			return "Instructor deleted successfully with id " + optionalInstructor.get().getInstructorId();
		}
		throw new InstructorNotFound("Instructor not found with id " + id);
	}
}
