package com.bbsm.service;

import java.util.List;

import com.bbsm.domain.MapDTO;

public interface MapService {
	//지도 리스트 
	public List<MapDTO> getmapList();
	//지도 추가하기
	public boolean addMap(MapDTO map);
	//지도 상세보기 
	public MapDTO viewMap(Long mapNo);
	//지도 수정하기 
	public boolean modifyMap(MapDTO map);
	//지도 삭제하기 
	public boolean deleteMap(Long mapNo);
	
}