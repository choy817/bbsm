package com.bbsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbsm.domain.BoardDTO;
import com.bbsm.domain.Criteria;
import com.bbsm.domain.PageDTO;
import com.bbsm.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	BoardService boardService;
	
	@GetMapping("/list")
	public void list(Criteria cri,Model model) {
		log.info("Controller ==============> boardList........."+cri);
		log.info(model.addAttribute("list",boardService.getList(cri)));
		model.addAttribute("pageMaker",new PageDTO(boardService.getTotal(cri), cri));
	}
	
	@GetMapping("/write")
	public void wirte() {
		log.info("Controller ==============> boardWrite.........");
		
	}
	
	@GetMapping("/view")												//자동으로 Model에 데이터를 지정한 이름으로 담아줌 
	public void view(@RequestParam("boardNo")long boardNo, Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("Controller ==============> boardView.........");
		model.addAttribute("board",boardService.view(boardNo));
	}
	
	@GetMapping("/modify")
	public void modify(@RequestParam("boardNo")long boardNo, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("Controller ==============> boardModify.........(Get)");
		model.addAttribute("board",boardService.view(boardNo));
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO board,RedirectAttributes rttr, Criteria cri) {
		log.info("Controller ==============> boardModify.........(Post)");
		log.info("board : "+board);
		if(boardService.modifyBoard(board)) {
			log.info("게시글 수정 성공.......");
			rttr.addFlashAttribute("msg", "modSuccess");
		}
		
		return "redirect:/board/list"+cri.getListLink();
	}
	
	@PostMapping("/delete")
	public String delete(long boardNo,RedirectAttributes rttr, Criteria cri) {
		log.info("Controller ==============> boardDelete........."+boardNo);
		if(boardService.deleteBoard(boardNo)) {
			log.info("게시글 삭제 성공.......");
			rttr.addFlashAttribute("msg", "delSuccess");
		}
		
		return "redirect:/board/list"+cri.getListLink();
	}

}
