package inc.evil.reviews.service.impl

import inc.evil.reviews.common.fixtures.ReviewFixture
import kotlinx.coroutines.runBlocking
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.stub
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class PdfReportGenerationServiceTest {

    private var reviewService: ReviewServiceImpl = mock(ReviewServiceImpl::class.java)
    private var reportGenerationService: PdfReportGenerationService = spy(PdfReportGenerationService(reviewService))

    @Test
    fun generateReportFor_whenInvoked_savesPdfFile() {
        val date = LocalDate.parse("2022-01-01")
        val pdDocument = spy(PDDocument::class.java)
        val pdPage = spy(PDPage::class.java)
        val pdPageContentStream = spy(PDPageContentStream(pdDocument, pdPage))
        reviewService.stub { onBlocking { it.findAllByCreatedAt(date) }.thenReturn(listOf(ReviewFixture.of(id = -1))) }
        `when`(reportGenerationService.getDocument()).thenReturn(pdDocument)
        `when`(reportGenerationService.getPage()).thenReturn(pdPage)
        doReturn(pdPageContentStream).`when`(reportGenerationService).getPageContentStream(pdDocument, pdPage)

        runBlocking {
            reportGenerationService.generateReportFor(date)

            verify(reviewService).findAllByCreatedAt(date)
            verify(pdPageContentStream).showText("All reviews for 2022-01-01: ")
            verify(pdPageContentStream).showText("-1 - What a nice course... by Squidward on 1970-01-01 for course [3]")
            verify(pdDocument).close()
        }
    }

    @Test
    fun generateReportFor_withDateNull_throwsIllegalArgumentException() {
        Assertions.assertThatCode {
            runBlocking {
                reportGenerationService.generateReportFor(null)
            }
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("Date should not be null")
    }



}
