package com.alekpajor.pbd.Services

import com.alekpajor.pbd.Models.Activity
import com.alekpajor.pbd.Repository.ActivityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ActivityService(
    @Autowired val activityRepository: ActivityRepository,
) {
    fun getAllActivities(): List<Activity> = activityRepository.findAll()

    fun createActivity(activity: Activity): Activity = activityRepository.save(activity)
}