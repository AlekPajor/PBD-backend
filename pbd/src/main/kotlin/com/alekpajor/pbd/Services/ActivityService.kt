package com.alekpajor.pbd.Services

import com.alekpajor.pbd.Models.Activity
import com.alekpajor.pbd.Repository.ActivityRepository
import com.alekpajor.pbd.Repository.ExerciseRepository
import com.alekpajor.pbd.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ActivityService(
    @Autowired val activityRepository: ActivityRepository,
    @Autowired val userRepository: UserRepository,
    @Autowired val exerciseRepository: ExerciseRepository,
) {
    fun getAllActivities(): List<Activity> = activityRepository.findAll()

    fun createActivity(activity: Activity): Activity = activityRepository.save(activity)

    fun assignToPatient(
        exerciseId: Long,
        patientId: Long,
        duration: String,
        startingTime: String,
        period: String,
    ) {
        val patient = userRepository.findById(patientId).orElseThrow {
            IllegalArgumentException("No user found with ID: $patientId")
        }

        val exercise = exerciseRepository.findById(exerciseId).orElseThrow {
            IllegalArgumentException("No exercise found with ID: $exerciseId")
        }

        if(patient.currentActivity != null) {
            activityRepository.delete(patient.currentActivity!!)
        }

        val activity = Activity(
            exercise = exercise,
            patient = patient,
            duration = duration,
            startingTime = startingTime,
            period = period
        )
        activityRepository.save(activity)

        val updatedPatient = patient.copy(currentActivity = activity)
        userRepository.save(updatedPatient)
    }
}