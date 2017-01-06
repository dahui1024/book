package com.midread.book.business;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.midread.book.db.entity.Essay;
import com.midread.book.db.service.EssayService;

@Component
public class EssayBusiness {
	@Autowired
	EssayService essayService;
	
	public List<Essay> getEssays(int page){
		List<Essay> essay_list = essayService.getByPage(page);
		return essay_list;
	}
	public Essay getEssay(String id){
		return essayService.getById(new ObjectId(id));
	}
	
	
}
