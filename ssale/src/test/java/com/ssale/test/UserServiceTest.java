package com.ssale.test;

import java.util.Date;

import org.junit.Test;

import com.ssale.entity.User;
import com.ssale.service.UserService;
import com.ssale.util.SpringUtils;

public class UserServiceTest extends SpringUtils {

	@Test
	public void saveTest() {
		for (int i = 1; i < 21; i++) {

			User user = new User();
			user.setAccount("bbb" + i);
			user.setName("李四" + i);
			user.setPassword("bbb");
			user.setLastLogin(new Date());
			user.setCreateDate(new Date());
			UserService userService = (UserService) context.getBean("userService");
			userService.saveEntity(user);

		}
	}

	@Test
	public void deleteTest() {
		UserService userService = (UserService) context.getBean("userService");
		User user = userService.getUserByAccount("123");
		System.out.println(user.getId());
		userService.deleteEntity("1223");
	}

	@Test
	public void updateTest() {
		UserService userService = (UserService) context.getBean("userService");
		User user = userService.getUserByAccount("123");
		System.out.println(user.getId());
		user.setName("aaa");
		userService.updateEntity(user);
	}

}
