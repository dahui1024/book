package com.midread.book.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.midread.book.business.TabbarBusiness;

@Controller
public class TabbarController {
	@Autowired
	TabbarBusiness tabbarBusiness;
	
	@RequestMapping(value="/images")
	public String images(@RequestParam(defaultValue="1",required=false)int page, HttpServletRequest request, Model model){
		model.addAttribute("images", tabbarBusiness.getImages(page));
		model.addAttribute("page", page+1);
		return "image/images";
	}
	
}
