package com.beyond.board.post.domain;

import com.beyond.board.author.domain.Author;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.dto.PostDetResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 3000)
    private String contents;

    // 연관 관계의 주인은 fk가 있는 post
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id") // db에 author_id로 들어가야 하니깐
    private Author author;

    private String appointment;
    private LocalDateTime appointmentTime;

    public PostListResDto listFromEntity(){
        return PostListResDto.builder()
                .id(this.id)
                .title(this.title)
                //.author(this.author)
                .author_email(this.author.getEmail())
                .build();
    }

    public PostDetResDto detFromEntity(){
        return PostDetResDto.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .author_email(this.author.getEmail())
                .createdTime(this.getCreatedTime())
                .updatedTime(this.getUpdatedTime())
                .build();
    }

    public void updatePost(PostUpdateDto dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }

    public void updateAppointment(String yn){
        this.appointment = yn;
    }
}
