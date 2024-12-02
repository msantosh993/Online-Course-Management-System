package sample_project.OnlineCourseManagementSystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sample_project.OnlineCourseManagementSystem.dto.ContentDto;
import sample_project.OnlineCourseManagementSystem.service.ContentService;

@RestController
@RequestMapping("/api/contents")
@Validated
public class ContentController {

	@Autowired
	private ContentService contentService;

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PostMapping("/uploadContent")
	public ResponseEntity<String> uploadContent(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "contentTitle", required = false) String contentTitle,
			@RequestParam(value = "courseId", required = false) Integer courseId,
			@RequestParam(value = "contentCreatedAt", required = false) String contentCreatedAt) throws IOException {

		if (file == null || file.isEmpty()) {
			return new ResponseEntity<>("You have entered an empty file.", HttpStatus.BAD_REQUEST);
		}
		if (contentTitle == null || contentTitle.trim().isEmpty()) {
			return new ResponseEntity<>("You have entered an empty content title.", HttpStatus.BAD_REQUEST);
		}
		if (courseId == null) {
			return new ResponseEntity<>("You have entered an empty course ID.", HttpStatus.BAD_REQUEST);
		}
		if (contentCreatedAt == null || contentCreatedAt.trim().isEmpty()) {
			return new ResponseEntity<>("You have entered an empty content created date.", HttpStatus.BAD_REQUEST);
		}

		ContentDto contentDto = new ContentDto();
		contentDto.setContentTitle(contentTitle);
		contentDto.setCourseId(courseId);
		contentDto.setContentCreatedAt(contentCreatedAt);

		String message = contentService.uploadContent(contentDto, file);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ROLE_INSTRUCTOR','ROLE_STUDENT')")
	@GetMapping("/getContentWithoutFile/{contentId}")
	public ResponseEntity<ContentDto> getContentWithoutFile(@PathVariable Integer contentId) {
		ContentDto contentDto = contentService.getContentWithoutFileById(contentId);
		return new ResponseEntity<>(contentDto, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_INSTRUCTOR','ROLE_STUDENT')")
	@GetMapping("/getAllContentsByCourseIdWithoutFiles/{courseId}")
    public List<ContentDto> getContentsByCourseId(@PathVariable Integer courseId) {
        return contentService.getContentsByCourseId(courseId);
    }
	@PreAuthorize("hasAnyRole('ROLE_INSTRUCTOR','ROLE_STUDENT')")
	@GetMapping("/downloadContentById/{contentId}")
	public ResponseEntity<byte[]> downloadContentById(@PathVariable Integer contentId) {
		byte[] contentData = contentService.downloadContentByContentId(contentId);
		return ResponseEntity.ok(contentData);
	}

	@PreAuthorize("hasAnyRole('ROLE_INSTRUCTOR','ROLE_STUDENT')")
	@GetMapping("/downloadContentByTitle/{contentTitle}")
	public ResponseEntity<byte[]> downloadContentByTitle(@PathVariable String contentTitle) {
		byte[] contentData = contentService.downloadContentByContentTitle(contentTitle);
		return ResponseEntity.ok(contentData);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PutMapping("/updateContent/{contentId}")
	public ResponseEntity<String> updateContent(@PathVariable Integer contentId,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "contentTitle", required = false) String contentTitle,
			@RequestParam(value = "contentCreatedAt", required = false) String contentCreatedAt) throws IOException {
		if (file == null || file.isEmpty()) {
			return new ResponseEntity<>("You have entered an empty file", HttpStatus.BAD_REQUEST);
		}
		if (contentTitle == null || contentTitle.isEmpty()) {
			return new ResponseEntity<>("You have entered an empty content title value", HttpStatus.BAD_REQUEST);
		}
		if (contentCreatedAt == null || contentCreatedAt.isEmpty()) {
			return new ResponseEntity<>("You have entered an empty content created date", HttpStatus.BAD_REQUEST);
		}
		ContentDto contentDto = new ContentDto();
		contentDto.setContentTitle(contentTitle);
		contentDto.setContentCreatedAt(contentCreatedAt);

		String message = contentService.updateContent(contentId, contentDto, file);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@DeleteMapping("/deleteContent/{contentId}")
	public ResponseEntity<String> deleteContent(@PathVariable Integer contentId) {
		String message = contentService.deleteContent(contentId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
