package com.bbsm.domain;

import lombok.Data;

@Data
public class ReplyDTO {
	private Long replyNo;			
	private Long boardNo;			
	private String replyContent;
	private String replyer;			
	private String replyDate;	
	private String modDate;
}
