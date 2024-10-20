package com.alekpajor.pbd.Services

import com.alekpajor.pbd.Common.Role
import com.alekpajor.pbd.Models.Report
import com.alekpajor.pbd.Repository.ActivityRepository
import com.alekpajor.pbd.Repository.ReportRepository
import com.alekpajor.pbd.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReportService(
    @Autowired val reportRepository: ReportRepository,
    @Autowired val userRepository: UserRepository,
    @Autowired val activityRepository: ActivityRepository
) {
    fun getAllReports(): List<Report> = reportRepository.findAll()

    fun createReport(report: Report): Report = reportRepository.save(report)

    fun getReportsByPatientId(patientId: Long): List<Report> {

        val patient = userRepository.findById(patientId).orElseThrow {
            IllegalArgumentException("No user found with ID: $patientId")
        }

        if(patient.role != Role.PATIENT) {
            throw IllegalArgumentException("User with provided ID has different role than ${Role.PATIENT}")
        }

        val reports = reportRepository.findByPatientId(patientId)
        return reports
    }

    fun addNewReport(
        patientId: Long,
        activityId: Long,
        date: String,
        time: String,
        correctness: Long
    ) {
        val patient = userRepository.findById(patientId).orElseThrow {
            IllegalArgumentException("No user found with ID: $patientId")
        }

        val activity = activityRepository.findById(activityId).orElseThrow {
            IllegalArgumentException("No activity found with ID: $patientId")
        }

        val report = Report(
            patient = patient,
            activity = activity,
            date = date,
            time = time,
            correctness = correctness
        )

        reportRepository.save(report)
    }
}