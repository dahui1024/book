package com.midread.book.db.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.midread.book.db.entity.Txt;
@Repository("txtDAO")
public class TxtDAO extends BasicDAO<Txt, ObjectId>{
	@Autowired
	public TxtDAO(Datastore datastore) {
		super(datastore);
	}
}
