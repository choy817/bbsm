package com.bbsm.mapper;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
		String cate="notice";
		log.info(boardMapper.getList());
	}
//	@Test
	public void getTotalTest() {
		Criteria cri=new Criteria();
		String cate="notice";
		log.info(boardMapper.getTotal(cri));
	}
	@Test
	public void getListWithPagingTest() {
		Criteria cri=new Criteria();
		
		cri.setPageNum(1);
		cri.setAmount(10);
		cri.setCate("free");
		
		List<BoardDTO> list=boardMapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board.getBoardNo()));
	
	}
//	@Test
	public void readTest() {
		long boardNo=1L;
		boardMapper.read(boardNo);
	}
	
//	@Test
	public void modifyTest() {
		BoardDTO board=new BoardDTO();
		board.setBoardNo(3088L);
		board.setBoardTitle("수정 테스트!!!!");
		board.setBoardContent("수정되어라 ");
		board.setCate("free");
		log.info(boardMapper.modifyBoard(board));
	}
	
//	@Test
	public void deleteTest() {
		BoardDTO board=new BoardDTO();
		board.setBoardNo(3092L);
		board.setCate("free");
		log.info(boardMapper.deleteBoard(board));
	}
	
	
//	@Test
	public void searchTest() {
		Criteria cri=new Criteria();
		cri.setKeyword("modify");
		cri.setType("C");
		
		String cate = "notice";
		
//		List<BoardDTO> list=boardMapper.getListWithPaging(cri);
//		
//		list.forEach(board -> log.info(board));
		
	}
//	@Test
	public void writeTest() {
		BoardDTO board=new BoardDTO();
		board.setBoardTitle("write test");
		board.setBoardWriter("banana");
		board.setBoardContent("test");
		board.setCate("free");
		boardMapper.write(board);
	}
	
//	@Test
	public void viewsCntTest() {
//		BoardDTO board=new BoardDTO();
//		board.setBoardNo(1029L);
//		log.info(boardMapper.viewsCnt(board));
		Long boardNo=1025L;
		log.info(boardMapper.viewsCnt(boardNo));
	}
	
//	@Test
	public void updateReco() {
		long boardNo=3008L;
		log.info(boardMapper.updateReco(boardNo));
	}
	
//	@Test
	public void mapGetListTest() {
		log.info(boardMapper.getMapList());
	}

}
