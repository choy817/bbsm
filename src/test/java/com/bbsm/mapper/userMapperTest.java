package com.bbsm.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bbsm.domain.UserDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class userMapperTest {
	
	@Setter(onMethod_ =@Autowired )
	UserMapper userMapper;

	//@Test
	public void checkIdTest() throws Exception{
		String userId="apple";
//		log.info(userMapper.checkId(userId));	
		userMapper.checkId(userId);

	}
	
	//@Test
	public void registerTest() throws Exception{
		UserDTO user=new UserDTO();
		user.setUserNo(1L);
		user.setUserId("banana");
		user.setUserName("바나나 ");
		user.setUserMail("banana@naver.com");
		user.setUserPhone("010-000-0000");
		user.setUserPw("asdf1234");
		user.setUserPwCheck("asdf1234");
		
		userMapper.register(user);
	}
	
	//@Test
	public void loginTest() throws Exception{
		UserDTO user=new UserDTO();
		user.setUserId("apple");
		user.setUserPw("ss");
		
		log.info(userMapper.selectUser(user));
	}
	
	
}
