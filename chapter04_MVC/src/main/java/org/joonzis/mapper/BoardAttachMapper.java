package org.joonzis.mapper;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;

public interface BoardAttachMapper {
	// 파일 업로드
	public void insert(BoardAttachVO vo);
	// 파일 삭제
	public void delete(String uuid);
	// 파일 목록
	public List<BoardAttachVO> findByBno(int bno);
	
	public BoardAttachVO getUuid(String uuid);

}
