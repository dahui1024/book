package com.midread.book.business;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.io.LineReader;
import com.midread.book.form.FileForm;
import com.midread.book.redis.StringTemplate;
import com.midread.book.utils.CommonConstant;
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
