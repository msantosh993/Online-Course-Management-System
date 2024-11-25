package sample_project.OnlineCourseManagementSystem.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void sendEmail(String to, String Subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(Subject);
		message.setText(text);
	}
}
