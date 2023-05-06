package com.example.demo.repository;

import com.example.demo.enity.Dream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DreamRepository extends JpaRepository<Dream, Integer> {

    List<Dream> findAll();
    Dream save (Dream dream);

    @Query("SELECT p.durata, p.energie, p.stres FROM Dream p WHERE p.data = :date")
    List<Object[]> findDreamMetricsByDate(@Param("date") LocalDate date);

    @Query("SELECT p.durata, p.energie, p.stres FROM Dream p WHERE p.data BETWEEN :startDate AND :endDate")
    List<Object[]> findDreamMetricsByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
