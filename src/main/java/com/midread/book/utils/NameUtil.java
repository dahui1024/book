package com.midread.book.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NameUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	public static String image(String title){
		
		return MD5Util.digest(title)+"_"+sdf.format(new Date());
		
	}
	
	public static String dateName(){
		return sdf.format(new Date());
	}
}
