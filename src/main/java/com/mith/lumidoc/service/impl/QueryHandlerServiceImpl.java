package com.mith.lumidoc.service.impl;

import com.mith.lumidoc.service.QueryHandlerService;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mithl
 * @date 20-03-2026
 * @email mithleshshah84@gmail.com
 */
@AllArgsConstructor
@Service
public class QueryHandlerServiceImpl implements QueryHandlerService {

    private EmbeddingModel model;

    private ChatModel chatModel;

    private EmbeddingStore<TextSegment> embeddingStore;

    @Override
    public String handleQuery(String query) {

        Embedding embedding = model.embed(query).content();

        EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
                .queryEmbedding(embedding).maxResults(3).build();

        EmbeddingSearchResult<TextSegment> result = embeddingStore.search(request);

       List<String> data =  result.matches().stream()
               .map(e -> e.embedded().text()).toList();
       String context = String.join("\n\n",data);

        String prompt = "Use the following context to answer the question.\n" +
                "If answer not in context say I don't know\n\n" +
                "Context:\n" + context + "\n\n" +
                "Question:\n" + query;

        return chatModel.chat(prompt);
    }
}
