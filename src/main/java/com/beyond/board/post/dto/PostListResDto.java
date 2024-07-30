package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostListResDto {

    private Long id;
    private String title;
    // Author 객체 그 자체를 return 하게 되면 Author 안에 Post가 재참조되어 순환참조 이슈 발생.
    // private Author author;
    private String author_email;

}
