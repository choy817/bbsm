package com.bbsm.domain;

import lombok.Data;

@Data
public class MapDTO {
	private Long mapNo;
	private String storeName;
	private String longitude;//경도
	private String latitude;//위도 
	
}
