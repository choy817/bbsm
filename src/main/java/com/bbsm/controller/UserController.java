package com.bbsm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbsm.domain.UserDTO;
import com.bbsm.service.UserService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/user/*")
public class UserController {
	
	@Setter(onMethod_ = @Autowired)
	UserService userService;
	
	@Setter(onMethod_ = @Autowired )
	BCryptPasswordEncoder pwdEncoder;
	
	//회원가입 
	@GetMapping("/register")
	public void register() {log.info("Controller ==============> register(Get).............");}
	
	@PostMapping("/register")
	public String register(UserDTO user) {
		log.info("Controller ==============> register(Post).........");
		int cnt=userService.checkId(user.getUserId());
		
		//중복된 아이디가 없으면 
		if(cnt==0) {
			//userPw암호화 후 가입 
			String en_pw=pwdEncoder.encode(user.getUserPw());
			user.setUserPw(en_pw);
			user.setUserPwCheck(en_pw);
			userService.register(user);
			return "/user/login";
		}
		return "/user/register";
	}
	//로그인
	@GetMapping("/login")
	public void login() {log.info("Controller ==============> login(Get)..........");}
	
	@PostMapping("/login")
	public String login(UserDTO user,HttpSession session,RedirectAttributes rttr) {
		log.info("Controller ==============> login(post).........");
		
		UserDTO login=userService.selectUser(user);
		
		boolean pwMatch=pwdEncoder.matches(user.getUserPw(), login.getUserPw());
		
		if(login!=null && pwMatch==true) {
			log.info("정보 일치 ");
			session.setAttribute("user", login);
			log.info("session user : " + login);
			return "redirect:/index";
		
		}else {
			log.info("정보 불일치 ");
			session.setAttribute("user", null);
		}
		
		return "/user/login";
		
	}
	
	//아이디 검사
	@ResponseBody
	@RequestMapping(value = "/checkId", method=RequestMethod.POST, 
	produces="application/json")
	public Map<Object, Object> checkId(@RequestBody String userId) {
		log.info("Controller ==============> checkId.............");
		Map<Object, Object> map=new HashMap<Object, Object>();
		int result=0;
		//아이디 검색 결과 
		result=userService.checkId(userId);
		log.info(userId);
		map.put("check",result);
		log.info("map : " +map.put("check",result));
		return map;
		
	}
}
