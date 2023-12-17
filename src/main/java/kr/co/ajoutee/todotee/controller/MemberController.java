package kr.co.ajoutee.todotee.controller;

import kr.co.ajoutee.todotee.store.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final FileStore fileStore;

    @PostMapping("/profile-images/upload")
    public ResponseEntity uploadProfileImage(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = fileStore.store(file);
        URI imagePath = URI.create("/images" + "/" + filename);
        return ResponseEntity.created(imagePath).build();
    }
}
