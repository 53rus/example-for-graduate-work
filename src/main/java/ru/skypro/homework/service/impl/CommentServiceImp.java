package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdAdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserService userService;

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Comments getComments(Integer id) {
        List<CommentDTO> commetList = commentRepository.findAll().stream()
                .filter(comment -> comment.getAd().getPk().equals(id))
                .map(CommentMapper::mapCommentToCommentDTO)
                .collect(Collectors.toList());
        return new Comments(commetList.size(), commetList);
    }

    /**
     *
     * @param id
     * @param comment
     * @param authentication
     * @return
     */
    @Override
    public CommentDTO addComment(Integer id,
                                 CreateOrUpdateComment comment,
                                 Authentication authentication) {

        LocalDateTime date = LocalDateTime.now();
        Ad ad = adRepository.findByPk(id).orElseThrow(AdAdNotFoundException::new);
        ;
        Comment newComment = new Comment();

        if (ad == null) {
            return null;
        }

        newComment.setText(comment.getText());
        newComment.setCreateAt(date);
        newComment.setUser(userService.getUser(authentication.getName()));
        newComment.setAd(ad);

        commentRepository.save(newComment);

        return CommentMapper.mapCommentToCommentDTO(newComment);
    }

    /**
     *
     * @param adId
     * @param commentId
     * @param authentication
     * @return
     */
    @Override
    @Transactional
    public HttpStatus deleteComment(Integer adId,
                                    Integer commentId,
                                    Authentication authentication) {
        Ad ad = adRepository.findByPk(adId).orElseThrow(AdAdNotFoundException::new);
        ;
        Comment comment = commentRepository.findByPk(commentId).orElseThrow(CommentNotFoundException::new);

        if (!comment.getAd().equals(ad)) {
            return HttpStatus.NOT_FOUND;
        }

        User user = userService.getUser(authentication.getName());
        Role role = user.getRole();
        boolean commentAuthor = (comment.getUser().equals(user));
        boolean permit = commentAuthor || role == Role.ADMIN;

        if (permit) {
            commentRepository.delete(comment);
            return HttpStatus.OK;
        } else {
            return HttpStatus.FORBIDDEN;
        }
    }

    /**
     *
     * @param adId
     * @param commentId
     * @param updateComment
     * @param authentication
     * @return
     */
    @Override
    public ResponseEntity<CommentDTO> updateComment(Integer adId,
                                                    Integer commentId,
                                                    CreateOrUpdateComment updateComment,
                                                    Authentication authentication) {

        Ad ad = adRepository.findByPk(adId).orElseThrow(AdAdNotFoundException::new);
        ;
        Comment comment = commentRepository.findByPk(commentId).orElseThrow(CommentNotFoundException::new);
        LocalDateTime date = LocalDateTime.now();

        if (!comment.getAd().equals(ad)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User user = userService.getUser(authentication.getName());
        Role role = user.getRole();
        boolean commentAuthor = (comment.getUser().equals(user));
        boolean permit = commentAuthor || role == Role.ADMIN;

        if (permit) {
            comment.setText(updateComment.getText());
            comment.setCreateAt(date);

            commentRepository.save(comment);

            return ResponseEntity.ok(CommentMapper.mapCommentToCommentDTO(comment));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
