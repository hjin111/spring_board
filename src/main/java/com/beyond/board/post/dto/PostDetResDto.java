package com.beyond.board.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDetResDto {

    private Long id;
    private String title;
    private String contents;
    private String author_email;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}
