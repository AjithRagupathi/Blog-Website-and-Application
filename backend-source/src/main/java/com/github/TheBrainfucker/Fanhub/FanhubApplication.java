package com.github.TheBrainfucker.Fanhub;

import com.github.TheBrainfucker.Fanhub.model.Role;
import com.github.TheBrainfucker.Fanhub.model.User;
import com.github.TheBrainfucker.Fanhub.repository.RoleRepository;
import com.github.TheBrainfucker.Fanhub.repository.UserRepository;
import com.github.TheBrainfucker.Fanhub.utils.ConstantUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class FanhubApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(FanhubApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (roleRepository.findAll().isEmpty()) {
			roleRepository.saveAndFlush(new Role(ConstantUtils.ADMIN.toString()));
			roleRepository.saveAndFlush(new Role(ConstantUtils.USER.toString()));
		}

		if (userRepository.findAll().isEmpty()) {
			User user0 = new User();
			user0.setRole(roleRepository.findByName(ConstantUtils.USER.toString()));
			user0.addDemoUser(user0, "USER");
			userRepository.saveAndFlush(user0);

			User user1 = new User();
			user1.setRole(roleRepository.findByName(ConstantUtils.ADMIN.toString()));
			user1.addDemoUser(user1, "ADMIN");
			userRepository.saveAndFlush(user1);
		}
	}
}
