package com.bbsm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbsm.domain.NaverLoginBO;
import com.bbsm.domain.UserDTO;
import com.bbsm.service.UserService;
import com.github.scribejava.core.model.OAuth2AccessToken;

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
	
	@Setter(onMethod_ = @Autowired)
	NaverLoginBO naverLoginBo;
	
	String apiResult=null;
	
	
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
	public String login(UserDTO user,HttpSession session,RedirectAttributes rttr,Model model) {
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
		
		return "/user/login";
		
	}
	//naver 로그인 첫 화면 요청 
	@ResponseBody
	@RequestMapping(value = "/naverLogin", method = {RequestMethod.GET, RequestMethod.POST})
	public String naverLogin(Model model, HttpSession session) {
		log.info("Controller ==============> NaverLogin.........");
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */		
		String naverAuthUrl = naverLoginBo.getAuthorizationUrl(session);				
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&		
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125		
		log.info("네이버:" + naverAuthUrl);				
		//네이버 		
		model.addAttribute("naverUrl", naverAuthUrl);
		/* 생성한 인증 URL을 View로 전달 */
		return naverAuthUrl;
	}
	
	//naver 로그인 성공시 callback 호출 
	@RequestMapping(value = "/callback", method = {RequestMethod.GET, RequestMethod.POST})
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, RedirectAttributes rttr) throws IOException, ParseException{
		log.info("Controller ==============> NaverLoginCallback.........");		
		OAuth2AccessToken oauthToken;        
		oauthToken = naverLoginBo.getAccessToken(session, code, state);         
		//1. 로그인 사용자 정보를 읽어온다.		
		apiResult = naverLoginBo.getUserProfile(oauthToken);  
		log.info(apiResult);
		//String형식의 json데이터				
		/** apiResult json 구조		
		 * {"resultcode":"00",		 
		 * "message":"success",		 
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}		**/
		//2. String형식인 apiResult를 json형태로 바꿈		
		JSONParser parser = new JSONParser();		
		Object obj = parser.parse(apiResult);		
		JSONObject jsonObj = (JSONObject) obj;				
		//3. 데이터 파싱 		
		//Top레벨 단계 _response 파싱		
		JSONObject response_obj = (JSONObject)jsonObj.get("response");		
		//response의 nickname값 파싱		
		String nickname = (String)response_obj.get("nickname"); 		
		//log.info(nickname);				
		//4.파싱 닉네임 세션으로 저장		
		session.setAttribute("sessionId",nickname); //세션 생성				
//		model.addAttribute("result", apiResult);	
		rttr.addFlashAttribute("msg", "NLoginSuccess");
		
		return "redirect:/index";
		
		
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
