package com.alekpajor.pbd.Repository

import com.alekpajor.pbd.Models.Exercise
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository : JpaRepository<Exercise, Long>