package com.midread.book.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midread.book.business.BookBusiness;
import com.midread.book.business.CommentBusiness;

@Controller
public class CommentController {
	@Autowired
	CommentBusiness commentBusiness;
	@Autowired
	BookBusiness bookBusiness;
	
	@RequestMapping(value="/chapter/comments")
	public @ResponseBody String chapter(@RequestParam String id, @RequestParam String invitation_code, @RequestParam String remark, HttpServletRequest request, Model model){
		commentBusiness.publishComment(id, invitation_code, remark);
		return "sucess";
	}
	
	@RequestMapping(value="/essay/comments")
	public @ResponseBody String essay(@RequestParam String id, @RequestParam String invitation_code, @RequestParam String remark, HttpServletRequest request, Model model){
		commentBusiness.publishEssayComment(id, invitation_code, remark);
		return "sucess";
	}
}
