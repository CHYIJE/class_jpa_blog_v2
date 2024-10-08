package com.tenco.blog_v1.User;

import lombok.Data;

@Data
public class UserDTO {

    // 정적 내부 클래스로 모으자
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public static class joinDTO {
        private String username;
        private String password;
        private String email;
    }

}
