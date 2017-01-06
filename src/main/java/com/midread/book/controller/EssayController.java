package com.midread.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midread.book.business.EssayBusiness;
import com.midread.book.db.entity.Essay;
import com.midread.book.utils.CommonConstant;
import com.midread.book.utils.CommonUtils;
import com.midread.book.utils.MD5Util;

@Controller
public class EssayController {
	@Autowired
	EssayBusiness essayBusiness;
	@Value("#{config.baidu_token}")
	protected String baidu_token;
	
	@RequestMapping(value="/essays")
	public String txts(@RequestParam(defaultValue="1",required=false)int page, HttpServletRequest request, Model model){
		List<Essay> essay_list = essayBusiness.getEssays(page);
		model.addAttribute("essays", essay_list);
		if (essay_list.size() == 10) {
			model.addAttribute("page", page + 1);
		}
		
		return "essay/list";
	}
	@RequestMapping(value="/essays/{id}")
	public String txts(@PathVariable String id, HttpServletRequest request, Model model){
		Essay essay = essayBusiness.getEssay(id);
		model.addAttribute("essay", essay);
		model.addAttribute("token", baidu_token);
		model.addAttribute("ip", CommonUtils.getIpAddress(request));
		if(StringUtils.isBlank(essay.getPassword())){
			return "essay/read";
		}
		return "essay/pass_read";
	}
	@RequestMapping(value="/essays/ajax/check/{id}")
	public @ResponseBody String check(@PathVariable String id, @RequestParam String password, HttpServletRequest request, Model model){
		Essay essay = essayBusiness.getEssay(id);
		if(StringUtils.equals(MD5Util.digest(CommonConstant.SALT+password), essay.getPassword())){
			return essay.getPassword();
		}else{
			return "error";
		}
	}
}
