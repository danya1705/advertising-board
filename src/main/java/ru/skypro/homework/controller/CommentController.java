package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateCommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.service.CommentService;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Получить комментарии объявления", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseWrapperCommentDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content})
    })
    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperCommentDto> getComments(@PathVariable Integer id) {
            return ResponseEntity.ok(commentService.getCommentsByAdId(id));
    }

    @Operation(summary = "Добавить комментарий к объявлению", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content})
    })
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer id, @RequestBody CreateCommentDto createCommentDto) {
        return ResponseEntity.ok(commentService.addComment(id,createCommentDto));
    }

    @Operation(summary = "Удалить комментарий", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content})
    })
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновить комментарий", tags = "Комментарии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class)
                    )}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content}),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content})
    })
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(commentId,commentDto));
    }
}
