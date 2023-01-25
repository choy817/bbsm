package com.bbsm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbsm.domain.BoardDTO;
import com.bbsm.domain.Criteria;
import com.bbsm.domain.MapDTO;
import com.bbsm.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_ = @Autowired)
	BoardMapper boardMapper;
	
	@Override
	//목록 가져오기 
	public List<BoardDTO> getList() {
		return boardMapper.getList();
	}

	@Override
	//페이징 처리된 목록 가져오기 
	public List<BoardDTO> getList(Criteria cri) {
		log.info("criteria : "+cri);
		return boardMapper.getListWithPaging(cri);
	}

	@Override
	//전체 데이터 개수 구하기
	public int getTotal(Criteria cri) {
		return boardMapper.getTotal(cri);
	}
	
	@Override
	//게시글 상세보기
	public BoardDTO view(long boardNo) {
		return boardMapper.read(boardNo);
	}

	@Override
	//게시글 수정 
	public boolean modifyBoard(BoardDTO board) {
		return boardMapper.modifyBoard(board);
	}

	@Override
	//게시글 삭제
	public boolean deleteBoard(long boardNo) {
		return boardMapper.deleteBoard(boardNo);
	}

	@Override
	//게시글 작성 
	public boolean write(BoardDTO board) {
		return boardMapper.write(board);
	}
	
	@Override
	public boolean viewsCnt(long boardNo) {
		return boardMapper.viewsCnt(boardNo);
	}
	@Override
	public int recoCnt(long boardNo) {
		return boardMapper.updateReco(boardNo);
	}
	@Override
	public int getReco(long boardNo) {
		return boardMapper.getReco(boardNo);
	}
	@Override
	public List<MapDTO> getMapList() {
		return boardMapper.getMapList();
	}
}
