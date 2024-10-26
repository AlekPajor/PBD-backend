package com.alekpajor.pbd.Services

import com.alekpajor.pbd.Models.Exercise
import com.alekpajor.pbd.Repository.ExerciseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExerciseService(
    @Autowired val exerciseRepository: ExerciseRepository
) {

    fun getAllExercises(): List<Exercise> = exerciseRepository.findAll()

}