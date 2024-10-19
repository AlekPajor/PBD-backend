package com.alekpajor.pbd.Repository

import com.alekpajor.pbd.Models.Activity
import org.springframework.data.jpa.repository.JpaRepository

interface ActivityRepository : JpaRepository<Activity, Long>