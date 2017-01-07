package com.midread.book.controller.form;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FileForm implements Serializable {
	private static final long serialVersionUID = -2457547290959703993L;
	private MultipartFile file;
	private String title;
	private String desc;
	private String type;
	private String invitation_code;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInvitation_code() {
		return invitation_code;
	}

	public void setInvitation_code(String invitation_code) {
		this.invitation_code = invitation_code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
