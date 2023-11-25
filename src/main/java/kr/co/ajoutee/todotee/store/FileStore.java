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
    public String store(MultipartFile file) throws IOException {
        String filename = createFilename(file.getOriginalFilename());
        String path = storeDirectory + "/" + filename;
        file.transferTo(new File(path));
        return filename;
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
}
