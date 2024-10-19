package com.alekpajor.pbd.Controllers

import com.alekpajor.pbd.Models.Snapshot
import com.alekpajor.pbd.Services.SnapshotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/snapshot")
class SnapshotController(@Autowired val snapshotService: SnapshotService) {

    @GetMapping
    fun getAllActivities(): List<Snapshot> {
        return snapshotService.getAllSnapshots()
    }

    @PostMapping
    fun createActivity(@RequestBody snapshot: Snapshot): Snapshot {
        return snapshotService.createSnapshot(snapshot)
    }

}