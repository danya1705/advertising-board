package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CommentDto;


import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {
    @Operation(summary = "Получить все объявления", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )})
    })

    @GetMapping
    public ResponseEntity<List<AdsDto>> getAdsAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Добавить объявление", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @PostMapping
    public AdsDto addAd(@RequestBody AdsDto adsDto) {
        return new AdsDto();
    }

    @Operation(summary = "Получить информацию об объявлении", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<AdsDto>> getAds(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Удалить объявление", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AdsDto> deleteAds(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Обновить информацию об объявлении", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @PatchMapping("/{id}")
    public AdsDto updateAds(@PathVariable Long id) {
        return new AdsDto();
    }

    @Operation(summary = "Получить объявление авторизованного пользователя", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsAuthorizedUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Обновить картинку объявления", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )})
    })
    @PatchMapping("/id/image")
    public AdsDto updateImage(@PathVariable Long id) {
        return new AdsDto();
    }



}
