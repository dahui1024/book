package com.midread.book.business;

import java.util.List;

import org.springframework.stereotype.Component;

import com.midread.book.controller.vo.ImageVo;
import com.midread.book.controller.vo.TxtVo;

@Component
public class TabbarBusiness extends AbstractBusiness {
	public List<ImageVo> getImages(int page){
		List<ImageVo> images = stringTemplate.getList("tabbar:images", ImageVo.class, (page-1)*10, 10);
		
		return images;
	}
	
	public List<TxtVo> getTxtList(String id){
		return stringTemplate.getList("txts:"+id, TxtVo.class);
	}
	public TxtVo getTxtCover(String id){
		return stringTemplate.getObject("txt:cover:"+id, TxtVo.class);
	}
	public TxtVo getTxt(String id, int sn){
		
		return  stringTemplate.getObject("txts:"+id+":"+sn, TxtVo.class);
	}
}
