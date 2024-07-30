package com.beyond.board.author.repository;

import com.beyond.board.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // findBy컬럼명의 규칙으로 자동으로 where 조건문을 사용한 쿼리 생성
    // 그외 : findByNameAndEmail, findByNameOrEmail
    // findByAgeBetween(int start, int end) => a와 b 사이에 숫자를 조회
    // findByAgeLessThan(int age) => ~보다 작은걸 조회, findByAgeGreaterThan(int age)
    // findByAgeIsNull, findByAgeIsNotNull
    // findAllOrderByEmail();
    Optional<Author> findByEmail(String email);

    // List<Author> findByNameIsNull(); // 매개변수 필요없음

    // List<Author> findAllOrderByEmail();

}
