package com.example.basic.repository;

import com.example.basic.domain.entity.SuperCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SuperCarRepository extends JpaRepository<SuperCar, Long> {
    public List<SuperCar> findBySuperCarReleaseDate(LocalDateTime superCarReleaseDate);
    public List<SuperCar> findSuperCarByColorContaining(String superCarColor);


    @Query("select s from SuperCar s where s.superCarName like :keyword")
    public List<SuperCar> find(@Param("keywoed") String keyword);

    public List<SuperCar> findSuperCarBySuperCarReleaseDateBetween(LocalDateTime startDate, LocalDateTime endDate);






}
