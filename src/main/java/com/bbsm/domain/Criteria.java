package com.bbsm.domain;

import org.springframework.web.util.UriComponentsBuilder;

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
	
	// 여러개의 파라미터들을 연결해서 URL의 형태로 만들어주는 기능 
	public String getListLink() {
		UriComponentsBuilder builder=UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount);
		
		return builder.toUriString();
	}
}
