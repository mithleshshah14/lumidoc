package com.mith.lumidoc.controller;

import com.mith.lumidoc.service.DocumentHandlerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author mithl
 * @date 20-03-2026
 * @email mithleshshah84@gmail.com
 */
@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class DocumentController {

    private DocumentHandlerService documentHandlerService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDoc(@RequestParam MultipartFile file){
        documentHandlerService.handleDocs(file);
        return ResponseEntity.ok("File Uploaded Successfully");
    }
}
