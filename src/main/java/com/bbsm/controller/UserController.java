package com.bbsm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public String register(UserDTO user,RedirectAttributes rttr) {
		log.info("Controller ==============> register(Post).........");
		int cnt=userService.checkId(user.getUserId());
		
		//중복된 아이디가 없으면 
		if(cnt==0) {
			//userPw암호화 후 가입 
			String en_pw=pwdEncoder.encode(user.getUserPw());
			user.setUserPw(en_pw);
			user.setUserPwCheck(en_pw);
			
			if(userService.register(user)) {
				rttr.addFlashAttribute("msg","regSuccess");
				return "redirect:/user/login";
			}
		}
		rttr.addFlashAttribute("msg","regFail");
		return "redirect:/user/register";
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
		rttr.addFlashAttribute("msg","logFail");
		return "redirect:/user/login";
		
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
	
	//회원정보 수정
	@GetMapping("/modifyUser")
	public void userModify() {
		log.info("Controller ==============> userModify(Get)..........");}
	
	@PostMapping("/modifyUser")
	public String userModify(UserDTO user,HttpSession session,RedirectAttributes rttr) {
		log.info("Controller ==============> userModify(Post)..........");
		if(userService.modifyUser(user)) {
			//설정된 세션을 모두 제거
			session.invalidate();
			rttr.addFlashAttribute("msg","modSuccess");
			return "redirect:/user/login";
		}
		rttr.addFlashAttribute("msg","modFail");
		return "redirect:/user/modifyUser";
	}
	
	//회원정보 삭제
	@GetMapping("/deleteUser")
	public void deleteUser() {
		log.info("Controller ==============> userDelete(Get)..........");}
	
	@PostMapping("/deleteUser")
	public String deleteUser(UserDTO user,HttpSession session,RedirectAttributes rttr) {
		log.info("Controller ==============> userDelete(Post)..........");
		//세션 값 가져오기
		UserDTO info=(UserDTO) session.getAttribute("user");
		//세션에 저장되어있는 패스워드
		String sessionPw=info.getUserPw();
		//회원 탈퇴 시 새로 입력한 패스워드
		String deletePw=user.getUserPw();
		//패스워드 비교
		boolean result=pwdEncoder.matches(deletePw, sessionPw);
		//일치
		if(result) {
			userService.deleteUser(user);
			SecurityContextHolder.clearContext();
			rttr.addFlashAttribute("msg","delSuccess");
			return "redirect:/index";
		//불일치	
		}else {
			rttr.addFlashAttribute("msg","delFail");
			return "redirect:/user/userDelete";
		}
		
		
	}
}
