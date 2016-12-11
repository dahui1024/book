package com.midread.book.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.midread.book.exception.IllegalFileTypeException;
import com.midread.book.form.FileForm;
import com.midread.book.utils.CommonConstant;
import com.midread.book.utils.NameUtil;
import com.midread.book.utils.OSSUtil;

@Controller
public class TabbarController {

	@RequestMapping(value="/images")
	public String version(FileForm form, HttpServletRequest request){
		if(StringUtils.equals(CommonConstant.INVATION_CODE, form.getInvitation_code())){
			try {
				OSSUtil.uploadImage(form.getFile(), NameUtil.image(form.getTitle()));
				
			} catch (IllegalFileTypeException e) {
				e.printStackTrace();
			}
		}
		return "image/images";
	}
}
