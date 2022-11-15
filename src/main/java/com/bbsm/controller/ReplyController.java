package com.bbsm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbsm.domain.Criteria;
import com.bbsm.domain.ReplyDTO;
import com.bbsm.domain.ReplyPageDTO;
import com.bbsm.service.ReplySerivce;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/replies/*")
public class ReplyController {
	
	@Setter(onMethod_ = @Autowired)
	ReplySerivce replyService;
	
	//consumes: 요청, produces: 응답.
	@PostMapping(value = "/create", consumes="application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> create(@RequestBody ReplyDTO reply) {
		log.info("Controller ==============> create........."+reply);
		int insertCnt=replyService.registerReply(reply);
		log.info("reply return : "+insertCnt);
		return insertCnt==1? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 전체 조회 
	@GetMapping(value = "/pages/{boardNo}/{page}", produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("boardNo") Long boardNo, @PathVariable("page") int page){
		log.info("Controller ==============> getList........."+boardNo);
		Criteria cri=new Criteria(page, 10);
		return new ResponseEntity<ReplyPageDTO>(replyService.getListWithPaging(cri, boardNo), HttpStatus.OK);
	}
	
	//댓글 한개만 조회 
	@GetMapping(value = "/{replyNo}", produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyDTO> read(@PathVariable("replyNo") Long replyNo){
		log.info("Controller ==============> read........."+replyNo);
		return new ResponseEntity<>(replyService.getReply(replyNo),HttpStatus.OK);
		
	}
	
	//댓글 삭제 
	@DeleteMapping(value = "/{replyNo}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> delete(@PathVariable("replyNo") Long replyNo){
		log.info("Controller ==============> delete........."+replyNo);
		boolean result=replyService.deleteReply(replyNo);
		return result==true? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//댓글 수정 
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = {"/{replyNo}"}, consumes = "application/json",
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody ReplyDTO reply, @PathVariable("replyNo") Long replyNo) {
		log.info("Controller ==============> modify........."+replyNo);
		reply.setReplyNo(replyNo);
		log.info(reply);
		return replyService.modifyReply(reply)==true? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	
}