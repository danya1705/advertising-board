package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.*;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.dto.Role;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AdsService {

    private final AdsRepository adsRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ImageService imageService;
    private final AdsMapper mapper;
    private final AdsListMapper listMapper;

    public Ads getAdById(Integer id) {
        return adsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ad with id=" + id + " not found"));
    }

    public byte[] editAdImage(Integer adId, MultipartFile imageFile) throws IOException {
        Ads ad = getAdById(adId);
        ad.setImage(imageService.uploadImage(imageFile));
        adsRepository.save(ad);
        return imageFile.getBytes();
    }

    public AdsDto createAd(CreateAdsDto createAdsDto, MultipartFile imageFile, String username) throws IOException {
        Ads ad = mapper.toAds(createAdsDto);
        User author = userService.getUserByUsername(username);
        ad.setAuthor(author);
        ad.setImage(imageService.uploadImage(imageFile));
        return mapper.toDto(adsRepository.save(ad));
    }

    public ResponseWrapperAdsDto getAdsByTitlePart(String titlePart) {
        return listMapper.toResponseWrapperAdsDto(adsRepository.findByTitleContaining(titlePart));
    }

    public ResponseWrapperAdsDto getAdsAll() {
        List<Ads> ads = adsRepository.findAll();
        return listMapper.toResponseWrapperAdsDto(ads);
    }

    public FullAdsDto getAdInfo(Integer id) {
        return mapper.toFullAdsDto(getAdById(id));
    }

    public boolean deleteAd(Integer id, String username) {
        User user = userService.getUserByUsername(username);
        Ads ad = getAdById(id);
        if (user.equals(ad.getAuthor()) || user.getRole() == Role.ADMIN) {
            commentRepository.deleteAll(ad.getCommentsList());
            adsRepository.delete(ad);
            return true;
        } else {
            return false;
        }
    }

    public Optional<AdsDto> updateAd(Integer id, CreateAdsDto createAdsDto, String username) {
        User user = userService.getUserByUsername(username);
        Ads ad = getAdById(id);
        if (user.equals(ad.getAuthor()) || user.getRole() == Role.ADMIN) {
            mapper.updateAds(createAdsDto, ad);
            return Optional.of(mapper.toDto(adsRepository.save(ad)));
        } else {
            return Optional.empty();
        }
    }

    public ResponseWrapperAdsDto getAdsMe(String username) {
        List<Ads> ads = adsRepository.findByAuthor_UserName(username);
        return listMapper.toResponseWrapperAdsDto(ads);
    }

}
