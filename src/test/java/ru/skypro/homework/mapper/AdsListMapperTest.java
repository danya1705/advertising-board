package ru.skypro.homework.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;

import java.util.List;

class AdsListMapperTest {

    public static final int FIRST_ID = 123;
    public static final int FIRST_PK = 111;
    public static final int SECOND_PK = 222;
    public static final int THIRD_PK = 333;
    private final AdsListMapper adsListMapper = new AdsListMapperImpl(new AdsMapperImpl());

    @Test
    void toDtoTest() {

        User user = new User();
        user.setId(FIRST_ID);

        Ads firstAd = new Ads();
        firstAd.setPk(FIRST_PK);
        firstAd.setAuthor(user);

        Ads secondAd = new Ads();
        secondAd.setPk(SECOND_PK);
        secondAd.setAuthor(user);

        Ads thirdAd = new Ads();
        thirdAd.setPk(THIRD_PK);
        thirdAd.setAuthor(user);

        user.setAdsList(List.of(firstAd, secondAd, thirdAd));

        ResponseWrapperAdsDto dto = adsListMapper.toDto(user);

        Assertions.assertThat(dto).isNotNull();
        Assertions.assertThat(dto.getCount()).isEqualTo(3);
        Assertions.assertThat(dto.getResults().get(0)).isInstanceOf(AdsDto.class);

    }
}