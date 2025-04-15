package org.joonzis.service;

import org.joonzis.domain.AuthVO;
import org.joonzis.domain.MemberVO;
import org.joonzis.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper mapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public int validateId(String userId) {
		return mapper.validateId(userId);
	}

	@Override
	public int join(MemberVO vo) {
		vo.setUserPw(passwordEncoder.encode(vo.getUserPw())); // 비밀번호 암호화
		return mapper.join(vo);
	}

	@Override
	public int auth(MemberVO vo) {
		AuthVO authVO = new AuthVO();
		authVO.setUserId(vo.getUserId());
		return mapper.auth(authVO);
	}	
}
