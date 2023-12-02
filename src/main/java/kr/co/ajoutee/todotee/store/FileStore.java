package kr.co.ajoutee.todotee.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${todotee.store.dir}")
    private String storeDirectory;
    public String store(MultipartFile file) {
        String filename = createFilename(file.getOriginalFilename());
        String destination = getDestination(filename);
        uploadFile(file, destination);
        return filename;
    }

    private String getDestination(String filename) {
        return storeDirectory + "/" + filename;
    }

    private String createFilename(String originalName) {
        String storeName = UUID.randomUUID().toString();
        String extension = extractFileExtension(originalName);
        return storeName + '.' + extension;
    }

    private String extractFileExtension(String originalName) {
        int position = originalName.lastIndexOf('.');
        return originalName.substring(position + 1);
    }

    private void uploadFile(MultipartFile file, String destination) {
        FileUploadThread fileUploadThread = new FileUploadThread(file, destination);
        Thread thread = new Thread(fileUploadThread);
        thread.start();
    }


}
