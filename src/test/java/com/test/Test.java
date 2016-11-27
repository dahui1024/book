package com.test;

import com.aliyun.oss.OSSClient;

public class Test {
	public static void main(String[] args) {
		// endpoint以杭州为例，其它region请按实际情况填写
		String endpoint = "http://oss-cn-beijing.aliyuncs.com";
		// accessKey请登录https://ak-console.aliyun.com/#/查看
		String accessKeyId = "tT9ivLVj2WdK3dlg";
		String accessKeySecret = "KmfFzPeB5iCUwB2wYZQehbts9Tugcg";
		// 创建OSSClient实例
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 使用访问OSS
		
		boolean found = client.doesObjectExist("bbcow-0", "test.html");
		System.out.println(found);
		// 关闭client
		client.shutdown();
	}
}
