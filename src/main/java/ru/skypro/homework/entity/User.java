package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_ads")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String image;

    @Enumerated(EnumType.STRING)
    private Role role;


}
