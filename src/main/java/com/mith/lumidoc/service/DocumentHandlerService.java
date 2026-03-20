package com.mith.lumidoc.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author mithl
 * @date 20-03-2026
 * @email mithleshshah84@gmail.com
 */
public interface DocumentHandlerService {

    void handleDocs(MultipartFile file);
}
