package com.bbsm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bbsm.domain.BoardDTO;
import com.bbsm.domain.Criteria;
import com.bbsm.domain.MapDTO;

public interface BoardMapper {
	//목록 가져오기 
	public List<BoardDTO> getList();
	//페이징 처리된 목록 가져오기 
	public List<BoardDTO> getListWithPaging(Criteria cri);
	//전체 데이터 개수 가져오기
	public int getTotal(Criteria cri);
	//게시글 상세보기
	public BoardDTO read(long boardNo);
	//게시물 수정하기
	public boolean modifyBoard(BoardDTO board);
	//게시물 삭제하기
	public boolean deleteBoard(BoardDTO board);
	//게시물 작성하기
	public boolean write(BoardDTO board);
	//게시물 조회수 증가 
	public boolean viewsCnt(long boardNo);
	//게시물 추천수 증가	
	public int updateReco(long boardNo);
	//게시물 추천수 가져오기
	public int getReco(long boardNo);
	//지도 목록 가져오기 
	public List<MapDTO> getMapList();
}
