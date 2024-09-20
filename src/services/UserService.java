package services;

import dto.SignUpForm;
import model.User;
import repositorys.UserRepository;

public class UserService {
	private User user;
	private UserRepository userRepository;
	public User createUser(String userName,String password,String email) {
	      user = new User();
	      userRepository = new UserRepository();
	      user.setUsername(userName);
	      user.setEmail(email);
	      user.setPassword(password);
	      user = userRepository.save(user);
	      return user;
	    }
}
