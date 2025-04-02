package org.joonzis.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTests {
	
	@Autowired
	private ReplyMapper mapper;
	
//	@Test
//	public void testInsert() {
//		ReplyVO vo = new ReplyVO();
//		vo.setBno(30);
//		vo.setReply("테스트 댓글");
//		vo.setReplyer("테스트유저");
//		int result = mapper.insert(vo);
//		log.info(result);
//	}
	
//	@Test
//	public void testGetList() {
//		int bno = 30;
//		List<ReplyVO> list = mapper.getList(bno);
//		for(ReplyVO vo : list) {
//			log.info(vo);
//		}
//	}
	
//	@Test
//	public void testRead() {
//		int rno = 1;
//		ReplyVO vo = mapper.read(rno);
//		log.info(vo);
//	}
	
	@Test
	public void testDelete() {
		int rno = 1;
		int result = mapper.delete(rno);
		log.info(result);
	}
	
//	@Test
//	public void testUpdate() {
//		ReplyVO vo = new ReplyVO();
//		vo.setRno(1);
//		vo.setReply("수정 댓글");
//		vo.setReplyer("수정 댓글 작성자");
//		mapper.update(vo);
//	}
	
}
