package com.mith.lumidoc;


import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mithl
 * @date 20-03-2026
 * @email mithleshshah84@gmail.com
 */
@Configuration
public class LumiDocConfig {

    @Value("${open-ai.api-key}")
    private String openApiKey;

    @Bean
    public EmbeddingModel embeddingModel() {
        return OpenAiEmbeddingModel.builder()
                .apiKey(openApiKey)
                .modelName("text-embedding-3-small")
                .build();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore(){
       return PgVectorEmbeddingStore.builder()
                .host("localhost")
                .port(5432)
                .database("lumidoc")
                .user("postgres")
                .password("postgres")
                .table("embeddings")
                .dimension(1536)
                .build();
    }
}
