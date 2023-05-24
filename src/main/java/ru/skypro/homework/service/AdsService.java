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
import ru.skypro.homework.entity.Image;
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

    /**
     * Получение объявления по id
     *
     * @param id ID объявления
     * @return Ads
     */
    public Ads getAdById(Integer id) {
        return adsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ad with id=" + id + " not found"));
    }

    /**
     * Изменение изображения объявления
     *
     * @param adId      ID объявления
     * @param imageFile изображение
     * @return byte[]
     */
    public byte[] editAdImage(Integer adId, MultipartFile imageFile) throws IOException {
        Ads ad = getAdById(adId);
        Image oldImage = ad.getImage();
        ad.setImage(imageService.uploadImage(imageFile));
        adsRepository.save(ad);
        imageService.deleteImage(oldImage);
        return imageFile.getBytes();
    }

    /**
     * Создание объявления
     *
     * @param createAdsDto DTO для создания объявления
     * @param imageFile    изображение
     * @param username     username пользователя
     * @return AdsDto
     */
    public AdsDto createAd(CreateAdsDto createAdsDto, MultipartFile imageFile, String username) throws IOException {
        Ads ad = mapper.toAds(createAdsDto);
        User author = userService.getUserByUsername(username);
        ad.setAuthor(author);
        ad.setImage(imageService.uploadImage(imageFile));
        return mapper.toDto(adsRepository.save(ad));
    }

    /**
     * Поиск объявления по части имени
     *
     * @param titlePart часть названия для поиска
     * @return ResponseWrapperAdsDto
     */
    public ResponseWrapperAdsDto getAdsByTitlePart(String titlePart) {
        return listMapper.toResponseWrapperAdsDto(adsRepository.findByTitleContainingIgnoreCase(titlePart));
    }

    /**
     * Получение списка всех объявлений
     *
     * @return ResponseWrapperAdsDto
     */
    public ResponseWrapperAdsDto getAdsAll() {
        List<Ads> ads = adsRepository.findAll();
        return listMapper.toResponseWrapperAdsDto(ads);
    }

    public FullAdsDto getAdInfo(Integer id) {
        return mapper.toFullAdsDto(getAdById(id));
    }

    /**
     * Удаление объявления
     *
     * @param id       ID объявления
     * @param username username пользователя
     * @return boolean
     */
    public boolean deleteAd(Integer id, String username) throws IOException {
        User user = userService.getUserByUsername(username);
        Ads ad = getAdById(id);
        if (user.equals(ad.getAuthor()) || user.getRole() == Role.ADMIN) {
            Image image = ad.getImage();
            commentRepository.deleteAll(ad.getCommentsList());
            adsRepository.delete(ad);
            imageService.deleteImage(image);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Обновление объявления
     *
     * @param id           ID объявления
     * @param createAdsDto DTO для обновления
     * @param username     username пользователя
     * @return Optional
     */
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

    /**
     * Получение списка объявлений
     *
     * @param username username пользователя
     * @return ResponseWrapperAdsDto
     */
    public ResponseWrapperAdsDto getAdsMe(String username) {
        List<Ads> ads = adsRepository.findByAuthor_UserName(username);
        return listMapper.toResponseWrapperAdsDto(ads);
    }

}
