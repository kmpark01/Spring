package org.joonzis.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.domain.BoardVO;
import org.joonzis.domain.Criteria;
import org.joonzis.domain.PageDTO;
import org.joonzis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
//		File file = null;
//		try {
//			file = new File("C:\\upload\\" + URLDecoder.decode(fileName, "utf-8"));
//			file.delete();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
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
	@PreAuthorize("isAuthenticated()") // 인증된 사용자면 true
	@PostMapping("/register")
	public String register(BoardVO vo) {
	    String tempBase = "C:\\upload\\temp";
	    String finalBase = "C:\\upload";

	    List<BoardAttachVO> finalAttachList = new ArrayList<>();

	    if (vo.getAttachList() != null) {
	        for (BoardAttachVO attach : vo.getAttachList()) {
	            Path tempPath = Paths.get(tempBase, attach.getUploadPath(), attach.getUuid() + "_" + attach.getFileName());
	            Path finalPath = Paths.get(finalBase, attach.getUploadPath(), attach.getUuid() + "_" + attach.getFileName());

	            try {
	                Files.createDirectories(finalPath.getParent());
	                Files.move(tempPath, finalPath, StandardCopyOption.REPLACE_EXISTING);

	                finalAttachList.add(attach);
	            } catch (IOException e) {
	                e.printStackTrace(); // 로그 처리 적절히
	            }
	        }
	    }

	    vo.setAttachList(finalAttachList);
	    service.register(vo);

	    return "redirect:/board/list";
	}
	
	// 게시글 등록
	@PreAuthorize("isAuthenticated()") // 인증된 사용자면 true
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
	public String modify(BoardVO vo, Criteria cri, RedirectAttributes rttr, String fileName) {
		String tempBase = "C:\\upload\\temp";
	    String finalBase = "C:\\upload";
	    
	    List<BoardAttachVO> finalAttachList = new ArrayList<>();
		
		log.info("modify..." + vo);
		if(service.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
		 if (vo.getAttachList() != null) {
		        for (BoardAttachVO attach : vo.getAttachList()) {
		            Path tempPath = Paths.get(tempBase, attach.getUploadPath(), attach.getUuid() + "_" + attach.getFileName());
		            Path finalPath = Paths.get(finalBase, attach.getUploadPath(), attach.getUuid() + "_" + attach.getFileName());

		            try {
		                Files.createDirectories(finalPath.getParent());
		                Files.move(tempPath, finalPath, StandardCopyOption.REPLACE_EXISTING);

		                finalAttachList.add(attach);
		            } catch (IOException e) {
		                e.printStackTrace(); // 로그 처리 적절히
		            }
		        }
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
	
	@ResponseBody
	@GetMapping(value="/getAttachList/{bno}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BoardAttachVO>> getAttachList(@PathVariable("bno") int bno){
		log.info("getAttahcList..." + bno);
		return new ResponseEntity<List<BoardAttachVO>>(service.getAttachLists(bno), HttpStatus.OK);
	}
	
	// 오늘 날짜의 경로를 문자열로 생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
}
