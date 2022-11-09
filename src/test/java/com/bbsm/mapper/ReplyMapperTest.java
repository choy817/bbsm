package com.bbsm.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bbsm.domain.Criteria;
import com.bbsm.domain.ReplyDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	ReplyMapper replyMapper;
	
	long[] bnoArr= {1L, 513L, 514L, 516L, 517L};
	
//	@Test
	public void testMapper() {
		log.info(replyMapper);
	}
	
//	@Test
	public void insertTest() {
		IntStream.rangeClosed(1, 10).forEach(i ->{
			ReplyDTO reply = new ReplyDTO();
			
			reply.setBoardNo(bnoArr[i%5]);
			reply.setReplyContent("댓글 테스트 "+i);
			reply.setReplyer("replyer"+i);
			
			replyMapper.insertReply(reply);
		});
		
	}
	
//	@Test
	public void getTest() {
		Long replyNo=1L;
		ReplyDTO reply=replyMapper.getReply(replyNo);
		
		log.info(reply);
		
	}
	
	@Test
	public void updateTest() {
		ReplyDTO reply= new ReplyDTO();
		reply.setReplyNo(2L);
		reply.setReplyContent("댓글수정테스트.");
		int result=replyMapper.updateReply(reply);
		log.info(result);
	}
	
//	@Test
	public void deleteTest() {
		Long replyNo=1L;
		replyMapper.deleteReply(replyNo);
		
	}
	
//	@Test
	public void listTest() {
		Criteria cri=new Criteria();
		List<ReplyDTO> replies=replyMapper.getListWithPaging(cri, bnoArr[1]);
		replies.forEach(reply -> log.info(reply));
	}
}
