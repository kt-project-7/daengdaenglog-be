package com.clover.util;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlobUtils {

    private final BlobContainerClient containerClient;

    public BlobUtils(@Value("${spring.cloud.azure.storage.blob.connection-string}") String connectionString,
            @Value("${spring.cloud.azure.storage.blob.container-name}") String containerName) {
        this.containerClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient()
                .getBlobContainerClient(containerName);
    }

    // MultipartFile에서 file.getOriginalFilename(), file.getInputStream() 를 불러오면 됨
    public void uploadFile(String fileName, InputStream fileData) throws IOException {
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        blobClient.upload(fileData, fileData.available(), true);
    }
}
