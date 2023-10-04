package models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder

public class User {
    private Long id;
    private String first_name;
    private String last_name;
    private String password;
    private Integer age;
    private String username;
}