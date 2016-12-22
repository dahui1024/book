package com.midread.book.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;

public class QiniuUtil {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "QZYtTsqkeWpMzkJ3T33o3IybYbkiWFphMLJ40DUc";
	String SECRET_KEY = "emLguAHwV5ugqSW-DUfC86YUXIOXHaBUf3OayO82";
	// 要上传的空间
	String bucketname = "midread";

	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

	// 第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
	Zone z = Zone.zone1();
	Configuration c = new Configuration(z);

	// 创建上传对象
	UploadManager uploadManager = new UploadManager(c);

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
		return auth.uploadToken(bucketname);
	}

	public void upload(String key, byte[] data) throws IOException {
		try {
			// 调用put方法上传
			Response res = uploadManager.put(data, key, getUpToken());
			// 打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}
	
	public List<String> list(String path){
        BucketManager bucketManager = new BucketManager(auth,c);
        List<String> list = new ArrayList<String>();
		try {
			// 调用listFiles方法列举指定空间的指定文件
			// 参数一：bucket 空间名
			// 参数二：prefix 文件名前缀
			// 参数三：marker 上一次获取文件列表时返回的 marker
			// 参数四：limit 每次迭代的长度限制，最大1000，推荐值 100
			// 参数五：delimiter 指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
			FileListing fileListing = bucketManager.listFiles(bucketname, "txt", null, 10, null);
			FileInfo[] items = fileListing.items;
			for (FileInfo fileInfo : items) {
				list.add(fileInfo.key);
			}
		} catch (QiniuException e) {
			// 捕获异常信息
			Response r = e.response;
			System.out.println(r.toString());
		}
		return list;
	}
	
	
	public static void main(String[] args) {
		QiniuUtil qu = new QiniuUtil();
		qu.list("image");
	}
}
