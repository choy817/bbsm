package com.bbsm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbsm.domain.BoardDTO;
import com.bbsm.domain.Criteria;
import com.bbsm.domain.FileDTO;
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
	public void write() {
		log.info("Controller ==============> boardWrite.........(Get)");
	}
	
	@PostMapping("/write")
	public String write(BoardDTO board) {
		log.info("Controller ==============> boardWrite.........(Post)"+board);
		
		
		if(boardService.write(board)){
			return "redirect:/board/list";
	}
	return "/board/write";
	}
	
	@GetMapping("/view")												//???????????? Model??? ???????????? ????????? ???????????? ????????? 
	public void view(@RequestParam("boardNo")long boardNo, Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("Controller ==============> boardView.........");
		log.info("????????? ??????.............."+boardService.viewsCnt(boardNo));
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
			log.info("????????? ?????? ??????.......");
			rttr.addFlashAttribute("msg", "modSuccess");
		}
		
		return "redirect:/board/list"+cri.getListLink();
	}
	
	@PostMapping("/delete")
	public String delete(long boardNo,RedirectAttributes rttr, Criteria cri) {
		log.info("Controller ==============> boardDelete........."+boardNo);
		if(boardService.deleteBoard(boardNo)) {
			log.info("????????? ?????? ??????.......");
			rttr.addFlashAttribute("msg", "delSuccess");
		}
		
		return "redirect:/board/list"+cri.getListLink();
	}
	
	@ResponseBody
	@RequestMapping(value = "/recoCnt", method = RequestMethod.POST, produces = "application/json")
	public int recoCnt(@RequestBody long boardNo) {
		log.info("Controller ==============> recoCnt........."+boardNo);
		int recoResult=boardService.recoCnt(boardNo);
		return recoResult;
	}
	
	@ResponseBody
	@PostMapping("/imgUpload")
	public void fileUpload(HttpServletRequest req, HttpServletResponse resp, MultipartHttpServletRequest multiFile) throws IOException {
		log.info("Controller ==============> imgUpload.........");
		//Json ?????? ??????
		JsonObject jsonObject = new JsonObject();
		// Json ????????? ???????????? ?????? PrintWriter ??????
		PrintWriter printWriter = null;
		OutputStream out = null;
		//????????? ???????????? ?????? MultipartHttpServletRequest ??? getFile ????????? ??????
		MultipartFile file = multiFile.getFile("upload");
		
		//????????? ???????????? ??????(?????? ????????? null ??????)
		if(file != null) {
			// ?????? ???????????? 0?????? ??????, ??????????????? ????????? ?????????
			if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
				if(file.getContentType().toLowerCase().startsWith("image/")) {//image??? ?????? ?????? 
					
				    try{
				    	//?????? ?????? ??????
			            String fileName = file.getOriginalFilename();
			            //????????? ????????? ???????????? ??????
			            byte[] bytes = file.getBytes();
			           
			            //????????? ????????? ???????????? ?????? 
			            String uploadPath = req.getSession().getServletContext().getRealPath("/resources/img/noticeimg");
			            //???????????? ?????? ?????? ??????
			            System.out.println("uploadPath:"+uploadPath);

			            File uploadFile = new File(uploadPath);
			            if(!uploadFile.exists()) {
			            	uploadFile.mkdir();
			            }
			          //??????????????? ???????????? ??????
			            String fileName2 = UUID.randomUUID().toString();
			          //????????? ?????? + ??????????????? ??????  ???????????? ????????? ??????
			            uploadPath = uploadPath + "/" + fileName2 +fileName;	//resources/images/noticeimg/uuid?????????+????????? 
			            
			            out = new FileOutputStream(new File(uploadPath));
			            out.write(bytes);
			            
			          //?????????????????? ????????? ??????
			            printWriter = resp.getWriter();
			          //????????? ???????????? Url ?????? ??????
			            String fileUrl = req.getContextPath() + "/resources/img/noticeimg/" +fileName2 +fileName; //url??????
			            System.out.println("fileUrl :" + fileUrl);
			            //json?????? ?????? 
			            JsonObject json = new JsonObject();
			          //????????? json ????????? ????????? ?????? ????????? + ?????? + ????????? CkEditor??? ??????
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
	@ResponseBody
	@PostMapping(value="/fileUpload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileDTO>> fileUpload(MultipartFile[] uploadFile) {
		log.info("Controller ==============> fileUpload.........(Post)");
		List<FileDTO> list=new ArrayList<FileDTO>();
		
		String uploadFolder="/Users/choyeonju/upload/temp";
		String uploadFolderPath=getFolder();
		
		File uploadPath= new File(uploadFolder,uploadFolderPath);
		
		log.info("upload path : "+uploadPath);
		
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			
			FileDTO attach=new FileDTO();
			String uploadFileName=multipartFile.getOriginalFilename();
			
			UUID uuid= UUID.randomUUID();
			uploadFileName=uuid.toString()+"_"+uploadFileName;
			
			try {
				File saveFile=new File(uploadPath,uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attach.setUuid(uuid.toString());
				attach.setUploadPath(uploadFolderPath);
				
				list.add(attach);
			} catch (IllegalStateException | IOException e) {
				log.error(e.getMessage());
			}
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	private String getFolder() {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String str=sdf.format(date);
		return str.replace("-", File.separator);
				
	}
}
