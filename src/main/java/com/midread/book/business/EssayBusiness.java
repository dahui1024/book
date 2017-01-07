package com.midread.book.business;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.midread.book.db.entity.Essay;
import com.midread.book.utils.AESUtils;

@Component
public class EssayBusiness extends AbstractBusiness {
	
	public List<Essay> getEssays(int page){
		List<Essay> essay_list = essayService.getByPage(page);
		return essay_list;
	}
	public Essay getEssay(String id){
		Essay essay = essayService.getById(new ObjectId(id));
		
		String digest_16 = StringUtils.substring(essay.getPassword(), 8, 24);
		
		try {
			System.out.println(AESUtils.Decrypt(essay.getContents(), digest_16));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (essay.getComments() != null) {
			Collections.reverse(essay.getComments());
		}
		return essay;
	}
	
	
}
