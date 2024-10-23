package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class Comments {
    /**
     * Общее количество комментариев
     */
    private Integer count;
    /**
     * Список всех комментариев
     */
    private List<CommentDTO> results;
}
