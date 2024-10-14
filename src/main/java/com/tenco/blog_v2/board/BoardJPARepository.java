package com.tenco.blog_v2.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// @Repository // 생략 가능
public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    // 커스텀 쿼리 메서드 만들어보기
    // Board 와 User 엔티티를 조인하여 특정 Board 엔티티를 조회
    @Query("SELECT b FROM Board b JOIN FETCH b.user WHERE b.id= :id")
    Optional<Board> findByIdJoinUser (@Param("id") int id);

}
