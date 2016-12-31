package com.midread.book.db.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.midread.book.db.dao.BookDAO;
import com.midread.book.db.entity.Book;

@Component
public class BookService {
	@Autowired
	private BookDAO bookDAO;

	public void save(Book txt) {
		bookDAO.save(txt);
	}

	public List<Book> getAll(){
		return bookDAO.find().asList();
	}
	
	public List<Book> getByPage(int page){
		Query<Book> query = bookDAO.createQuery();
		query.order("-upload_time");
		query.offset((page - 1) * 10);
		query.limit(10);
		return bookDAO.find(query).asList();
	}
	
	public Book getById(ObjectId id){
		return bookDAO.get(id);
	}
}
