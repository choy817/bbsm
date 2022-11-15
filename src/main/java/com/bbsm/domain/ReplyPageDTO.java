package com.bbsm.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPageDTO {
	//해당 게시글의 전체 댓글 수 
	private int replyCnt;
	//댓글 목록 
	private List<ReplyDTO> list;
}
