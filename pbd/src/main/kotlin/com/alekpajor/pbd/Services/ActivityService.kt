package com.alekpajor.pbd.Services

import com.alekpajor.pbd.Models.Activity
import com.alekpajor.pbd.Models.Report
import com.alekpajor.pbd.Repository.ActivityRepository
import com.alekpajor.pbd.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ActivityService(
    @Autowired val activityRepository: ActivityRepository,
    @Autowired val userRepository: UserRepository
) {
    fun getAllActivities(): List<Activity> = activityRepository.findAll()

    fun createActivity(activity: Activity): Activity = activityRepository.save(activity)

    fun assignToPatient(activityId: Long, patientId: Long) {
        val patient = userRepository.findById(patientId).orElseThrow {
            IllegalArgumentException("No user found with ID: $patientId")
        }

        val activity = activityRepository.findById(activityId).orElseThrow {
            IllegalArgumentException("No activity found with ID: $patientId")
        }

        val updatedPatient = patient.copy(currentActivity = activity)
        userRepository.save(updatedPatient)
    }
}