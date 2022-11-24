package inc.evil.reviews.service.impl

import inc.evil.reviews.model.Review
import inc.evil.reviews.service.ReportGenerationService
import inc.evil.reviews.service.ReviewService
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime


private const val RELEASE_DATE = "2020-01-01"

@Service
class PdfReportGenerationService(val reviewService: ReviewService) : ReportGenerationService {


    override suspend fun generateReportById(id: Int) {
        val review = kotlin.runCatching { reviewService.findById(id) }
            .getOrElse { Review(-1, "n/a", "n/a", LocalDateTime.MIN, LocalDateTime.MIN, -1) }

        val (document, contentStream) = setUp()
        contentStream.showText("Review with id [${review.id}]: ")
        contentStream.newLine()
        contentStream.showText(
            "${review.id} - ${review.text}... by ${review.author} on ${review.createdAt?.toLocalDate()} for course [${review.courseId}]",
        ); contentStream.newLine()
        contentStream.endText()
        contentStream.close()

        document.save("reviews-id-${review.id}.pdf")
        document.close()
    }

    override suspend fun generateReportFor(date: LocalDate?) {
        requireNotNull(date) { "Date should not be null" }

        val reviews = if (date.isBefore(LocalDate.parse(RELEASE_DATE))) {
            reviewService.findAllByCreatedAt(today())
        } else {
            reviewService.findAllByCreatedAt(date)
        }

        val (document, contentStream) = setUp()
        contentStream.showText("All reviews for ${date}: ")
        contentStream.newLine()
        reviews.forEach {
            contentStream.showText(
                "${it.id} - ${it.text}... by ${it.author} on ${it.createdAt?.toLocalDate()} for course [${it.courseId}]",
            ); contentStream.newLine()
        }
        contentStream.endText()
        contentStream.close()

        document.save("reviews-${date}.pdf")
        document.close()
    }

    internal fun today(): LocalDate = LocalDate.now()

    override suspend fun generateReport() {
        val reviews = reviewService.findAll()
        val (document, contentStream) = setUp()
        contentStream.showText("All reviews : ")
        contentStream.newLine()
        reviews.forEach {
            contentStream.showText(
                "${it.id} - ${it.text}... by ${it.author} on ${it.createdAt?.toLocalDate()} for course [${it.courseId}]",
            ); contentStream.newLine()
        }
        contentStream.endText()
        contentStream.close()

        document.save("reviews.pdf")
        document.close()
    }

    private fun setUp(): Pair<PDDocument, PDPageContentStream> {
        val document = getDocument()
        val page = getPage()
        document.addPage(page)
        val contentStream = getPageContentStream(document, page)
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 11f)
        contentStream.setLeading(14.5f);
        contentStream.beginText()
        contentStream.newLineAtOffset(25F, 700F);
        return Pair(document, contentStream)
    }

    internal fun getPageContentStream(document: PDDocument, page: PDPage) = PDPageContentStream(document, page)

    internal fun getPage() = PDPage()

    internal fun getDocument() = PDDocument()

}
