package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;

import java.io.IOException;
import java.util.logging.Logger;

@Service
public class AdsService {

    private final AdsRepository adsRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdsMapper mapper;
    private final Logger logger = Logger.getLogger("AdsServiceLogger");

    public AdsService(AdsRepository adsRepository, UserService userService, ImageService imageService, AdsMapper mapper) {
        this.adsRepository = adsRepository;
        this.userService = userService;
        this.imageService = imageService;
        this.mapper = mapper;
    }

    public Ads getAdById(Integer id) {
        return adsRepository.findById(id)
                .orElseGet(() -> {
                            logger.info("Ad with id=" + id + " noy found");
                            return null;
                        }
                );
    }

    public byte[] editAdImage(Integer adId, MultipartFile imageFile) throws IOException {
        Ads ad = getAdById(adId);
        ad.setImage(imageService.uploadImage(imageFile));
        adsRepository.save(ad);
        return imageFile.getBytes();
    }

    public AdsDto createAd(CreateAdsDto createAdsDto, MultipartFile imageFile) throws IOException {
        Ads ad = mapper.toAds(createAdsDto);
        User author = userService.getUserById(1);
        ad.setAuthor(author);
        ad.setImage(imageService.uploadImage(imageFile));
        return mapper.toDto(adsRepository.save(ad));
    }
}
