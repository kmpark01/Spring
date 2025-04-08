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
public class BoardAttachMapperTests {
	
	@Autowired
	private BoardAttachMapper mapper;
	
//	@Test
//	public void testInsert() {
//		BoardAttachVO vo = new BoardAttachVO();
//		vo.setUuid("테스트 UUID");
//		vo.setUploadPath("테스트 경로");
//		vo.setFileName("테스트 파일 명");
//		vo.setBno(30);
//		mapper.insert(vo);
//	}
	
//	@Test
//	public void testSelect() {
//		int bno = 30;
//		mapper.findByBno(bno);
//	}
	
	@Test
	public void testDelete() {
		String uuid = "테스트 UUID";
		mapper.delete(uuid);
	}
}
