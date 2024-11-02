package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;
@Component
public class CommentMapper {
    public static Comment mapCommentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setPk(commentDTO.getPk());
        comment.setCreateAt(commentDTO.getCreatedAt());
        comment.setText(commentDTO.getText());
        return comment;
    }

    public static CommentDTO mapCommentToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthor(comment.getUser().getId());
        commentDTO.setAuthorImage(comment.getUser().getImage());
        commentDTO.setAuthorFirstName(comment.getUser().getFirstName());
        commentDTO.setCreatedAt(comment.getCreateAt());
        commentDTO.setText(comment.getText());
        commentDTO.setPk(comment.getPk());
        return commentDTO;
    }

    public static Comment mapCreateOrUpdateCommentToComment(CreateOrUpdateComment commentDTO) {
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        return comment;
    }

    public static CreateOrUpdateComment CommentToCreateOrUpdateComment(Comment comment) {
        CreateOrUpdateComment commentDTO = new CreateOrUpdateComment();
        commentDTO.setText(comment.getText());
        return commentDTO;
    }
}
