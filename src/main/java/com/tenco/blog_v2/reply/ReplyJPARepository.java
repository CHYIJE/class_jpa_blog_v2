package com.tenco.blog_v2.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 어노테이션 생략 가능
public interface ReplyJPARepository extends JpaRepository<Reply, Integer> {
    // 기복적인 주요 메서드 제공 받음 (구현체를 만들어 준다.)

    // 1. 커스텀 쿼리를 만들어본다. 어노테이션 사용
    // boardId를 통해서 리플정보를 조회하는 기능
    @Query("SELECT r FROM Reply r WHERE r.board.Id = :boardId")
    List<Reply> findByBoardId(@Param("boardId") Integer boardId); // 알아서 메서드의 바디를 만들어 준다.
}
