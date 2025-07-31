# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Development Commands

```bash
# Build application
./mvnw clean package

# Run application
./mvnw spring-boot:run

# Run tests
./mvnw test

# Run single test
./mvnw test -Dtest=XuchongBlogApplicationTests

# Package JAR
./mvnw clean package -DskipTests
```

## Architecture Overview

This is a Spring Boot 3.4.5 backend application for a personal blog system with the following structure:

### Core Technologies
- **Java 17** with Spring Boot 3.x
- **MyBatis Plus 3.5.12** for ORM
- **MySQL 8.0** for primary database
- **Redis** for caching/sessions (port 6381)
- **JWT** for authentication
- **Aliyun OSS** for file storage
- **QQ Email SMTP** for verification codes

### Package Structure
- `com.xuchong.blog.common.*` - Common utilities, constants, configuration properties
- `com.xuchong.blog.pojo.dto` - Request/Response DTOs
- `com.xuchong.blog.pojo.entity.db` - Database entities
- `com.xuchong.blog.pojo.entity` - View models for API responses
- `com.xuchong.blog.server.controller` - REST API controllers
- `com.xuchong.blog.server.service` - Business logic services
- `com.xuchong.blog.server.mapper` - MyBatis mappers

### Key Features
- **User Management**: Registration, login, JWT authentication
- **Moment System**: Create, like/unlike moments (posts)
- **Guest Book**: Visitor messages and comments
- **Admin Panel**: Admin-only operations
- **File Upload**: Aliyun OSS integration
- **Email Verification**: QQ SMTP for registration verification

### Authentication
- JWT-based authentication with custom `JwtTokenInterceptor`
- Token validation on `/user/logout`, `/user/getIsAdmin`, and `/moment/likeOrDislike/**`
- CORS configured for `http://localhost:3000` frontend

### Database Configuration
- Main database: `xuchong_blog` on MySQL
- Redis: Database 2 on localhost:6381
- MyBatis Plus with camelCase naming convention
- Table names: user, moment, moment_like, moment_comment, guest_book, guest_comment

### API Documentation
- Swagger UI available at `/doc.html` via Knife4j
- All endpoints documented with OpenAPI 3.0 annotations