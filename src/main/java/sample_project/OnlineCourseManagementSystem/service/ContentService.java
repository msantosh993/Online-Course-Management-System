package sample_project.OnlineCourseManagementSystem.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sample_project.OnlineCourseManagementSystem.dto.ContentDto;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.ContentAlreadyExist;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.ContentNotFound;
import sample_project.OnlineCourseManagementSystem.exceptionHandler.CourseNotFound;
import sample_project.OnlineCourseManagementSystem.model.Content;
import sample_project.OnlineCourseManagementSystem.model.Course;
import sample_project.OnlineCourseManagementSystem.repo.ContentRepo;
import sample_project.OnlineCourseManagementSystem.repo.CourseRepo;

@Service
public class ContentService {

	@Autowired
	private ContentRepo contentRepo;

	@Autowired
	private CourseRepo courseRepo;

	public String uploadContent(ContentDto contentDto, MultipartFile file) throws IOException {
		Optional<Content> existedContent = contentRepo.findByContentTitle(contentDto.getContentTitle());
		if (existedContent.isPresent()) {
			throw new ContentAlreadyExist(
					"There is already content existed with that name you have to change the Name of the file");
		}
		Content content = new Content();
		Optional<Course> course = courseRepo.findById(contentDto.getCourseId());
		if (course.isPresent()) {
			content.setCourse(course.get());
		} else {
			throw new CourseNotFound("Course not found with ID: " + contentDto.getCourseId());
		}

		content.setContentTitle(contentDto.getContentTitle());
		content.setContentCreatedAt(contentDto.getContentCreatedAt());
		content.setContentFile(file.getBytes());
		Content contentCreated = contentRepo.save(content);
		if (contentCreated != null) {
			return "file uploaded successfully : " + file.getOriginalFilename()+" and Content id is "+contentCreated.getContentId();
		}
		return null;
	}

	public byte[] downloadContentByContentId(Integer contentId) {
		Optional<Content> content = contentRepo.findById(contentId);
		if (content.isPresent()) {
			byte[] contentData = content.get().getContentFile();
			return contentData;
		}
		throw new ContentNotFound("Content not found with ID: " + contentId);
	}

	public byte[] downloadContentByContentTitle(String contentTitle) {
		Optional<Content> content = contentRepo.findByContentTitle(contentTitle);
		if (content.isPresent()) {
			return content.get().getContentFile();
		} else {
			throw new ContentNotFound("Content not found with title: " + contentTitle);
		}
	}

	public ContentDto getContentWithoutFileById(Integer contentId) {
		Optional<Content> content = contentRepo.findById(contentId);
		if (content.isPresent()) {
			Content contentData = content.get();
			ContentDto contentDto = new ContentDto();
			contentDto.setContentTitle(contentData.getContentTitle());
			contentDto.setContentCreatedAt(contentData.getContentCreatedAt());
			contentDto.setCourseId(contentData.getCourse().getCourseId());
			return contentDto;
		} else {
			throw new ContentNotFound("Content not found with ID: " + contentId);
		}
	}

	public List<ContentDto> getContentsByCourseId(Integer courseId) {
        List<Content> contents = contentRepo.findByCourseCourseId(courseId);

        if (contents.isEmpty()) {
            throw new ContentNotFound("No content found for Course ID: " + courseId);
        }

        List<ContentDto> contentDtos = new ArrayList<>();
        for (Content content : contents) {
            ContentDto contentDto = new ContentDto();
            contentDto.setContentTitle(content.getContentTitle());
            contentDto.setContentCreatedAt(content.getContentCreatedAt());
            contentDto.setCourseId(content.getCourse().getCourseId());
            contentDtos.add(contentDto);
        }
        return contentDtos;
    }
	public String updateContent(Integer contentId, ContentDto contentDto, MultipartFile file) throws IOException {
		Optional<Content> optionalContent = contentRepo.findById(contentId);
		if (optionalContent.isPresent()) {
			Content content = optionalContent.get();
			content.setContentTitle(contentDto.getContentTitle());
			content.setContentCreatedAt(contentDto.getContentCreatedAt());
			content.setContentFile(file.getBytes());
			Content updatedContent = contentRepo.save(content);
			if (updatedContent != null) {
				return "file updated successfully for content id "+contentId+" New File Name : " + file.getOriginalFilename();
			}
			return null;
		}
		throw new ContentNotFound("Content not found with ID: " + contentId);
	}

	public String deleteContent(Integer contentId) {
		Optional<Content> content = contentRepo.findById(contentId);
		if (content.isPresent()) {
			contentRepo.delete(content.get());
			return "Content deleted successfully for content id "+contentId+" and content file " +content.get().getContentTitle();
		} else {
			throw new ContentNotFound("Content not found with ID: " + contentId);
		}
	}
}
