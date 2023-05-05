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
public class CommentController {

    @Operation(summary = "Получить комментарии объявления", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )})
    })
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsAds(@PathVariable Integer id) {
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Добавить комментарий к объявлению", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )})

    })
    @PostMapping("/{id}/comments")
    public CommentDto addCommentAds(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        System.out.println("Comment add");
        return new CommentDto();
    }

    @Operation(summary = "Удалить комментарий", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )})
    })
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> deleteCommentAds(@PathVariable Integer adId, @PathVariable Integer commentId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Обновить комментарий", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )})
    })
    @PatchMapping("/{adId}/comments/{commentId}")
    public CommentDto updateCommentAds(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody CommentDto commentDto) {
        System.out.println("Update comment");
        return new CommentDto();
    }
}
