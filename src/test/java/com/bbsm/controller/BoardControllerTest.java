package com.bbsm.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration //서블릿 컨텍스트 객체 사용 시
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTest {
	
	@Setter(onMethod_ = @Autowired)
	WebApplicationContext context;
	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
	}
	
//	@Test
	public void getListWithPaging() throws Exception {
		log.info(mockMvc.perform(
				MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")
				.param("amount","10"))
				.andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void modifyTest() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("boardNo", "515")
				.param("boardTitle", "게시글 수정 테스트 ")
				.param("boardContent","컨트롤러 테스트 "))
				.andReturn().getModelAndView().getModelMap());
	}

}
