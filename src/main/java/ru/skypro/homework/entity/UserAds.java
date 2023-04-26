package ru.skypro.homework.entity;

import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Entity
public class UserAds {

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
