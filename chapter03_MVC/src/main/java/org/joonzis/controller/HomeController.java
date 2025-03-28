package org.joonzis.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.joonzis.dto.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	// @@RequestMapping : url-mapping
	// 메소드를 대상으로 어노테이션을 붙인다
	// value = "/" : 컨텍스트 패스를 의미, 서버:포트/디폴트패키지
	// method=RequestMethod.GET : get/post 방식
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "member/input";
	}
	
	// @RequestParam("id") String id :
	// 리퀘스트를 통한 파라미터를 받아와서 문자열 변수에 저장하는 과정
	// Model model : 데이터를 저장하여 이동하기 위한 객체
	// request.setAttribute == model.addAttribute
	@RequestMapping(value ="member/result", method=RequestMethod.POST)
	public String login(@RequestParam("id") String id,
						@RequestParam("pw") String pw,
						Model model) {
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "member/output";
	}
	
	// 주소에 http:localhost:8080/a/b/c/d/e 입력 시 view01.jsp로 이동
	@RequestMapping(value="/a/b/c/d/e") // value 생략 가능
	public String goView01() {
		// 1. 리턴 타입 : 뷰(view)를 리턴하기 때문에 언제나 String 리턴
		// 2. 메소드명 : goView01은 아무런 의미가 없다,
		// (메소드 끼리 이름만 다르면 된다
		// 3. 리턴 : "/View01", "View01"의 차이점은 없다.
		return "/view01";
	}
	
	@RequestMapping("admin/view02")
	public String goView02(Model model) {
		
		/* Model 클래스
		 *  1. request의 attribute 역할을 수행하는 클래스
		 *  2. addAttribute("속성값", "값") 방식으로 Attribute 저장
		 *  3. controller가 jsp에게 데이터를 전달하려면 무조건 model 사용
		 *  4. Model model을 매개 변수로 선언하여 사용
		 */
		
		model.addAttribute("id", "admin");
		model.addAttribute("pw", "1234");
		return "admin/view02";
	}
	
	@RequestMapping("index")
	public void goIndex(Model model) {
		// 요청하는 urlMapping과 도착하는 jsp의 경로가 같다면
		// 메소드의 리턴 타입을 무시하고 void로 처리해도 된다.
		model.addAttribute("id", "admin");
		model.addAttribute("pw", "1234");
	}
	
	@RequestMapping(value="v01", method=RequestMethod.POST)
	public String goResult1(Model model, StudentDto sDto) {
		model.addAttribute("sDto", sDto);
		return "result";
	}
	
	@RequestMapping(value="v02", method=RequestMethod.POST)
	public String goResult2(@ModelAttribute("s") StudentDto sDto) {
		return "result";
	}
}
