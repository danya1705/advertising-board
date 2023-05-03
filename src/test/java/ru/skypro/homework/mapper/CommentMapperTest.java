package ru.skypro.homework.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.UserService;

import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class CommentMapperTest {

    public static final int COMMENT_ID = 444;
    public static final int WRONG_COMMENT_ID = 4;
    public static final String TEXT = "Some text";
    public static final String NEW_TEXT = "Some other text";
    public static final long DATE_MILLIS = 123456789L;
    public static final long WRONG_DATE_MILLIS = 987654321L;
    public static final int USER_ID = 222;
    public static final int WRONG_USER_ID = 2;
    public static final String USER_FIRST_NAME = "firstName";
    public static final String WRONG_USER_FIRST_NAME = "wrongFirstName";
    public static final String IMAGE_URL = "Some URL";
    public static final String WRONG_IMAGE_URL = "Some wrong URL";
    public static final int AD_ID = 333;
    @Mock
    private UserService userService;
    @Mock
    private AdsService adsService;
    @InjectMocks
    private CommentMapperImpl commentMapper;

    @Test
    void toDtoTest() {

        Image image = new Image();
        image.setUrl(IMAGE_URL);

        User user = new User();
        user.setId(USER_ID);
        user.setFirstName(USER_FIRST_NAME);
        user.setImage(image);

        Comment comment = new Comment();
        comment.setAd(new Ads());
        comment.setCommentId(COMMENT_ID);
        comment.setAuthor(user);
        comment.setCreatedAt(DATE_MILLIS);
        comment.setText(TEXT);

        CommentDto dto = commentMapper.toDto(comment);

        Assertions.assertThat(dto).isNotNull();
        Assertions.assertThat(dto.getPk()).isEqualTo(COMMENT_ID);
        Assertions.assertThat(dto.getText()).isEqualTo(TEXT);
        Assertions.assertThat(dto.getCreatedAt()).isEqualTo(DATE_MILLIS);
        Assertions.assertThat(dto.getAuthor()).isEqualTo(USER_ID);
        Assertions.assertThat(dto.getAuthorFirstName()).isEqualTo(USER_FIRST_NAME);
        Assertions.assertThat(dto.getAuthorImage()).isEqualTo(IMAGE_URL);

    }

    @Test
    void updateCommentTest() {

        Image image = new Image();
        image.setUrl(IMAGE_URL);

        Image wrongImage = new Image();
        image.setUrl(WRONG_IMAGE_URL);

        User user = new User();
        user.setFirstName(USER_FIRST_NAME);
        user.setImage(image);

        User wrongUser = new User();
        user.setFirstName(WRONG_USER_FIRST_NAME);
        user.setImage(wrongImage);

        Comment comment = new Comment();
        comment.setAd(new Ads());
        comment.setCommentId(COMMENT_ID);
        comment.setAuthor(user);
        comment.setCreatedAt(DATE_MILLIS);
        comment.setText(TEXT);

        CommentDto dto = new CommentDto();
        dto.setPk(WRONG_COMMENT_ID);
        dto.setAuthor(WRONG_USER_ID);
        dto.setAuthorFirstName(WRONG_USER_FIRST_NAME);
        dto.setAuthorImage(WRONG_IMAGE_URL);
        dto.setCreatedAt(WRONG_DATE_MILLIS);
        dto.setText(NEW_TEXT);

        commentMapper.updateComment(dto, comment);

        Assertions.assertThat(comment).isNotNull();
        Assertions.assertThat(comment.getCommentId()).isEqualTo(COMMENT_ID);
        Assertions.assertThat(comment.getAuthor()).isEqualTo(user);
        Assertions.assertThat(comment.getCreatedAt()).isEqualTo(DATE_MILLIS);
        Assertions.assertThat(comment.getText()).isEqualTo(NEW_TEXT);

    }

    @Test
    void toCommentTest() {

        CommentDto dto = new CommentDto();
        dto.setAuthor(USER_ID);
        dto.setText(TEXT);

        User user = new User();
        user.setId(USER_ID);

        Ads ad = new Ads();
        ad.setPk(AD_ID);

        Mockito.when(userService.getUserById(eq(USER_ID))).thenReturn(user);
        Mockito.when(adsService.getAdById(eq(AD_ID))).thenReturn(ad);

        Comment comment = commentMapper.toComment(dto, AD_ID);

        Assertions.assertThat(comment).isNotNull();
        Assertions.assertThat(comment.getAd()).isEqualTo(ad);
        Assertions.assertThat(comment.getAuthor()).isEqualTo(user);
        Assertions.assertThat(comment.getText()).isEqualTo(TEXT);

    }
}