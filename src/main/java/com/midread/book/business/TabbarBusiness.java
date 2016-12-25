package com.midread.book.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.midread.book.redis.StringTemplate;
import com.midread.book.utils.QiniuUtil;
import com.midread.book.vo.ImageVo;
import com.midread.book.vo.TxtVo;

@Component
public class TabbarBusiness {
	@Autowired
	QiniuUtil qiniuUtil;
	@Autowired
	StringTemplate stringTemplate;
	
	public List<ImageVo> getImages(int page){
		List<ImageVo> images = stringTemplate.getList("tabbar:images", ImageVo.class, (page-1)*10, 10);
		
		return images;
	}
	
	public List<TxtVo> getTxts(int page){
		List<TxtVo> txts = stringTemplate.getList("tabbar:txts", TxtVo.class, (page-1)*10, 10);
		return txts;
	}
	public List<TxtVo> getTxtList(String id){
		return stringTemplate.getList("txts:"+id, TxtVo.class);
	}
	public TxtVo getTxtCover(String id){
		return stringTemplate.getObject("txt:cover:"+id, TxtVo.class);
	}
	public TxtVo getTxt(String id, int sn){
		
		return stringTemplate.getObject("txts:"+id+":"+sn, TxtVo.class);
	}
}
