package com.mith.lumidoc.controller;

import com.mith.lumidoc.service.QueryHandlerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mithl
 * @date 20-03-2026
 * @email mithleshshah84@gmail.com
 */
@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class QueryController {

    private QueryHandlerService queryHandlerService;

    @GetMapping("/query")
    public ResponseEntity<String> query(@RequestParam String query){
        return ResponseEntity.ok(queryHandlerService.handleQuery(query));
    }

}
