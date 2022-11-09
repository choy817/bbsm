package com.bbsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbsm.domain.Criteria;
import com.bbsm.domain.ReplyDTO;
import com.bbsm.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplySerivce {
	
	@Setter(onMethod_ = @Autowired)
	ReplyMapper replyMapper;

	@Override
	public int registerReply(ReplyDTO reply) {
		return replyMapper.insertReply(reply);
	}

	@Override
	public ReplyDTO getReply(Long replyNo) {
		return replyMapper.getReply(replyNo);
	}

	@Override
	public boolean modifyReply(ReplyDTO reply) {
		return replyMapper.updateReply(reply)==1;
	}

	@Override
	public boolean deleteReply(Long replyNo) {
		return replyMapper.deleteReply(replyNo)==1;
	}

	@Override
	public List<ReplyDTO> getListWithPaging(Criteria cri, Long boardNo) {
		return replyMapper.getListWithPaging(cri, boardNo);
	}
	
	

}
