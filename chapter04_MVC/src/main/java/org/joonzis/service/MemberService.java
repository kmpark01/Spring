package org.joonzis.service;

import org.joonzis.domain.MemberVO;

public interface MemberService {
	public int validateId(String userId);
	public int join(MemberVO vo);
	public int auth(MemberVO vo);
}
