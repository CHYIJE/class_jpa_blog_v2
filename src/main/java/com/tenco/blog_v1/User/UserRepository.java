package com.tenco.blog_v1.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository // IoC
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    // 사용자 정보 수정 기능 만들기 (더티 체킹 사용)
    @Transactional // 더티 체킹 반영
    public User updateById(int id, String password, String email) {
        // id 값으로 영속성 컨텍스테 정보가 있는가? 확인
        User userEntity = findById(id);
        userEntity.setPassword(password);
        userEntity.setEmail(email);
        return userEntity; // 수정
    }

    public User findById(int id) {
        return em.find(User.class, id);
    }

    /**
     * 사용자 저장 메서드 (JPA API 사용)
     * @param user
     * @return 저장된 사용자 엔티티
     */
    @Transactional
    public User save(User user) {
        // JPQL은 INSERT구문을 직접 지원하지 않습니다.
        em.persist(user); // 영속화
        return user;
    }

    /**
     * 사용자 이름과 비밀번호로 사용자 조회
     * @param username
     * @param password
     * @return 조회된 User 엔티티, null
     */
    // DTO에 묶이면 재사용성이 떨어진다 풀어서 쓰는게 좋다
    public User findByUsernameAndPassword(String username, String password) {
        TypedQuery<User> jpql =
                em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        jpql.setParameter("username", username);
        jpql.setParameter("password", password);
        return jpql.getSingleResult();
    }


}
