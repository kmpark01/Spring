package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;

public interface BoardService {
	// 전체 리스트
	public List<BoardVO> getList();
	
	//데이터 삽입
	public void register(BoardVO vo);
	
	// 단일 데이터
	public BoardVO get(int bno);
	
	// 게시글 삭제
	public boolean remove(int bno);
	
	// 게시글 수정
	public boolean modify(BoardVO vo);
	
	// 페이징 기능 추가
	public List<BoardVO> getListWithPaging(Criteria cri);
	public int getTotalRecordCount();
}
