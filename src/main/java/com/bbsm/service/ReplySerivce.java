package com.bbsm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bbsm.domain.Criteria;
import com.bbsm.domain.ReplyDTO;

public interface ReplySerivce {
	//댓글 삽입 
	public int registerReply(ReplyDTO reply);
	//댓글 조회 
	public ReplyDTO getReply(Long replyNo);
	//댓글 수정 
	public boolean modifyReply(ReplyDTO reply);
	//댓글 삭제
	public boolean deleteReply(Long replyNo);
	//댓글 목록 
	public List<ReplyDTO> getListWithPaging(@Param("cri") Criteria cri, @Param("boardNo") Long boardNo);
	

}
