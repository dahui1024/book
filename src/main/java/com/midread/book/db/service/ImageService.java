package com.midread.book.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.midread.book.db.dao.ImageDAO;
import com.midread.book.db.entity.Image;

@Component
public class ImageService {
	@Autowired
	private ImageDAO imageDAO;

	public void save(Image image) {
		imageDAO.save(image);
	}

}
