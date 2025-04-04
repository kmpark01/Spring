package org.joonzis.service;

import java.util.List;

import org.joonzis.domain.ReplyVO;
import org.joonzis.mapper.BoardMapper;
import org.joonzis.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardmapper;
	
	@Transactional
	@Override
	public int insert(ReplyVO vo) {
		log.info("insert..." + vo);
		boardmapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}

	@Override
	public List<ReplyVO> getList(int bno) {	
		log.info("getList..." + bno);
		return mapper.getList(bno);
	}

	@Override
	public ReplyVO read(int rno) {
		log.info("read..." + rno);
		return mapper.read(rno);
	}
	
	@Transactional
	@Override
	public int delete(int rno) {
		log.info("delete..." + rno);
		ReplyVO vo = mapper.read(rno);
		boardmapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public int update(ReplyVO vo) {
		log.info("update..." + vo);
		return mapper.update(vo);
	}

}
