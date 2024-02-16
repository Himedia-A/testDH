package org.zerock.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.ibatis.javassist.tools.reflect.Sample;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;



@Controller
@RequestMapping("/sample/*")
@Log4j
public class HimediaController {
	
	@RequestMapping(value = "/basic",method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get.......");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get........");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		dto.setAge(11);
		dto.setName("ㅋㅋ");
		log.info(""+dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name: " + name);
		log.info("age: "+ age);
		return "ex02";
	}
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids: " + ids);
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids: "+Arrays.toString(ids));
		return "ex02Array";
	}
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		return "ex02Bean";
	}
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: "+todo);
		return "ex03";
	}
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page, Model model) {
		log.info("dto: "+dto);
		log.info("page: "+page);
		model.addAttribute("himedia","하이미디어");
		return "/sample/ex04";
	}
	@GetMapping("/exts")
	public String exts(Model model) {
		//model.addAttribute("test","ㅎㅇ");
//		rttr.addFlashAttribute("name","abcd");
//		rttr.addFlashAttribute("age",20);
		return "/sample/exts";
	}
	@GetMapping("/exts2")
	public String exts2(RedirectAttributes rttr) {
		rttr.addFlashAttribute("test","ㅋㅋ");
		return "redirect:/sample/exts";
	}
	@GetMapping("/hi01")
	public String hi01() {
		log.info("hi01");
		return "home";
	}
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06...");
		SampleDTO dto = new SampleDTO();
		dto.setAge(11);
		dto.setName("ㅎㅇ");
		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07....");
		
		String msg = "{\"name\": \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/json;charset=utf-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload....");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		String path = "C:\\workspace\\spring\\ex01\\src\\main\\webapp\\WEB-INF\\views\\upload";
		files.forEach(file->{
			log.info("----------------------");
			log.info("name:"+file.getOriginalFilename());
			log.info("size:"+file.getSize());
			File sfile = new File(path + "\\" + file.getOriginalFilename());
				try {
					file.transferTo(sfile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		});
	}
	
}
