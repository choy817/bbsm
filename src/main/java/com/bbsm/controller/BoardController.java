package com.bbsm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import com.bbsm.domain.MapDTO;
import com.bbsm.domain.PageDTO;
import com.bbsm.service.BoardService;
import com.google.gson.Gson;
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
	
	@GetMapping("/view")												//자동으로 Model에 데이터를 지정한 이름으로 담아줌 
	public void view(@RequestParam("boardNo")long boardNo, Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("Controller ==============> boardView.........");
		log.info("조회수 증가.............."+boardService.viewsCnt(boardNo));
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
	
	@GetMapping("/map")
	public void map(Model model) {
		log.info("Controller ==============> boardMap");
		List<MapDTO> mapList=new ArrayList<MapDTO>();
		mapList=boardService.getMapList();
//		JSONArray array=new JSONArray();
		String array=new Gson().toJson(mapList);
//		for (int i = 0; i < mapList.size(); i++) {
//			JSONObject json=new JSONObject();
//			json.put("mapList", mapList);
//		}
//		log.info("mapList : " +mapList );
//		array.add(mapList);
		log.info("array : "+array);
		model.addAttribute("map", array);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/recoCnt", method = RequestMethod.POST, produces = "application/json")
	public int recoCnt(@RequestBody long boardNo) {
		log.info("Controller ==============> recoCnt........."+boardNo);
		int recoResult=boardService.recoCnt(boardNo);
		if(recoResult!=0) {
			log.info("추천성공~");
		}
		return recoResult;
	}
	
	@ResponseBody
	@PostMapping("/imgUpload")
	public void fileUpload(HttpServletRequest req, HttpServletResponse resp, MultipartHttpServletRequest multiFile) throws IOException {
		log.info("Controller ==============> imgUpload.........");
		//Json 객체 생성
		JsonObject jsonObject = new JsonObject();
		// Json 객체를 출력하기 위해 PrintWriter 생성
		PrintWriter printWriter = null;
		OutputStream out = null;
		//파일을 가져오기 위해 MultipartHttpServletRequest 의 getFile 메서드 사용
		MultipartFile file = multiFile.getFile("upload");
		
		//파일이 비어있지 않고(비어 있다면 null 반환)
		if(file != null) {
			// 파일 사이즈가 0보다 크고, 파일이름이 공백이 아닐때
			if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
				if(file.getContentType().toLowerCase().startsWith("image/")) {//image인 것만 등록 
					
				    try{
				    	//파일 이름 설정
			            String fileName = file.getOriginalFilename();
			            //파일을 바이트 타입으로 변경
			            byte[] bytes = file.getBytes();
			           
			            //파일이 실제로 저장되는 경로 
			            String uploadPath = req.getSession().getServletContext().getRealPath("/resources/img/noticeimg");
			            //저장되는 파일 경로 설정
			            System.out.println("uploadPath:"+uploadPath);

			            File uploadFile = new File(uploadPath);
			            if(!uploadFile.exists()) {
			            	uploadFile.mkdir();
			            }
			          //파일이름을 랜덤하게 생성
			            String fileName2 = UUID.randomUUID().toString();
			          //업로드 경로 + 파일이름을 줘서  데이터를 서버에 전송
			            uploadPath = uploadPath + "/" + fileName2 +fileName;	//resources/images/noticeimg/uuid랜덤값+파일명 
			            
			            out = new FileOutputStream(new File(uploadPath));
			            out.write(bytes);
			            
			          //클라이언트에 이벤트 추가
			            printWriter = resp.getWriter();
			          //파일이 연결되는 Url 주소 설정
			            String fileUrl = req.getContextPath() + "/resources/img/noticeimg/" +fileName2 +fileName; //url경로
			            System.out.println("fileUrl :" + fileUrl);
			            //json으로 전달 
			            JsonObject json = new JsonObject();
			          //생성된 json 객체를 이용해 파일 업로드 + 이름 + 주소를 CkEditor에 전송
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
