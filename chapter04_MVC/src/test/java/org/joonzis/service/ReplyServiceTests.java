package org.joonzis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyServiceTests {
	
	@Autowired
	private ReplyService service;
	
//	@Test
//	public void testInsert() {
//		ReplyVO vo = new ReplyVO();
//		vo.setBno(30);
//		vo.setReply("테스트 댓글");
//		vo.setReplyer("테스트 유저");
//		service.insert(vo);
//	}
	
//	@Test
//	public void testGetList() {
//		int bno = 30;
//		service.getList(bno);
//	}
	
//	@Test
//	public void testRead() {
//		int rno = 2;
//		service.read(rno);
//	}
	
//	@Test
//	public void testUpdate() {
//		ReplyVO vo = new ReplyVO();
//		vo.setReply("수정댓글");
//		vo.setReplyer("수정 작성자");
//		vo.setRno(2);
//		service.update(vo);
//	}
	
	@Test
	public void testDelete() {
		int rno = 2;
		service.delete(rno);
	}
}
