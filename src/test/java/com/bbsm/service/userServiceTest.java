package com.bbsm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bbsm.domain.UserDTO;
import com.bbsm.mapper.userMapperTest;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class userServiceTest {
	
	@Setter(onMethod_ = @Autowired)
	UserService userService;
	
//	@Test
	public void checkIdTest() {
		String userId="apple";
		userService.checkId(userId);
		
	}
	@Test
	public void registerTest() {
		UserDTO user=new UserDTO();
		user.setUserNo(3l);
		user.setUserId("cherry");
		user.setUserName("유체리");
		user.setUserMail("chr@nver.com");
		user.setUserPhone("010-00-000");
		user.setUserPw("s");
		user.setUserPwCheck("s");
		
		userService.register(user);
	}
}
