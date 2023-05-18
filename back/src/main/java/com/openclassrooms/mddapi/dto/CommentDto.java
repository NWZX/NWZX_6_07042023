package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;

    @NonNull
    private ArticleLtdDto article;

    @NonNull
    private UserLtdDto user;

    @NonNull
    @Size(max = 50)
    @Email
    private String title;

    @NonNull
    @Size(max = 500)
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
