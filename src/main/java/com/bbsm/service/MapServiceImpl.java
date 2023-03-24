package com.bbsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbsm.domain.MapDTO;
import com.bbsm.mapper.MapMapper;

import lombok.Setter;

@Service
public class MapServiceImpl implements MapService {
	
	@Setter(onMethod_ = @Autowired)
	MapMapper mapMapper;
	
	@Override
	public List<MapDTO> getmapList() {
		return mapMapper.getMapList();
	}
	@Override
	public boolean addMap(MapDTO map) {
		return mapMapper.addMap(map)==1;
	}
	@Override
	public MapDTO viewMap(Long mapNo) {
		return mapMapper.read(mapNo);
	}
	@Override
	public boolean modifyMap(MapDTO map) {
		return mapMapper.modifyMap(map)==1;
	}
	@Override
	public boolean deleteMap(Long mapNo) {
		return mapMapper.deleteMap(mapNo)==1;
	}
}

	
	
	
