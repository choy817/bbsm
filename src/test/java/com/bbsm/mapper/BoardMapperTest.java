package com.bbsm.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bbsm.domain.BoardDTO;
import com.bbsm.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	BoardMapper boardMapper;
	
//	@Test
	public void getListTest() {
		log.info(boardMapper.getList());
	}
//	@Test
	public void getListWithPagingTest() {
		Criteria cri=new Criteria();
		
		cri.setPageNum(1);
		cri.setAmount(10);
		
		List<BoardDTO> list=boardMapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board.getBoardNo()));
	
	}
	@Test
	public void readTest() {
		long boardNo=1L;
		boardMapper.read(boardNo);
	}

}