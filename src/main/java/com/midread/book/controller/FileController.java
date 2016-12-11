package com.midread.book.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midread.book.exception.IllegalFileTypeException;
import com.midread.book.form.FileForm;
import com.midread.book.utils.CommonConstant;
import com.midread.book.utils.NameUtil;
import com.midread.book.utils.OSSUtil;

@Controller("fileController")
public class FileController {

	@RequestMapping(value="/image", method=RequestMethod.POST)
	public @ResponseBody Object version(FileForm form, HttpServletRequest request){
		if(StringUtils.equals(CommonConstant.INVATION_CODE, form.getInvitation_code())){
			try {
				OSSUtil.uploadImage(form.getFile(), NameUtil.image(form.getTitle()));
				
			} catch (IllegalFileTypeException e) {
				e.printStackTrace();
			}
		}
		return "test";
	}
}
