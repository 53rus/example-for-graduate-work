package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
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
