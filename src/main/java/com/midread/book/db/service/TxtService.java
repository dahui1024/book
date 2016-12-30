package com.midread.book.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.midread.book.db.dao.TxtDAO;
import com.midread.book.db.entity.Txt;

@Component
public class TxtService {
	@Autowired
	private TxtDAO txtDAO;

	public void save(Txt txt) {
		txtDAO.save(txt);
	}

}
