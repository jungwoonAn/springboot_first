package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service  // servcie 객체 생성
@Log4j2
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;  // 게시글 레파지토리 객체 주입

    // 모든 게시글 조회
    public List<Article> index() {
        return articleRepository.findAll();
    }

    // 단일 게시글 조회
    public Article show(Long id) {
        return  articleRepository.findById(id).orElse(null);
    }

    // 게시글 생성
    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        // id는 데이터 생성할 때 넣을 필요가 없지만
        // article 객체에 id가 존재한다면 null을 반환하틑 코드 추가
        if(article.getId() != null){
            return null;
        }

        return articleRepository.save(article);
    }

    // 게시글 수정
    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        // 2. id에 해당하는 게시글 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if(target == null || !article.getId().equals(id)) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청 응답! id: {}, article: {}", id, article.toString());
            return null;
        }

        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article);  // 일부만 수정할 수 있도록 Article 엔티티에 메서드 추가
        Article updated = articleRepository.save(target);
        return updated;
    }

    // 게시글 삭제
    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기
        if(target == null || !articleRepository.existsById(id)) {
            return null;
        }

        // 3. 대상 삭제하기
        articleRepository.delete(target);
        return target;
    }

    // 데이터 리스트 DB에 저장
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 리스트를 엔티티 리스트로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .toList();

        // 2. 엔티티 리스트를 DB에 저장하기
//        articleList.stream()
//                .forEach(article -> articleRepository.save(article));
        articleRepository.saveAll(articleList);

        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("실패!"));

        // 4. 결과 값 반환하기
        return articleList;
    }
}
