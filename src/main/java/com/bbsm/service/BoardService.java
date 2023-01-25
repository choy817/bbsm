package com.bbsm.service;

import java.util.List;

import com.bbsm.domain.BoardDTO;
import com.bbsm.domain.Criteria;
import com.bbsm.domain.MapDTO;

public interface BoardService {
	//리스트 가져오기 
	public List<BoardDTO> getList();
	//페이징 처리된 목록 가져오기 
	public List<BoardDTO> getList(Criteria cri);
	//전체 데이터 개수 구하기
	public int getTotal(Criteria cri);
	//게시글 상세보기
	public BoardDTO view(long boardNo);
	//게시글 수정
	public boolean modifyBoard(BoardDTO board);
	//게시글 삭제
	public boolean deleteBoard(long boardNo);
	//게시글 작성 
	public boolean write(BoardDTO board);
	//게시글 조회수 증가
	public boolean viewsCnt(long boardNo);
	//게시글 추천수 증가	
	public int recoCnt(long boardNo);
	//게시글 추천수 가져오기	
	public int getReco(long boardNo);
	//지도 리스트 가져오기 
	public List<MapDTO> getMapList();
}
