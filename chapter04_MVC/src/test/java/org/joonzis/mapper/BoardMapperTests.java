package org.joonzis.mapper;

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
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//		List<BoardVO> list = mapper.getList();
//		for(BoardVO vo : list) {
//			log.info(vo);
//		}
//	}
	
//	@Test
//	public void testInsert() {
//		BoardVO vo = new BoardVO();
//		vo.setTitle("테스트제목5");
//		vo.setContent("테스트내용5");
//		vo.setWriter("user05");
//		int result = mapper.insert(vo);
//		log.info(result);
//	}
	
//	@Test
//	public void testRead() {
//		BoardVO vo = new BoardVO();
//		vo.setBno(6);
//		mapper.read(vo);
//	}
	
//	@Test
//	public void testDelete() {
//		BoardVO vo = new BoardVO();
//		vo.setBno(6);
//		mapper.delete(vo);
//	}
	
	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(5);
		vo.setTitle("테스트제목6");
		vo.setContent("테스트내용6");
		vo.setWriter("user06");
		mapper.update(vo);
	}
}
