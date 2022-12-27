package inc.evil.reviews.service.hazelcast

import com.hazelcast.spring.context.SpringAware
import inc.evil.courses.api.facade.CourseApiFacade
import inc.evil.courses.api.web.dto.CourseApiResponse
import org.springframework.beans.factory.annotation.Autowired
import java.io.Serializable
import java.util.concurrent.Callable

@SpringAware
class GetCourseByIdCallable(val id: Int) : Callable<CourseApiResponse>, Serializable {

    @Autowired
    @Transient
    private lateinit var courseApiFacade: CourseApiFacade

    override fun call(): CourseApiResponse = courseApiFacade.findById(id)
}
