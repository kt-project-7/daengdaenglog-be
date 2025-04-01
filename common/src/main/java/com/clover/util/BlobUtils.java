package com.clover.util;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

import com.azure.storage.blob.models.BlobHttpHeaders;
import com.clover.exception.FileConvertFailException;
import com.clover.exception.errorcode.GlobalErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class BlobUtils {

    private final BlobContainerClient containerClient;

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("${spring.cloud.azure.storage.blob.account-name}")
    private String accountName;

    public BlobUtils(@Value("${spring.cloud.azure.storage.blob.connection-string}") String connectionString,
                     @Value("${spring.cloud.azure.storage.blob.container-name}") String containerName) {
        this.containerClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient()
                .getBlobContainerClient(containerName);
    }

    public String uploadFile(MultipartFile multipartFile, String path) {
        String fileName = UUID.randomUUID() + "_" + Objects.requireNonNull(multipartFile.getOriginalFilename());
        if (path.lastIndexOf("/") != path.length() - 1) {
            path += "/";
        }

        String fileLocation = path + fileName;
        try (InputStream fileData = multipartFile.getInputStream()) {
            BlobClient blobClient = containerClient.getBlobClient(fileLocation);
            blobClient.upload(fileData, fileData.available(), true);

            Path newPath = Paths.get(fileName);
            String contentType = Files.probeContentType(newPath);

            blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(contentType));

            return "https://" + accountName + ".blob.core.windows.net/" +  containerName + "/" + fileLocation;
        } catch (IOException e) {
            throw new FileConvertFailException(GlobalErrorCode.FILE_CONVERT_FAIL);
        }
    }
}