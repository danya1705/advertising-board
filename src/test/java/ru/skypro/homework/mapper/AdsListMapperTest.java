package ru.skypro.homework.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Ads;

import java.util.List;

class AdsListMapperTest {

    public static final int FIRST_PK = 111;
    public static final int SECOND_PK = 222;
    public static final int THIRD_PK = 333;
    private final AdsListMapper adsListMapper = new AdsListMapperImpl(new AdsMapperImpl());

    @Test
    void toDtoTest() {

        Ads firstAd = new Ads();
        firstAd.setPk(FIRST_PK);

        Ads secondAd = new Ads();
        secondAd.setPk(SECOND_PK);

        Ads thirdAd = new Ads();
        thirdAd.setPk(THIRD_PK);

        ResponseWrapperAdsDto dto = adsListMapper.toResponseWrapperAdsDto(List.of(firstAd, secondAd, thirdAd));

        Assertions.assertThat(dto).isNotNull();
        Assertions.assertThat(dto.getCount()).isEqualTo(3);
        Assertions.assertThat(dto.getResults().get(0)).isInstanceOf(AdsDto.class);
        Assertions.assertThat(dto.getResults().get(0).getPk()).isEqualTo(FIRST_PK);
        Assertions.assertThat(dto.getResults().get(1).getPk()).isEqualTo(SECOND_PK);
        Assertions.assertThat(dto.getResults().get(2).getPk()).isEqualTo(THIRD_PK);

    }
}