package inc.evil.reviews.config.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Pointcut(value = "within(@org.springframework.stereotype.Service *)")
    fun serviceLayer() {
    }

    @Pointcut(value = "within(@org.springframework.stereotype.Repository *)")
    fun repoLayer() {
    }

    @Pointcut(value = "within(@org.springframework.stereotype.Controller *)")
    fun webLayer() {
    }

    @Around(value = "serviceLayer() || repoLayer() || webLayer() ")
    @Throws(Throwable::class)
    fun logAround(pjp: ProceedingJoinPoint): Any? {
        log.debug("before :: ${pjp.toLongString()} ")
        val returnValue = pjp.proceed()
        log.debug("after :: ${pjp.toLongString()} = $returnValue")
        return returnValue
    }
}
