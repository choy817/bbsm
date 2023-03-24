package com.bbsm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbsm.domain.MapDTO;
import com.bbsm.service.MapService;
import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/map/*")
public class MapController {
	
	@Setter(onMethod_ = @Autowired)
	MapService mapService;
	@GetMapping("/list")
	public void map(Model model) {
		log.info("Controller ==============> boardMap........");
		List<MapDTO> mapList=new ArrayList<MapDTO>();
		mapList=mapService.getmapList();
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
	
	@GetMapping("/addMap")
	public void addMap() {
		log.info("Controller ==============> addMap.........(Get)");
	}
	@PostMapping("/addMap")
	public String addMap(MapDTO map, Model model) {
		log.info("Controller ==============> addMap.........(Post)");
		if(mapService.addMap(map)) {
			log.info("지도 추가 성공......");
			model.addAttribute("msg","addSuccess");
			return "redirect:/map/list";
		}
		return "";
	}
	
	@GetMapping("/modifyMap")
	public void modifyMap(@RequestParam("mapNo") Long mapNo, Model model) {
		log.info("Controller ==============> modifyMap.........(Get)");
		model.addAttribute("map",mapService.viewMap(mapNo));
	}
	
	@PostMapping("/modifyMap")
	public String modifyMap(MapDTO map, RedirectAttributes rttr) {
		log.info("Controller ==============> modifyMap.........(post)"+map);
		if(mapService.modifyMap(map)) {
			log.info("게시글 수정 성공...........");
			rttr.addFlashAttribute("msg", "modSuccess");
			return "redirect:/map/list";
		}
		return "";
	}
	
	@GetMapping("/deleteMap")
	public String deleteMap(@RequestParam("mapNo") Long mapNo, RedirectAttributes rttr) {
		log.info("Controller ==============> deleteMap.........");
		if(mapService.deleteMap(mapNo)) {
			log.info("게시글 삭제 성공...........");
			rttr.addFlashAttribute("msg", "delSuccess");
			return "redirect:/map/list";
		}
		return "";
	}
	
}
