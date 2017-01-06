package com.midread.book.business;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.io.LineReader;
import com.midread.book.db.entity.Book;
import com.midread.book.db.entity.Chapter;
import com.midread.book.db.entity.Essay;
import com.midread.book.db.service.BookService;
import com.midread.book.db.service.ChapterService;
import com.midread.book.db.service.EssayService;
import com.midread.book.form.EssayForm;
import com.midread.book.form.FileForm;
import com.midread.book.form.TxtForm;
import com.midread.book.redis.StringTemplate;
import com.midread.book.utils.CommonConstant;
import com.midread.book.utils.CommonConstant.STATUS;
import com.midread.book.utils.MD5Util;
import com.midread.book.utils.NameUtil;
import com.midread.book.utils.QiniuUtil;
import com.midread.book.vo.ImageVo;
import com.midread.book.vo.TxtVo;

@Component
public class FileBusiness {
	@Autowired
	QiniuUtil qiniuUtil;
	@Autowired
	StringTemplate stringTemplate;
	@Autowired
	BookService bookService;
	@Autowired
	ChapterService chapterService;
	@Autowired
	EssayService essayService;
	
	public void uploadTxt(TxtForm form){
		if (!StringUtils.equals(CommonConstant.INVATION_CODE, form.getInvitation_code())) {
			return;
		}
		Book txt = new Book();
		txt.setAuthor(form.getAuthor());
		txt.setCategory(form.getCategory());
		txt.setDescription(form.getDesc());
		txt.setLabels(form.getLabels());
		txt.setName(form.getName());
		txt.setProvider(form.getInvitation_code());
		txt.setUpload_time(new Date());
		txt.setStatus(STATUS.enable);

		List<Chapter> chapter_list = new ArrayList<Chapter>();
		String file_name = StringUtils.trim(form.getFile().getOriginalFilename()).toLowerCase();
		if (file_name.endsWith("txt")) {
			
			BOMInputStream bomIn = null;
	    	InputStreamReader reader = null;
	    	try {
	    		bomIn = new BOMInputStream(form.getFile().getInputStream());
	    		String charset = "utf-8";
				if (bomIn.hasBOM()) {
					charset = bomIn.getBOMCharsetName();
				}
				reader = new InputStreamReader(bomIn, charset);
				LineReader lr = new LineReader(reader);
				
	    		String line = null;
	    		Chapter chapter = new Chapter();
				while((line = lr.readLine()) != null){
					if (StringUtils.startsWith(line, "title")) {
						chapter = new Chapter();
						chapter_list.add(chapter);
						chapter.setTitle(line.replace("title", ""));
						chapter.setSn(chapter_list.size());
					} else if (StringUtils.startsWith(line, "name")) {

					} else if (StringUtils.startsWith(line, "author")) {

					} else if (StringUtils.startsWith(line, "describtion")) {

					} else {
						if (StringUtils.isNotBlank(line)) {
							if(chapter.getContent() != null){
								chapter.setContent(chapter.getContent()+"\n" + line);
							}else{
								chapter.setContent(line);
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
					bomIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		txt.setSegments(chapter_list.size());
		bookService.save(txt);
		for (Chapter chapter : chapter_list) {
			chapter.setBook_id(txt.getId());
			chapterService.save(chapter);
			
			// OSS存储
			String oss_url = "txt/" + txt.getId().toString() + "/" + chapter.getSn() + ".txt";
			try {
				qiniuUtil.upload(oss_url, chapter.getContent().getBytes("utf-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void uploadEssay(EssayForm form){
		if (StringUtils.isBlank(form.getName()) || StringUtils.isBlank(form.getContent()) || StringUtils.isBlank(form.getDescription())) {
			return;
		}
		
		Essay essay = new Essay();
		essay.setContent(StringUtils.trim(form.getContent()));
		essay.setDescription(StringUtils.trim(form.getDescription()));
		essay.setName(StringUtils.trim(form.getName()));
		if (StringUtils.isNotBlank(form.getPassword())) {
			essay.setPassword(MD5Util.digest(CommonConstant.SALT+form.getPassword()));
		}
		essay.setUpload_time(new Date());
		essay.setStatus(STATUS.enable);
		
		essayService.save(essay);
		
		String oss_url = "essay/" + essay.getId().toString() + ".txt";
		try {
			// OSS存储
			if (StringUtils.isNotBlank(form.getPassword())) {
				oss_url = "essay/" + essay.getId().toString() + "/" + essay.getPassword() + ".txt";
			}
			qiniuUtil.upload(oss_url, essay.getContent().getBytes("utf-8"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String upload(FileForm form){
		if (StringUtils.equals(CommonConstant.INVATION_CODE, form.getInvitation_code())) {
			try {
				String name = StringUtils.trim(form.getFile().getOriginalFilename()).toLowerCase();
				if (name.endsWith("jpg") || name.endsWith("png") || name.endsWith("gif") || name.endsWith("jpeg")) {
					ImageVo vo = new ImageVo();
					vo.setTitle(form.getTitle());
					vo.setUrl("image/" + NameUtil.dateName() + "/" + name);
					vo.setId(MD5Util.digest(vo.getUrl()));
					vo.setUpload_date(new Date());
					vo.setDesc(form.getDesc());
					qiniuUtil.upload(vo.getUrl(), form.getFile().getBytes());
					
					stringTemplate.leftPush("tabbar:images", vo);
					stringTemplate.setObject("images:"+vo.getId(), vo, 0);
					
					return "images";
				}
				if (name.endsWith("txt")) {
					
					readTxt(form, name);
					
					return "txts";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "index";
		}
		
		return "index";
	}
	
	private void readTxt(FileForm form, String name){
		BOMInputStream bomIn = null;
    	InputStreamReader reader = null;
    	try {
    		bomIn = new BOMInputStream(form.getFile().getInputStream());
    		String charset = "utf-8";
			if (bomIn.hasBOM()) {
				charset = bomIn.getBOMCharsetName();
			}
			reader = new InputStreamReader(bomIn, charset);
			
			LineReader lr = new LineReader(reader);
			
			int count = 0;
			int seg_count = 1;
			StringBuffer txt = new StringBuffer();
			
    		String line = null;
			while((line = lr.readLine()) != null){
				txt.append(line).append("\n");
				count++;
				if(count > 200){
					save(form, seg_count, name, txt, true);
					count = 0;
					seg_count++;
					txt = new StringBuffer();
				}
			}
			if(count > 0){
				save(form, seg_count, name, txt, false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				bomIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void save(FileForm form, int seg_count, String name, StringBuffer txt, boolean hasNext) throws IOException{
		TxtVo vo = new TxtVo();
		vo.setTitle(form.getTitle()+"("+seg_count+")");
		vo.setUrl("txt/" + NameUtil.dateName() + "/" + name.replace(".txt", "/"+seg_count+".txt"));
		vo.setId(MD5Util.digest(NameUtil.dateName() + "/" + name));
		vo.setUpload_date(new Date());
		//vo.setDesc(form.getDesc());
		vo.setCurrent_sn(seg_count);
		if(hasNext){
			vo.setNext_sn(seg_count+1);
		}
		qiniuUtil.upload(vo.getUrl(), txt.toString().getBytes());
		
		if(seg_count == 1){
			TxtVo tvo = new TxtVo();
			tvo.setTitle(form.getTitle());
			tvo.setId(MD5Util.digest(NameUtil.dateName() + "/" + name));
			tvo.setUpload_date(new Date());
			tvo.setDesc(form.getDesc());
			stringTemplate.setObject("txt:cover:"+vo.getId(), tvo, 0);
			stringTemplate.leftPush("tabbar:txts", tvo);
		}
		stringTemplate.rightPush("txts:"+vo.getId(), vo);
		stringTemplate.setObject("txts:"+vo.getId()+":"+seg_count, vo, 0);
	}
}
