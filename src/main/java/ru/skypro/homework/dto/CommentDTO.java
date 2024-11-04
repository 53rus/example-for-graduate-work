package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    /**
     * Id автора комментария
     */
    private Integer author;
    /**
     * Ссылка на аватар автора комментария
     */
    private String authorImage;
    /**
     * Имя создателя комментария
     */
    private String authorFirstName;
    /**
     * Дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
     */
    private LocalDateTime createdAt;
    /**
     * Id комментария
     */
    private Integer pk;
    /**
     * Текст комментария
     */
    private String text;
}
