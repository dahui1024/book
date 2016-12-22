package com.midread.book.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midread.book.form.FileForm;
import com.midread.book.utils.CommonConstant;
import com.midread.book.utils.NameUtil;
import com.midread.book.utils.QiniuUtil;

@Controller("fileController")
public class FileController {

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public @ResponseBody Object version(FileForm form, HttpServletRequest request) {
		if (StringUtils.equals(CommonConstant.INVATION_CODE, form.getInvitation_code())) {
			QiniuUtil qu = new QiniuUtil();
			try {
				String name = form.getFile().getOriginalFilename();
				if (name.endsWith("jpg") || name.endsWith("png") || name.endsWith("gif") || name.endsWith("jpeg")) {
					qu.upload("image/" + NameUtil.dateName() + "/" + name, form.getFile().getBytes());
				}
				if (name.endsWith("txt")) {
					qu.upload("txt/" + NameUtil.dateName() + "/" + name, form.getFile().getBytes());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "test";
	}
}
