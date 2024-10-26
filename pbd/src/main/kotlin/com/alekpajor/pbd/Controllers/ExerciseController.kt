package com.alekpajor.pbd.Controllers

import com.alekpajor.pbd.Models.Exercise
import com.alekpajor.pbd.Services.ExerciseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/exercise")
class ExerciseController(@Autowired val exerciseService: ExerciseService) {

    @GetMapping
    fun getAllExercises(): List<Exercise> {
        return exerciseService.getAllExercises()
    }

}