package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;



public interface BoardMapper {
	// 전체 리스트
	public List<BoardVO> getList();
	// 데이터 삽임
	public int insert(BoardVO vo);
//	// 게시글 상세 페이지
	public BoardVO read(int bno);
//	// 게시글 삭제
	public int delete(int bno);
//	// 게시글 수정 - 제목, 내용, 작성자, 수정 날짜 변경
	public int update(BoardVO vo);
	// 페이징 추가
	public List<BoardVO> getListWithPaging(Criteria cri);
	public int getTotalRecordCount();
}
