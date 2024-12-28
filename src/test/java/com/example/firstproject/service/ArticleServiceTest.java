package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void index(){
        // 1. 예상 데이터
        Article a = new Article(1L, "가가가", "11111");
        Article b = new Article(2L, "나나나", "22222");
        Article c = new Article(3L, "다다다", "33333");
        List<Article> expected = new ArrayList<>(Arrays.asList(a,b,c));
        // 2. 실제 데이터
        List<Article> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가", "11111");
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;  // 예상 데이터도 null반환
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 존재하지 않는 id로 조회시 실제 null을 반환
        // 3. 비교 및 검증
        assertEquals(expected, article);  // 테스트는 통과됨
    }

    @Test
    void create_success() {
        // 1. 예상 데이터
        String title = "라라라";
        String content = "44444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_fail() {
        // 1. 예상 데이터
        Long id = 4L;
        String title = "라라라";
        String content = "44444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

}