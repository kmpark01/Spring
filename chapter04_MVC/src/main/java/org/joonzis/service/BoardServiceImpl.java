package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.mapper.BoardAttachMapper;
import org.joonzis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private BoardAttachMapper attachMapper;

	@Override
	public List<BoardVO> getList() {
		log.info("getList...");
		return mapper.getList();
	}
	
	@Transactional
	@Override
	public void register(BoardVO vo) {
		log.info("getList..." + vo);
		
		// 1. tbl_board 테이블에 게시글 등록
		mapper.insert(vo);
		
		// 2. 등록된 게시글 번호 가져오기
		int currentBno =  vo.getBno();
		
		// 3. 첨부 파일이 존재하면, 파일 테이블에 데이터 등
		if(vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			vo.getAttachList().forEach(attach -> {
				attach.setBno(currentBno);
				attachMapper.insert(attach);
			});
		}
		
	}

	@Override
	public BoardVO get(int bno) {
		log.info("get..." + bno);
		return mapper.read(bno);
	}

	@Override
	public boolean remove(int bno) {
		log.info("remove..." + bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public boolean modify(BoardVO vo) {
		log.info("modify..." + vo);
		
		int result = mapper.update(vo);
		
		int currentBno = vo.getBno();
		
		// 3. 첨부 파일이 존재하면, 파일 테이블에 데이터 등
		if(vo.getAttachList() != null && vo.getAttachList().size() > 0) {
			vo.getAttachList().forEach(attach -> {
				attach.setBno(currentBno);
				
				if(attachMapper.getUuid(attach.getUuid()) == null) {
					attachMapper.insert(attach);					
				}
			});
		}
		if(result == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotalRecordCount() {
		return mapper.getTotalRecordCount();
	}

	@Override
	public List<BoardAttachVO> getAttachLists(int bno) {
		log.info("getAttachList..." + bno);
		return attachMapper.findByBno(bno);
	}
	

}
