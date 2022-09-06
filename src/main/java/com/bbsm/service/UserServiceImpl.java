package com.bbsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bbsm.domain.UserDTO;
import com.bbsm.mapper.UserMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//컨트롤러 -> 서비스 호출 -> DAO 호출 -> Mapper -> DB 

@Service
@Log4j
public class UserServiceImpl implements UserService {
	
	@Setter(onMethod_ =@Autowired)
	UserMapper userMapper;
	@Setter(onMethod_ =@Autowired)
	BCryptPasswordEncoder pwdEncoder;

	@Override
	public int checkId(String userId) {
		
		log.info("service ==============> "+userId);
		
		return userMapper.checkId(userId);
	} 

	@Override
	public boolean register(UserDTO user) {
		boolean regResult=userMapper.register(user);
		
		if(regResult) {
			log.info("Service ==============> register...........");
		}
		return regResult;
	}

	@Override
	public UserDTO selectUser(UserDTO user) {
		log.info("Service ==============> userSelect............");
		return userMapper.selectUser(user);
	}

	@Override
	public boolean modifyUser(UserDTO user) {
		log.info("Service ==============> modifyUser............");
		String enPw=pwdEncoder.encode(user.getUserPw());
		user.setUserPw(enPw);
		
		return userMapper.modifyUser(user);
		
	}

	@Override
	public boolean deleteUser(UserDTO user) {
		log.info("Service ==============> deleteUser............");
		return userMapper.deleteUser(user)==true;
	}

	

}
