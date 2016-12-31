package com.midread.book.db.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.midread.book.db.dao.ChapterDAO;
import com.midread.book.db.entity.Chapter;
import com.midread.book.db.entity.Comment;

@Component
public class ChapterService {
	@Autowired
	private ChapterDAO chapterDAO;

	public void save(Chapter chapter) {
		chapterDAO.save(chapter);
	}
	public Chapter getById(ObjectId id){
		return chapterDAO.get(id);
	}
	
	public Chapter getByBookAndSn(ObjectId book_id, int sn){
		Query<Chapter> query = chapterDAO.createQuery();
		query.and(query.criteria("book_id").equal(book_id), query.criteria("sn").equal(sn));
		query.retrievedFields(false, "content");
		return chapterDAO.findOne(query);
	}
	
	public List<Chapter> getByBook(ObjectId book_id){
		Query<Chapter> query = chapterDAO.createQuery().field("book_id").equal(book_id);
		query.order("sn");
		return chapterDAO.find(query).asList();
	}
	
	public void addComment(ObjectId id, Comment comment){
		Query<Chapter> query = chapterDAO.createQuery().field("_id").equal(id);
		
		UpdateOperations<Chapter> ops = chapterDAO.createUpdateOperations();
		ops.add("comments", comment);
		
		chapterDAO.update(query, ops);
	}
	
}
