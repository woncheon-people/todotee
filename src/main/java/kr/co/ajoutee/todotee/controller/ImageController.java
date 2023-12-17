package kr.co.ajoutee.todotee.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Value("${todotee.store.dir}")
    private String storeDirectory;

    @GetMapping("/{filename}")
    public Resource getImage(@PathVariable("filename") String filename) throws MalformedURLException {
        return new FileUrlResource(storeDirectory + '/' + filename);
    }
}
