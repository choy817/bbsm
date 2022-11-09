package com.bbsm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bbsm.domain.Criteria;
import com.bbsm.domain.ReplyDTO;

public interface ReplyMapper {
	//댓글 삽입 
	public int insertReply(ReplyDTO reply);
	//댓글 조회 
	public ReplyDTO getReply(Long replyNo);
	//댓글 수정 
	public int updateReply(ReplyDTO reply);
	//댓글 삭제
	public int deleteReply(Long replyNo);
	//댓글 목록 
	public List<ReplyDTO> getListWithPaging(@Param("cri") Criteria cri, @Param("boardNo") Long boardNo);
	
}
