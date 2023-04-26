package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.UserAds;

public interface UserAdsRepository extends JpaRepository<UserAds,Integer> {
}
