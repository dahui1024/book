package com.midread.book.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.midread.book.business.BookBusiness;
import com.midread.book.db.entity.Book;
import com.midread.book.utils.CommonUtils;

@Controller
public class BookController {
	@Autowired
	BookBusiness bookBusiness;
	@Value("#{config.baidu_token}")
	protected String baidu_token;
	
	@RequestMapping(value="/books")
	public String txts(@RequestParam(defaultValue="1",required=false)int page, HttpServletRequest request, Model model){
		List<Book> book_list = bookBusiness.getBooks(page);
		model.addAttribute("books", book_list);
		if (book_list.size() == 10) {
			model.addAttribute("page", page + 1);
		}
		
		return "book/list";
	}
	@RequestMapping(value="/books/{id}")
	public String txts(@PathVariable String id, Model model){
		model.addAttribute("chapter_list", bookBusiness.getBookChapters(id));
		model.addAttribute("book", bookBusiness.getBook(id));
		return "book/detail";
	}
	@RequestMapping(value="/books/{book_id}/{sn}")
	public String txts(@PathVariable String book_id, @PathVariable int sn, HttpServletRequest request, Model model){
		model.addAttribute("chapter", bookBusiness.getChapter(book_id, sn));
		model.addAttribute("token", baidu_token);
		model.addAttribute("ip", CommonUtils.getIpAddress(request));
		return "book/read";
	}
	@RequestMapping(value="/chapters/{id}")
	public String chapter(@PathVariable String id, HttpServletRequest request, Model model){
		model.addAttribute("chapter", bookBusiness.getChapterById(id));
		model.addAttribute("token", baidu_token);
		model.addAttribute("ip", CommonUtils.getIpAddress(request));
		return "book/read";
	}
}
