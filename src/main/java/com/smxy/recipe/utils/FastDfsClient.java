/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/27 21:38
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class FastDfsClient {
    private static final Logger logger = LoggerFactory.getLogger(FastDfsClient.class);
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageClient1 storageClient;
    private static StorageServer storageServer;
    static {
        try {
            String filePath = new ClassPathResource("fastdfs_server.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!",e);
        }
    }
    public static String uploadFile(String fileName, String extName, NameValuePair[] pairs) throws Exception{
        return storageClient.upload_file1(fileName, extName, pairs);
    }

    static void deleteFile(String fileName) throws Exception {
        storageClient.delete_file1(fileName);
    }

    static String uploadBinaryFile(byte[] source, String extName, NameValuePair[] pairs) throws Exception{
        return storageClient.upload_file1(source, extName, pairs);
    }

}
