package com.itheima.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.itheima.utils.aliyunUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class aliyunTest {
  /*  //使用阿里云提供的SDK实现将本地图片上传到阿里云服务器
    @Test
    public void test1(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FkQ7EZUCNooHpCaFBa8";
        String accessKeySecret = "ldkiCx6NkcXEmjw2CxAkG9C9gYxwHC";
        String bucketName = "itheimahealth-space2";
// <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "D:\\linux关闭防火墙.jpg";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        String content = "Hello OSS";
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

// 关闭OSSClient。
        ossClient.shutdown();

    }


    @Test
    public void test2() throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAI4FkQ7EZUCNooHpCaFBa8";
        String accessKeySecret = "ldkiCx6NkcXEmjw2CxAkG9C9gYxwHC";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = new FileInputStream("D:\\传智健康\\视频\\day04\\资源\\图片资源\\03a36073-a140-4942-9b9b-712cecb144901.jpg");
        ossClient.putObject("itheimahealth-space2", "test1/abc.jpg", inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }


    @Test
    public void test3(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FkQ7EZUCNooHpCaFBa8";
        String accessKeySecret = "ldkiCx6NkcXEmjw2CxAkG9C9gYxwHC";
        String bucketName = "itheimahealth-space2";
// <yourObjectName>表示删除OSS文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "6667.jpg";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件。
        ossClient.deleteObject(bucketName, objectName);

// 关闭OSSClient。
        ossClient.shutdown();
    }


    @Test
    public void test4() throws FileNotFoundException {
        aliyunUtils.upload2aliyun("D:\\传智健康\\视频\\day04\\资源\\图片资源\\3bd90d2c-4e82-42a1-a401-882c88b06a1a2.jpg","bbb.jpg");
    }


    @Test
    public void test5(){
        aliyunUtils.deleteFileFromAliyun("bbb.jpg");
    }

    @Test
    public void test6(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FkQ7EZUCNooHpCaFBa8";
        String accessKeySecret = "ldkiCx6NkcXEmjw2CxAkG9C9gYxwHC";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

// 上传Byte数组。
        byte[] content = "Hello OSS".getBytes();
        ossClient.putObject("itheimahealth-space2", "aaa.jpg", new ByteArrayInputStream(content));

// 关闭OSSClient。
        ossClient.shutdown();
    }*/
}
