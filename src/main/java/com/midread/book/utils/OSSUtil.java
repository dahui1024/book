package com.midread.book.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.midread.book.exception.IllegalFileTypeException;

public class OSSUtil {
	private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	private static String accessKeyId = "tT9ivLVj2WdK3dlg";
	private static String accessKeySecret = "KmfFzPeB5iCUwB2wYZQehbts9Tugcg";
	
	public static void uploadImage(MultipartFile file , String key) throws IllegalFileTypeException {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 上传文件流
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
			
			if(!file.getContentType().startsWith("image")){
				throw new IllegalFileTypeException();
			}
			System.out.println(key);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(file.getContentType());
			
			ossClient.putObject("bbcow-0", key, inputStream, meta);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				ossClient.shutdown();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
