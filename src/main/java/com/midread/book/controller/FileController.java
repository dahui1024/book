package com.midread.book.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.midread.book.business.FileBusiness;
import com.midread.book.controller.form.EssayForm;
import com.midread.book.controller.form.FileForm;
import com.midread.book.controller.form.TxtForm;

@Controller("fileController")
public class FileController {
	@Autowired
	FileBusiness fileBusiness;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(FileForm form, HttpServletRequest request) {
		return "redirect:"+fileBusiness.upload(form);
	}
	
	@RequestMapping(value = "/wisdoms/upload_txt", method = RequestMethod.POST)
	public String uploadTxt(TxtForm form, HttpServletRequest request) {
		fileBusiness.uploadTxt(form);
		return "redirect:/books";
	}
	@RequestMapping(value = "/wisdoms/upload_essay", method = RequestMethod.POST)
	public String uploadEssay(EssayForm form, HttpServletRequest request) {
		fileBusiness.uploadEssay(form);
		return "redirect:/essays";
	}
}
