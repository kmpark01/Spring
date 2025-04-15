package org.joonzis.controller;

import org.joonzis.domain.MemberVO;
import org.joonzis.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/checkId/{mId}")
	@ResponseBody
	public int checkId(@PathVariable("mId") String userId) {
		log.info("checkId : " + userId);
		int count = service.validateId(userId);
		log.info("count : " + count);
		service.validateId(userId);
		return count;
	}
	
	@ResponseBody
	@PostMapping(value = "/join", produces = MediaType.APPLICATION_JSON_VALUE)
	public int join(@RequestBody MemberVO vo) {
		log.info("join : " + vo);
		int result = service.join(vo);
		log.info(result);
		service.auth(vo);
		return result;
	}
}
