package com.midread.book.business;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.midread.book.db.entity.Book;
import com.midread.book.db.entity.Chapter;
import com.midread.book.db.service.BookService;
import com.midread.book.db.service.ChapterService;

@Component
public class BookBusiness {
	@Autowired
	BookService bookService;
	@Autowired
	ChapterService chapterService;
	
	public List<Book> getBooks(int page){
		List<Book> book_list = bookService.getByPage(page);
		return book_list;
	}
	public Book getBook(String id){
		return bookService.getById(new ObjectId(id));
	}
	public Chapter getChapter(String book_id, int sn){
		return chapterService.getByBookAndSn(new ObjectId(book_id), sn);
	}
	public Chapter getChapterById(String id){
		return chapterService.getById(new ObjectId(id));
	}
	
	public List<Chapter> getBookChapters(String id){
		return chapterService.getByBook(new ObjectId(id));
	}
	
}
