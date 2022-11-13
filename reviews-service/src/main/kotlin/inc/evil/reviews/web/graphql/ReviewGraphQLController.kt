package inc.evil.reviews.web.graphql

import inc.evil.reviews.facade.ReviewFacade
import inc.evil.reviews.web.dto.ReviewRequest
import inc.evil.reviews.web.dto.ReviewResponse
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import javax.validation.Valid


@Controller
class ReviewGraphQLController(val reviewFacade: ReviewFacade) {

    @QueryMapping
    suspend fun getReviewById(@Argument id: Int): ReviewResponse = reviewFacade.findById(id)

    @QueryMapping
    suspend fun getAllReviews(): List<ReviewResponse> = reviewFacade.findAll()

    @MutationMapping
    suspend fun deleteReviewById(@Argument id: Int) = reviewFacade.deleteById(id)

    @MutationMapping
    suspend fun createReview(@Valid @Argument request: ReviewRequest): ReviewResponse = reviewFacade.save(request)
}

