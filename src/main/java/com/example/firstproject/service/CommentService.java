package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;  // 댓글 레파지토리 객체 주입

    @Autowired
    private ArticleRepository articleRepository;  // 게시글 레파지토리 객체 주입

    public List<CommentDto> comments(Long articleId) {
//        // 1. 댓글 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 2. 엔티티 -> Dto 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (Comment comment : comments) {
//            CommentDto dto = CommentDto.createCommentDto(comment);
//            dtos.add(dto);
//        }
        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream().map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)  // 부모 게시글 가져오기
                .orElseThrow(IllegalArgumentException::new);  // 없으면 에러 발생

        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        
        // 3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        
        // 4. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }
}
