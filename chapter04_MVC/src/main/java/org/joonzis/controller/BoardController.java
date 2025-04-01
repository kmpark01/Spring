package org.joonzis.controller;

import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.domain.PageDTO;
import org.joonzis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// 게시글 전체 조회
	@GetMapping("/list" )
	public String list(Model model, Criteria cri) {
		log.info("list...");
		
		int pageNum = cri.getPageNum();
		int amount = cri.getAmount();
		
		cri = new Criteria(pageNum, amount);
		
		int total = service.getTotalRecordCount();
		PageDTO pdto = new PageDTO(cri, total);
		
		model.addAttribute("list", service.getListWithPaging(cri));
		model.addAttribute("pageMaker", pdto);

		System.out.println(total);
		return "/board/list";
	}
	
	// 게시글 등록
	@PostMapping("/register")
	public String register(BoardVO vo) {
		log.info("register..." + vo);
		service.register(vo);
		return "redirect:/board/list";
	}
	
	// 게시글 등록
	@GetMapping("/register")
	public void register2() {
		log.info("register...");
	}
	
	// 게시글 조회
	@GetMapping("/get")
	public String get(@RequestParam("bno") int bno, Model model) {
		log.info("get..." + bno);
		model.addAttribute("vo", service.get(bno));
		
		return "/board/get";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("bno") int bno, Model model) {
		log.info("modify..." + bno);
		model.addAttribute("vo", service.get(bno));
		
		return "/board/modify";
	}
	
	// 게시글 수정
	@PostMapping("/modify")
	public String modify(BoardVO vo, Criteria cri, RedirectAttributes rttr) {
		
		log.info("modify..." + vo);
		if(service.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list?pageNum=" + cri.getPageNum() + "&amount=" + cri.getAmount();
	}
	
	// 게시글 삭제
	@PostMapping("/remove")
	public String remove(Criteria cri, int bno) {
		log.info("remove..." + bno);
		service.remove(bno);
		return "redirect:/board/list?pageNum=" + cri.getPageNum() + "&amount=" + cri.getAmount();
	}
}
