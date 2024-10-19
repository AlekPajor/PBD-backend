package com.alekpajor.pbd.Controllers

import com.alekpajor.pbd.Models.Activity
import com.alekpajor.pbd.Services.ActivityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/activity")
class ActivityController(@Autowired val activityService: ActivityService) {

    @GetMapping
    fun getAllActivities(): List<Activity> {
        return activityService.getAllActivities()
    }

    @PostMapping
    fun createActivity(@RequestBody activity: Activity): Activity {
        return activityService.createActivity(activity)
    }

}