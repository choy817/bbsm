package com.bbsm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Criteria {
	int pageNum;	//페이지번호 
	int amount;		//게시글 데이터 수 
	
	public Criteria() {
		this(1,10);	//기본값 1페이지 , 게시물 10개로 지정 
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum=pageNum;
		this.amount=amount;
	}
}
