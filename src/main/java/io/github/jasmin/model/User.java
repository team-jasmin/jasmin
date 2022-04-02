package io.github.jasmin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class User {
    private int userNo;
    private String userName;
    private String userId;
    private String userPassword;

    public User(){
    }

}
