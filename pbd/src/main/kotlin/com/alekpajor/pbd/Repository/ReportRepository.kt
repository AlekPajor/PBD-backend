package com.alekpajor.pbd.Repository

import com.alekpajor.pbd.Models.Report
import org.springframework.data.jpa.repository.JpaRepository

interface ReportRepository : JpaRepository<Report, Long>