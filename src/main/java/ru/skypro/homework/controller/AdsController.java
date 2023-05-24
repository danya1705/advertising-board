package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@AllArgsConstructor
@Slf4j
public class AdsController {

    private final AdsService adsService;

    @Operation(summary = "Получить все объявления", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseWrapperAdsDto.class)
                    )})
    })
    @GetMapping
    public ResponseEntity<ResponseWrapperAdsDto> getAdsAll() {
        return ResponseEntity.ok(adsService.getAdsAll());
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
            @RequestPart(name = "image") MultipartFile imageFile,
            Authentication authentication) throws IOException {
        return new ResponseEntity<>(adsService.createAd(createAdsDto, imageFile, authentication.getName()), HttpStatus.CREATED);
    }

    @Operation(summary = "Получить информацию об объявлении", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FullAdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content})
    })
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(adsService.getAdInfo(id));
    }

    @Operation(summary = "Удалить объявление", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content = {@Content()}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable Integer id, Authentication authentication) throws IOException {
        if (adsService.deleteAd(id, authentication.getName())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Обновить информацию об объявлении", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content()})
    })
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAd(@PathVariable Integer id,
                                           @RequestBody CreateAdsDto createAdsDto,
                                           Authentication authentication) {
        return adsService.updateAd(id, createAdsDto, authentication.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    @Operation(summary = "Получить объявления авторизованного пользователя", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseWrapperAdsDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
    })
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adsService.getAdsMe(authentication.getName()));
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

    @Operation(summary = "Поиск объявления по названию", tags = "Объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AdsDto.class))
                    )}
            )
    })
    @GetMapping("/title/{titlePart}")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsByNamePart(@PathVariable String titlePart) {
        return ResponseEntity.ok(adsService.getAdsByTitlePart(titlePart));
    }
}
