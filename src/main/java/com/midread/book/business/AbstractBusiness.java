package com.midread.book.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.midread.book.db.service.BookService;
import com.midread.book.db.service.ChapterService;
import com.midread.book.db.service.EssayService;
import com.midread.book.redis.StringTemplate;
import com.midread.book.utils.QiniuUtil;

public class AbstractBusiness {
	@Autowired
	EssayService essayService;
	@Autowired
	BookService bookService;
	@Autowired
	ChapterService chapterService;
	@Autowired
	QiniuUtil qiniuUtil;
	
	@Autowired
	StringTemplate stringTemplate;
	
}
