package com.alekpajor.pbd.Services

import com.alekpajor.pbd.Models.Report
import com.alekpajor.pbd.Repository.ReportRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReportService(
    @Autowired val reportRepository: ReportRepository,
) {
    fun getAllReports(): List<Report> = reportRepository.findAll()

    fun createReport(report: Report): Report = reportRepository.save(report)
}