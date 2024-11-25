package sample_project.OnlineCourseManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sample_project.OnlineCourseManagementSystem.model.UserPrincipal;
import sample_project.OnlineCourseManagementSystem.model.Users;
import sample_project.OnlineCourseManagementSystem.repo.UsersRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByusername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User NOT Found with username " + username);
		}
		return new UserPrincipal(user);
	}

}
