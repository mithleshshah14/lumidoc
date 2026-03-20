package com.mith.lumidoc.service.impl;

import com.mith.lumidoc.exception.FileHandlerException;
import com.mith.lumidoc.service.DocumentHandlerService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author mithl
 * @date 20-03-2026
 * @email mithleshshah84@gmail.com
 */
@AllArgsConstructor
@Service
public class DocumentHandlerServiceImpl implements DocumentHandlerService {

    private final EmbeddingModel embeddingModel;

    private final EmbeddingStore<TextSegment> embeddingStore;

    @Override
    public void handleDocs(MultipartFile file) {

        try{
            if(file.isEmpty()) {
                throw new FileHandlerException("File is Empty");
            }

            InputStream inputStream = file.getInputStream();

            try(PDDocument document = Loader.loadPDF(inputStream.readAllBytes())){
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                Document document1 = Document.from(text);
                DocumentSplitter splitter = DocumentSplitters.recursive(500, 100);

                EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                        .embeddingModel(embeddingModel)
                        .embeddingStore(embeddingStore)
                        .documentSplitter(splitter)
                        .build();

                ingestor.ingest(document1);



            }

        } catch (IOException exception){
            throw new FileHandlerException("Error while processing file");
        }
    }
}
