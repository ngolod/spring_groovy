package com.ticlab.demo.repositories

import com.ticlab.demo.entities.Training
import org.springframework.data.jpa.repository.JpaRepository

interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findBySex(String sex)

    List<Training> findByName(String name)
}
