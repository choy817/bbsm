package com.bbsm.service;

import com.bbsm.domain.UserDTO;

public interface UserService {
	//아이디 검사 
	public int checkId(String userId);
	//회원가입 
	public boolean register(UserDTO user);
	//로그인
	public UserDTO selectUser(UserDTO user);
}
