package com.bbsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		log.info("Controller : boardWrite");
		
	}
	
	@GetMapping("/view")
	public void view(@RequestParam("boardNo")long boardNo, Model model) {
		log.info("Controller ==============> boardView.........");
		model.addAttribute("board",boardService.view(boardNo));
	}

}
