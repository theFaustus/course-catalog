package inc.evil.reviews.service

import java.time.LocalDate

interface ReportGenerationService {
    suspend fun generateReport()
    suspend fun generateReportFor(date: LocalDate?)
    suspend fun generateReportById(id: Int)
}
