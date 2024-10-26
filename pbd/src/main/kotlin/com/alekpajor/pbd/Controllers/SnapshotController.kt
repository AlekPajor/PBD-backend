package com.alekpajor.pbd.Controllers

import com.alekpajor.pbd.Models.Snapshot
import com.alekpajor.pbd.Requests.AddSnapshotRequest
import com.alekpajor.pbd.Services.SnapshotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/snapshot")
class SnapshotController(@Autowired val snapshotService: SnapshotService) {

    @GetMapping
    fun getAllSnapshots(): List<Snapshot> {
        return snapshotService.getAllSnapshots()
    }

    @PostMapping
    fun createSnapshot(@RequestBody snapshot: Snapshot): Snapshot {
        return snapshotService.createSnapshot(snapshot)
    }

    @PostMapping("add")
    fun addNewSnapshot(@RequestBody addSnapshotRequest: AddSnapshotRequest): ResponseEntity<Any> {
        return try {
            snapshotService.addNewSnapshot(
                time = addSnapshotRequest.time,
                leftElbow = addSnapshotRequest.leftElbow,
                rightElbow = addSnapshotRequest.rightElbow,
                leftKnee = addSnapshotRequest.leftKnee,
                rightKnee = addSnapshotRequest.rightKnee
            )
            ResponseEntity("Snapshot added successfully", HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("Error: ${e.message}", HttpStatus.BAD_REQUEST)
        }
        catch (e: Exception) {
            ResponseEntity("Unexpected error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}