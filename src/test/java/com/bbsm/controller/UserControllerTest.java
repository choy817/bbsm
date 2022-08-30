package com.bbsm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration//서블릿 컨텍스트 객체 사용 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class UserControllerTest {
	
	@Setter(onMethod_ = @Autowired)
	WebAppConfiguration context;
	MockMvc mockMvc;
	
	@Test
	public void loginTest() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.param("userId", "apple")
				.param("userPw", "ss")));
	}

}
