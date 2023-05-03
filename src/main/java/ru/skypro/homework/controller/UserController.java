package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@Log
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Обновление пароля", tags = "Пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content)
    })
    @PostMapping("/set_password")
    public NewPasswordDto setPassword() {
        return new NewPasswordDto();
    }

    @Operation(summary = "Получить информацию об авторизованном пользователе", tags = "Пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content)
    })
    @GetMapping("/me")
    public UserDto getUser() {
        return new UserDto();
    }

    @Operation(summary = "Обновить информацию об авторизованном пользователе", tags = "Пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content)
    })
    @PatchMapping("/me")
    public UserDto updateUser(@RequestBody UserDto user) {
        return new UserDto();
    }

    @Operation(summary = "Обновить аватар авторизованного пользователя", tags = "Пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK"),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized")
    })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity updateUserImage(@RequestParam MultipartFile image) throws IOException {
        int id = 1;
        log.info("Updating image for user with id = " + id);
        userService.editUserImage(id, image);
        return ResponseEntity.ok().build();
    }

}
