package com.bbsm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bbsm.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BaordServiceTest {
	
	@Setter(onMethod_ = @Autowired)
	BoardService boardService;
	
//	@Test
	public void getListTest() {
		log.info(boardService.getList());
	}
	
//	@Test
	public void getListWithPagingTest() {
		boardService.getList(new Criteria(1, 10)).forEach(board -> log.info(board));;
	}
	
	@Test
	public void viewTest() {
		long boardNo=721L;
		boardService.view(boardNo);
	}

}
