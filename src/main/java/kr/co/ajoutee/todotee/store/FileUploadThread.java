package kr.co.ajoutee.todotee.store;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadThread implements Runnable {
    private final MultipartFile file;
    private final String destination;

    FileUploadThread(MultipartFile file, String destination) {
        this.file = file;
        this.destination = destination;
    }

    @Override
    public void run() {
        try {
            file.transferTo(new File(destination));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
