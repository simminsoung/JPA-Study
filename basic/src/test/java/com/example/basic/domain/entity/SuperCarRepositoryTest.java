package com.example.basic.domain.entity;


import com.example.basic.repository.MemberRepository;
import com.example.basic.repository.SuperCarRepository;
import com.example.basic.type.MemberType;
import com.example.basic.type.SuperCarBrand;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SuperCarRepositoryTest {

    @Autowired
    private SuperCarRepository superCarRepository;

    @Test
    public void saveTest(){
        SuperCar bentley = new SuperCar();
        SuperCar lamborghini = new SuperCar();
        LocalDateTime bentleyReleaseDate = LocalDateTime.of(2018, 12, 4, 0, 0);
        LocalDateTime lamborghiniReleaseDate = LocalDateTime.of(2022, 4, 25, 0, 0);
        bentley.create(SuperCarBrand.BENTLEY, "White", "GT", 250_000_000L, bentleyReleaseDate);
        lamborghini.create(SuperCarBrand.LAMBORGHINI, "Yellow", "Urus", 450_000_000L, lamborghiniReleaseDate);
        superCarRepository.save(bentley);
        superCarRepository.save(lamborghini);
    }

    //
//    @Test
//    public void dummySaveTest(){
//        SuperCar bentley = new SuperCar();
//        LocalDateTime bentleyReleaseDate = LocalDateTime.of(2019, 12, 4, 0, 0);
//        bentley.create(SuperCarBrand.BENTLEY, "White", "GT", 350_000_000L, bentleyReleaseDate);
//        log.info("superCar : " + superCarRepository.save(bentley));
//
//    }


    @Test
    public void deleteByIdTest(){
        superCarRepository.deleteById(2L);
    }

    @Test
    public void findByIdTest(){
        assertThat(superCarRepository.findById(1l).get().getSuperCarName()).isEqualTo("GT");
    }

    @Test
    public void findAllTest(){
        superCarRepository.findAll().stream().map(SuperCar::toString).forEach(log::info);
    }

    @Test
    public void findCountTest(){
        log.info("count: "+superCarRepository.count());
    }

    @Test
    public void findSuperCarByReleaseDateTest(){
        LocalDateTime time = LocalDateTime.of(2019,12,04,0,0);
        assertThat(superCarRepository.findBySuperCarReleaseDate(time).get(0).getSuperCarName()).isEqualTo("GT");
    }

    @Test
    public void indSuperCarBySuperCarReleaseDateBetween(){
        LocalDateTime startDate = LocalDateTime.of(2019, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 0, 0);
        superCarRepository.findSuperCarBySuperCarReleaseDateBetween(startDate , endDate);

    }

//    @Test
//    public void findBySuperCarColorContainingTest(){
//        superCarRepository.findBySuperCarColorContaining("e").stream().map(SuperCar::toString).forEach(log::info);
//    }



}
