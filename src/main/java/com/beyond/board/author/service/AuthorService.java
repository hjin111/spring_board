package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorDetailDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
// 조회 작업시에 readOnly 설정하면 성능 향상
// 다만, 저장 작업시에 Transactional 필요 ( 별도로 저장 작업 할 create 위에 Transactional 붙이기 )
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author authorCreate(AuthorSaveReqDto dto){ // 굳이 사용자한테 return 객체를 주지는 않을거임

        if(authorRepository.findByEmail(dto.getEmail()).isPresent()){
            throw  new IllegalArgumentException("이미 존재하는 email 입니다");
        }
        if(dto.getPassword().length() < 8){
            throw new IllegalArgumentException("이미 존재하는 email입니다.");
        }
        Author author = dto.toEntity();
        // cascade persist 테스트. remove 테스트는 회원 삭제로 대체
        author.getPosts().add(Post.builder().title("가입인사").author(author).contents("안녕하세요" + dto.getName() + "입니다.").build());
        Author savedAuthor = authorRepository.save(author);
        return savedAuthor;
    }

    public List<AuthorListResDto> authorList(){
        List<AuthorListResDto> authorListResDtos = new ArrayList<>();
        List<Author> authors = authorRepository.findAll();
        for(Author a : authors){
            AuthorListResDto authorListResDto = a.fromEntity();
            authorListResDtos.add(authorListResDto);
        }

        return authorListResDtos;
    }

    public AuthorDetailDto authorDetail(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("member is not found."));
        AuthorDetailDto authorDetailDto = new AuthorDetailDto();
        return authorDetailDto.fromEntity(author);
    }

    public Author authorFindByEmail(String email){
        Author author = authorRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("해당 email의 사용자는 없습니다."));
        return author;
    }

    @Transactional
    public void delete(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("author is not found"));
        authorRepository.delete(author);
    }

//    @Transactional
//    public void delete(Long id){
//        authorRepository.deleteById(id);
//    }

    @Transactional
    public void update(Long id, AuthorUpdateDto dto){
        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("author is not found"));
        author.updateAuthor(dto);
        // jpa가 특정 엔티티의 변경을 자동으로 인지하고 변경사항을 DB에 반영하는 것이 dirtychecking(변경감지) - 이때는 반드시 @Transactional 붙어야 함
        // 더티체킹시 @Transactional 어노테이션 필요
        // authorRepository.save(author);
    }

}
