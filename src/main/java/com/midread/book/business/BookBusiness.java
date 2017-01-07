package com.midread.book.business;

import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.midread.book.db.entity.Book;
import com.midread.book.db.entity.Chapter;

@Component
public class BookBusiness extends AbstractBusiness {
	
	public List<Book> getBooks(int page){
		List<Book> book_list = bookService.getByPage(page);
		return book_list;
	}
	public Book getBook(String id){
		return bookService.getById(new ObjectId(id));
	}
	public Chapter getChapter(String book_id, int sn){
		Chapter chapter = chapterService.getByBookAndSn(new ObjectId(book_id), sn);
		Collections.reverse(chapter.getComments());
		return chapter;
	}
	public Chapter getChapterById(String id){
		Chapter chapter = chapterService.getById(new ObjectId(id));
		Collections.reverse(chapter.getComments());
		return chapter;
	}
	
	public List<Chapter> getBookChapters(String id){
		return chapterService.getByBook(new ObjectId(id));
	}
	
}
