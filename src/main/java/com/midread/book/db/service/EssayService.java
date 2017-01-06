package com.midread.book.db.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.midread.book.db.dao.EssayDAO;
import com.midread.book.db.entity.Essay;

@Component
public class EssayService {
	@Autowired
	private EssayDAO essayDAO;

	public void save(Essay essay) {
		essayDAO.save(essay);
	}

	public List<Essay> getAll(){
		return essayDAO.find().asList();
	}
	
	public List<Essay> getByPage(int page){
		Query<Essay> query = essayDAO.createQuery();
		query.order("-upload_time");
		query.offset((page - 1) * 10);
		query.limit(10);
		return essayDAO.find(query).asList();
	}
	
	public Essay getById(ObjectId id){
		return essayDAO.get(id);
	}
}
