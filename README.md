# LumiDoc 📄

LumiDoc is an AI-powered document Q&A application built with Java and Spring Boot. Upload any PDF document and ask questions about it — LumiDoc uses RAG (Retrieval Augmented Generation) to find relevant information and generate accurate answers.

## Features

- PDF document upload and text extraction
- Intelligent document chunking with overlap
- Semantic search using pgvector
- OpenAI embeddings for vector generation
- RAG pipeline for accurate, context-aware answers
- REST API for easy integration

## Tech Stack

- **Backend:** Java 17, Spring Boot 3.4.1
- **AI/LLM:** LangChain4j, OpenAI (text-embedding-3-small, gpt-4o-mini)
- **Vector Database:** PostgreSQL + pgvector
- **PDF Processing:** Apache PDFBox 3.x
- **Build Tool:** Gradle

## Prerequisites

- Java 17+
- Docker
- OpenAI API Key

## Setup

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/lumidoc.git
cd lumidoc
```

### 2. Start PostgreSQL with pgvector
```bash
docker run --name lumidoc-postgres \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_DB=lumidoc \
  -p 5432:5432 \
  -d pgvector/pgvector:pg16
```

### 3. Enable pgvector extension
```bash
docker exec -it lumidoc-postgres psql -U postgres -d lumidoc -c "CREATE EXTENSION IF NOT EXISTS vector;"
```

### 4. Configure application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lumidoc
spring.datasource.username=postgres
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.time_zone=UTC

openai.api.key=your-openai-api-key
```

### 5. Run the application
```bash
./gradlew bootRun
```

## API Endpoints

### Upload Document
```
POST /api/upload
Content-Type: multipart/form-data

Parameters:
  file: PDF file to upload
```

### Query Document
```
GET /api/query?query=your question here
```

## How It Works

LumiDoc uses RAG (Retrieval Augmented Generation) pipeline:

```
PDF Upload
    → Text Extraction (PDFBox)
    → Document Chunking (500 chars, 100 overlap)
    → Embedding Generation (OpenAI)
    → Vector Storage (pgvector)

User Query
    → Query Embedding (OpenAI)
    → Semantic Search (pgvector)
    → Context Retrieval (top 3 matches)
    → Answer Generation (OpenAI GPT-4o-mini)
    → Response
```

## Project Structure

```
lumidoc/
├── src/main/java/com/mith/lumidoc/
│   ├── controller/
│   │   ├── DocumentController.java
│   │   └── QueryController.java
│   ├── service/
│   │   ├── DocumentHandlerService.java
│   │   ├── QueryHandlerService.java
│   │   └── impl/
│   │       ├── DocumentHandlerServiceImpl.java
│   │       └── QueryHandlerServiceImpl.java
│   ├── config/
│   │   └── LumiDocConfig.java
│   └── LumidocApplication.java
└── src/main/resources/
    └── application.properties
```

## 🔮 Future Enhancements

- Multiple document support
- Document metadata storage
- Chat history and memory
- Streaming responses
- Web UI

## 📄 License

MIT License
