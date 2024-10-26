package com.alekpajor.pbd.Controllers

import com.alekpajor.pbd.Models.Activity
import com.alekpajor.pbd.Requests.AssignActivityRequest
import com.alekpajor.pbd.Services.ActivityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @PostMapping("assign")
    fun assignToPatient(@RequestBody assignActivityRequest: AssignActivityRequest): ResponseEntity<Any> {
        return try {
            activityService.assignToPatient(
                patientId = assignActivityRequest.patientId,
                exerciseId = assignActivityRequest.exerciseId,
                duration = assignActivityRequest.duration,
                startingTime = assignActivityRequest.startingTime,
                period = assignActivityRequest.period
            )
            ResponseEntity("Activity assigned to patient with ID ${assignActivityRequest.patientId}", HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("Error: ${e.message}", HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            ResponseEntity("Unexpected error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}