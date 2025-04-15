package org.joonzis.mapper;

<<<<<<< HEAD
=======
import org.joonzis.domain.AuthVO;
>>>>>>> 443ad65 (추가)
import org.joonzis.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userId);
<<<<<<< HEAD
=======
	public int validateId(String userId);
	public int join(MemberVO vo);
	public int auth(AuthVO vo);
>>>>>>> 443ad65 (추가)
}
