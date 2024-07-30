package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateDto;
import com.beyond.board.author.dto.AuthorUpdateDto;
import com.beyond.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    //    저장 및 detail조회
    @Test
    void saveAndFind() {
        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("hongildong", "hong@gmail.com", "12345", Role.ADMIN);
        Author author = authorService.authorCreate(authorDto);
        Author authorDetail = authorService.authorFindByEmail("hong@gmail.com");
        Assertions.assertEquals(authorDetail.getEmail(), authorDto.getEmail());
    }


    //    update 검증
    @Test
    void updateTest(){
//        객체 create
        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("hongildong", "hong@gmail.com", "12345", Role.ADMIN);
        Author author = authorService.authorCreate(authorDto);

//        수정작업(name, password)
        authorService.update(author.getId(), new AuthorUpdateDto("hong", "121212"));

//        수정후 재조회 : name, password가 맞는지 각각 검증
        Author savedAuthor = authorService.authorFindByEmail("hong@gmail.com");
        Assertions.assertEquals("hong", savedAuthor.getName());
        Assertions.assertEquals("121212", savedAuthor.getPassword());

    }
}