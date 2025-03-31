package org.joonzis.service;


import org.joonzis.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
//	@Test
//	public void testExist() {
//		log.info(service);
//		assertNotNull(service);
//	}
	
//	@Test
//	public void testGetList() {
//		service.getList();
//	}
	
//	@Test
//	public void testRegister() {
//		BoardVO vo = new BoardVO();
//		vo.setTitle("테스트제목7");
//		vo.setContent("테스트내용7");
//		vo.setWriter("user07");
//		service.register(vo);
//	}
	
//	@Test
//	public void testGet() {
//		int bno = 1;
//		service.get(bno);
//		log.info(bno);
//	}
	
//	@Test
//	public void testRemove() {
//		int bno = 5;
//		service.remove(bno);
//		log.info(bno);
//	}
	
	@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		vo.setBno(5);
		vo.setTitle("수정");
		vo.setContent("수정내용");
		vo.setWriter("수정 작성자");
		service.modify(vo);
		log.info(vo);
	}
}
