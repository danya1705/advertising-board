package ru.skypro.homework.service;

import liquibase.pro.packaged.A;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.*;
import ru.skypro.homework.repository.AdsRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AdsService {

    private final AdsRepository adsRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdsMapper mapper;
    private final AdsListMapper listMapper;
    private final Logger logger = Logger.getLogger("AdsServiceLogger");

    public AdsService(
            AdsRepository adsRepository,
            UserService userService,
            ImageService imageService,
            AdsMapper mapper,
            AdsListMapper listMapper) {
        this.adsRepository = adsRepository;
        this.userService = userService;
        this.imageService = imageService;
        this.mapper = mapper;
        this.listMapper = listMapper;
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

    public ResponseWrapperAdsDto getAdsByNamePart(String namePart) {
        return listMapper.toResponseWrapperAdsDto(adsRepository.findByTitleContaining(namePart));
    }

    public List<AdsDto> getAdsAll(){
        List<Ads> ads = adsRepository.findAll();
        List<AdsDto> adsList = new ArrayList<>();
        for (Ads ad : ads) {
            AdsDto adDto = new AdsDto();
            adsList.add(adDto);
        }
        return adsList;
    }

    public Ads getAdsById(Integer id) {
        return adsRepository.findById(id)
                .orElseGet(() -> {
                            logger.info("Ads with id=" + id + " noy found");
                            return null;
                        }
                );
    }

    public ResponseEntity<AdsDto> deleteAds(Integer id) {
        Optional<Ads> optionalAds = adsRepository.findById(id);
        if (optionalAds.isPresent()) {
            Ads ads = optionalAds.get();
            adsRepository.delete(ads);
            return new ResponseEntity<>(new AdsDto(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public AdsDto updateAds(Integer id, CreateAdsDto createAdsDto) {
        Ads ad = mapper.toAds(createAdsDto);
        User author = userService.getUserById(id);
        ad.setAuthor(author);
        return mapper.toDto(adsRepository.save(ad));
    }

    public AdsDto getAdsInfo(int adsId) {
        Ads ads = getAdsById(adsId);
        AdsMapperImpl adsMapper = new AdsMapperImpl();
        return adsMapper.toDto(ads);
    }

}
