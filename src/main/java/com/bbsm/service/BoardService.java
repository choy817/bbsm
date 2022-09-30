package com.bbsm.service;

import java.util.List;

import com.bbsm.domain.BoardDTO;
import com.bbsm.domain.Criteria;

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
}
