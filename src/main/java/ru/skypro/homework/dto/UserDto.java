package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String image;
}
