package com.bbsm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbsm.domain.BoardDTO;
import com.bbsm.domain.Criteria;
import com.bbsm.domain.PageDTO;
import com.bbsm.service.BoardService;
import com.google.gson.JsonObject;

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
		log.info("Controller ==============> boardWrite.........(Get)");
	}
	
	@PostMapping
	public String wirte(BoardDTO board) {
		log.info("Controller ==============> boardWrite.........(Post)"+board);
		if(boardService.write(board)){
			return "redirect:/board/list";
		}
		return "/board/write";
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
	
	@ResponseBody
	@PostMapping(value = "/fileUpload")
	public void fileUpload(HttpServletRequest req, HttpServletResponse resp, MultipartHttpServletRequest multiFile) throws IOException {
		log.info("Controller ==============> fileUpload.........");
		JsonObject jsonObject = new JsonObject();
		PrintWriter printWriter = null;
		OutputStream out = null;
		MultipartFile file = multiFile.getFile("upload");
		
		if(file != null) {
			if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
				if(file.getContentType().toLowerCase().startsWith("image/")) {//image인 것만 등록 
					
				    try{
				    	 
			            String fileName = file.getOriginalFilename();
			            byte[] bytes = file.getBytes();
			           
			            String uploadPath = req.getSession().getServletContext().getRealPath("/resources/img/noticeimg"); //저장경로
			            System.out.println("uploadPath:"+uploadPath);

			            File uploadFile = new File(uploadPath);
			            if(!uploadFile.exists()) {
			            	uploadFile.mkdir();
			            }
			            String fileName2 = UUID.randomUUID().toString();
			            uploadPath = uploadPath + "/" + fileName2 +fileName;	//resources/images/noticeimg/uuid랜덤값+파일명 
			            
			            out = new FileOutputStream(new File(uploadPath));
			            out.write(bytes);
			            
			            printWriter = resp.getWriter();
			            String fileUrl = req.getContextPath() + "/resources/images/noticeimg/" +fileName2 +fileName; //url경로
			            System.out.println("fileUrl :" + fileUrl);
			            //json으로 전달 
			            JsonObject json = new JsonObject();
			            json.addProperty("uploaded", 1);
			            json.addProperty("fileName", fileName);
			            json.addProperty("url", fileUrl);
			            printWriter.print(json);
			            System.out.println(json);
			 
			        }catch(IOException e){
			            e.printStackTrace();
			        } finally {
			            if (out != null) {
		                    out.close();
		                }
		                if (printWriter != null) {
		                    printWriter.close();
		                }
			        }
				}
			}
		}
	}
}
