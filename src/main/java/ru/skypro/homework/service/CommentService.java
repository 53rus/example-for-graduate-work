package ru.skypro.homework.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {
    Comments getComments(Integer id);

    CommentDTO addComment(Integer id, CreateOrUpdateComment comment, Authentication authentication);
    @Transactional
    HttpStatus deleteComment(Integer adId, Integer commentId, Authentication authentication);

    ResponseEntity<CommentDTO> updateComment(Integer adId, Integer commentId, CreateOrUpdateComment updateComment, Authentication authentication);


}
