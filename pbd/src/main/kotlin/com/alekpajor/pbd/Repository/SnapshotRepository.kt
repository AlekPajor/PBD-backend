package com.alekpajor.pbd.Repository

import com.alekpajor.pbd.Models.Snapshot
import org.springframework.data.jpa.repository.JpaRepository

interface SnapshotRepository : JpaRepository<Snapshot, Long>