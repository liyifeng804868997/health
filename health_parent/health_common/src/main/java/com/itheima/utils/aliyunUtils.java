package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class aliyunUtils {
    public static String accessKey="LTAI4FkQ7EZUCNooHpCaFBa8";
    public static String secretKey="ldkiCx6NkcXEmjw2CxAkG9C9gYxwHC";
    public static String bucket="itheimahealth-space2";
    public static String endpoint="http://oss-cn-shenzhen.aliyuncs.com";

    public static void  upload2aliyun(String filePath,String fileName) throws FileNotFoundException {
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);

// 上传文件流。
        InputStream inputStream = new FileInputStream(filePath);
        ossClient.putObject(bucket, fileName, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void upload2aliyun(byte[] bytes,String fileName){

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey,secretKey);

        ossClient.putObject(bucket, fileName, new ByteArrayInputStream(bytes));

// 关闭OSSClient。
        ossClient.shutdown();
    }



    public static void deleteFileFromAliyun(String fileName){
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);

// 删除文件。
        ossClient.deleteObject(bucket, fileName);

// 关闭OSSClient。
        ossClient.shutdown();
    }

}
