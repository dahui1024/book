package com.midread.book.business;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.midread.book.db.entity.Comment;

@Component
public class CommentBusiness extends AbstractBusiness {
	
	public void publishComment(String id, String invitation_code, String remark){
		Comment comment = new Comment();
		comment.setCreate_time(new Date());
		if (StringUtils.isBlank(remark) || StringUtils.isBlank(invitation_code)) {
			return;
		}
		comment.setNickname(StringUtils.trim(invitation_code));
		comment.setRemark(remark);
		
		chapterService.addComment(new ObjectId(id), comment);
	}
	
	public void publishEssayComment(String id, String invitation_code, String remark){
		Comment comment = new Comment();
		comment.setCreate_time(new Date());
		if (StringUtils.isBlank(remark) || StringUtils.isBlank(invitation_code)) {
			return;
		}
		comment.setNickname(StringUtils.trim(invitation_code));
		comment.setRemark(remark);
		
		essayService.addComment(new ObjectId(id), comment);
	}
}
