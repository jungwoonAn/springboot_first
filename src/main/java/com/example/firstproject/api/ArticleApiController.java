package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    // GET
    // 모든 게시글 조회
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    // 단일 게시글 조회
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    // 게시글 생성
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    // 게시글 수정
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    // 게시글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    // 트랜잭션 맛보기
    // 1. 게시판에 데이터 3개를 하넊번에 생성 요청하기
    // 2. 데이터를 DB에 저장하는 과정에서 의도적으로 오류 발생시키기
    // 3. 어떻게 롤백되는지 확인하기
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        
        List<Article> createdList = articleService.createArticles(dtos);

        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // ===========================================
    // service 계층 나누기 전 코드
    // ===========================================

//    @Autowired
//    private ArticleRepository articleRepository;
//
//    // GET
//    // 모든 게시글 조회
//    @GetMapping("/api/articles")
//    public List<Article> index() {
//        return articleRepository.findAll();
//    }
//
//    // 단일 게시글 조회
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    // POST
//    // 게시글 생성
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//
//    // PATCH
//    // 게시글 수정
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
//        // 1. DTO -> 엔티티 변환하기
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
//        // 2. id에 해당하는 게시글 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못된 요청 처리하기
//        if(target == null || !article.getId().equals(id)) {
//            // 400, 잘못된 요청 응답!
//            log.info("잘못된 요청 응답! id: {}, article: {}", id, article.toString());
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 4. 업데이트 및 정상 응답(200)하기
//        target.patch(article);  // 일부만 수정할 수 있도록 Article 엔티티에 메서드 추가
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    // DELETE
//    // 게시글 삭제
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // 1. 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 2. 잘못된 요청 처리하기
//        if(target == null || !articleRepository.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 3. 대상 삭제하기
//        articleRepository.delete(target);
////        return ResponseEntity.status(HttpStatus.OK).body(null);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
