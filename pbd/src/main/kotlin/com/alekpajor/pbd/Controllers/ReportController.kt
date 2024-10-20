package com.alekpajor.pbd.Controllers

import com.alekpajor.pbd.Models.Report
import com.alekpajor.pbd.Requests.AddReportRequest
import com.alekpajor.pbd.Services.ReportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @GetMapping("{patientId}")
    fun getReportsByPatientId(@PathVariable patientId: Long): ResponseEntity<Any> {
        return try {
            val reports = reportService.getReportsByPatientId(patientId)
            ResponseEntity(reports, HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("Error: ${e.message}", HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            ResponseEntity("Unexpected error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("add")
    fun addNewReport(@RequestBody addReportRequest: AddReportRequest): ResponseEntity<Any> {
        return try {
            reportService.addNewReport(
                patientId = addReportRequest.patientId,
                activityId = addReportRequest.activityId,
                date = addReportRequest.date,
                time = addReportRequest.time,
                correctness = addReportRequest.correctness
            )
            ResponseEntity("Report added successfully", HttpStatus.OK)
        } catch (e: IllegalArgumentException) {
            ResponseEntity("Error: ${e.message}", HttpStatus.BAD_REQUEST)
        }
        catch (e: Exception) {
            ResponseEntity("Unexpected error: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}