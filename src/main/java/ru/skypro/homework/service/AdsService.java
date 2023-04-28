package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.repository.AdsRepository;

import java.util.logging.Logger;

@Service
public class AdsService {

    private final AdsRepository adsRepository;
    private final Logger logger = Logger.getLogger("AdsServiceLogger");

    public AdsService(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

    public Ads getAdById(Integer id) {
        return adsRepository.findById(id)
                .orElseGet(() -> {
                            logger.info("Ad with id=" + id + " noy found");
                            return null;
                        }
                );
    }
}
