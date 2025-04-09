package org.joonzis.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.mapper.BoardAttachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class UploadController {
	
	@Autowired
	private BoardAttachMapper attachMapper;
	
	@GetMapping("/uploadForm")
	public String uploadForm() {
		log.info("upload form");
		return "uploadForm";
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		for(MultipartFile multipartFile : uploadFile) {			
			log.info("-----------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
		}
	}
	
	@GetMapping("/uploadAsync")
	public String uploadAsync() {
		log.info("uploadAsync");
		return "uploadAsync";
	}
	
	@ResponseBody
	@PostMapping(value = "/uploadAsyncAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BoardAttachVO>> uploadAsyncAction(MultipartFile[] uploadFile) {
		log.info("upload async post...");
		
		List<BoardAttachVO> list = new ArrayList<BoardAttachVO>();
		
		String uploadFolder = "C:\\upload\\temp";
		
		// 폴더 만들어주기
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("uploadPath : " + uploadPath);
		
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			// 파일 정보를 담을 AttachDTO 객체 생성
			BoardAttachVO attachDTO = new BoardAttachVO();
			
			log.info("-----------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			log.info("only file name : " + uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setFileName(multipartFile.getOriginalFilename());
				attachDTO.setUploadPath(getFolder());
				
				list.add(attachDTO);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return new ResponseEntity<List<BoardAttachVO>>(list, HttpStatus.OK);
	}
	
	// 파일 다운로드 메소드
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		log.info("download file..." + fileName);
		
		Resource resource = new FileSystemResource("C:\\upload\\" + fileName);
		log.info("resource..." + resource);
		
		String resourceName = fileName.substring(fileName.indexOf("_") + 1);
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attach; fileName=" + new String(resourceName.getBytes("utf-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	// uuid 추출
	private String extractUUID(String fileName) {
		try {
			fileName = URLDecoder.decode(fileName, "utf-8");
			int sIdx = fileName.indexOf("s_");
			if (sIdx != -1) {
				int uuidStart = sIdx + 2;
				int uuidEnd = fileName.indexOf("_", uuidStart);
				return fileName.substring(uuidStart, uuidEnd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 파일 삭제
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(@RequestBody List<String> fileNames) {
	    log.info("deleteFile..." + fileNames);

	    for (String fileName : fileNames) {
	        try {
	            String uuid = extractUUID(fileName);
	            String decodedName = URLDecoder.decode(fileName, "utf-8");
	            File file;

	            if (uuid != null) {
	                file = new File("C:\\upload\\" + decodedName);
	                attachMapper.delete(uuid);
	            } else {
	                file = new File("C:\\upload\\temp\\" + decodedName);
	            }

	            if (file.exists()) {
	                file.delete();
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    return new ResponseEntity<>("deleted", HttpStatus.OK);
	}
	

	// 오늘 날짜의 경로를 문자열로 생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
}
