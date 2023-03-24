package com.bbsm.mapper;

import java.util.List;

import com.bbsm.domain.MapDTO;

public interface MapMapper {
	//지도 리스트 가져오기 
	public List<MapDTO> getMapList();
	//지도 추가하기 
	public int addMap(MapDTO map);
	//지도 상세보기 
	public MapDTO read(Long mapNo);
	//지도 수정하기
	public int modifyMap(MapDTO map);
	//지도 삭제하기 
	public int deleteMap(Long mapNo);
}
