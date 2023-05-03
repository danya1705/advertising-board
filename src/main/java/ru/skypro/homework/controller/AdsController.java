package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.service.AdsService;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@Log
public class AdsController {

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

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
            @ApiResponse(responseCode = "201", description = "Created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdsDto> addAd(
            @RequestPart(name = "properties") CreateAdsDto createAdsDto,
            @RequestPart(name = "image") MultipartFile imageFile) throws IOException {
        return new ResponseEntity<>(adsService.createAd(createAdsDto, imageFile), HttpStatus.CREATED);
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
    public AdsDto updateAds(@PathVariable Long id, @RequestBody CreateAdsDto createAdsDto) {
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
                    content = {@Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping(value = "/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable int id, @RequestParam MultipartFile image) throws IOException {
        log.info("Updating image for advertisement with id = " + id);
        return ResponseEntity.ok(adsService.editAdImage(id, image));
    }
}
