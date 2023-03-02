package com.bbsm.domain;

import lombok.Data;

@Data
public class MapDTO {
	private Long mapNo;
	private String storeName;
	private String latitude;//위도 
	private String longitude;//경도
	private String address;
	private String tel;
	private String description;
	
}
