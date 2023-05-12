package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Ads;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ads,Integer> {
    List<Ads> findByTitleContaining(String namePart);
    List<Ads> findByAuthor_Id(Integer id);
    List<Ads> findByAuthor_UserName(String username);
}
