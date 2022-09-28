package com.bbsm.domain;

import lombok.Data;

@Data
public class BoardDTO {
	private long boardNo;		
	private String boardTitle;		
	private String boardContent;
	private String boardWriter;
	private long boardView;
	private String boardDate;
	private long boardReco;
}
