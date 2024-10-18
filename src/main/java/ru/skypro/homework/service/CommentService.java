package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {
    Comments getComments(Integer id);

    CommentDTO addComment(Integer Id, CreateOrUpdateComment comment);

    void deleteComment(Integer adId, Integer commentId);

    CommentDTO updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment);


}
