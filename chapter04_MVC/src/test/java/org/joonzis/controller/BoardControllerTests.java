package org.joonzis.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		{
			"file:src/main/webapp/WEB-INF/spring/root-context.xml",
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
		}
		
		)
public class BoardControllerTests {
	 @Autowired
	 private WebApplicationContext ctx;
	 
	 // 가짜 mvc - 가상으로 URL과 파라미터 등을 마치 브라우저에서 사용하는 것처럼 사용하기 위한 객체
	 
	 private MockMvc mockMvc;
	 
	 // @Before - 모든 테스트 전에 매번 실행
	 @Before
	 public void setup() {
		 this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	 }
	 
	 @Test
	 public void testList() throws Exception{
		 log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				 .andReturn()
				 .getModelAndView()
				 .getModelMap()
				 );
	 }
}
