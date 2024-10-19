package com.alekpajor.pbd.Controllers

import com.alekpajor.pbd.Models.Report
import com.alekpajor.pbd.Services.ReportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/report")
class ReportController(@Autowired val reportService: ReportService) {

    @GetMapping
    fun getAllReports(): List<Report> {
        return reportService.getAllReports()
    }

    @PostMapping
    fun createReport(@RequestBody report: Report): Report {
        return reportService.createReport(report)
    }

}