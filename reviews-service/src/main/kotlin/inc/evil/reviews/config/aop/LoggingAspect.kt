package inc.evil.reviews.config.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.stereotype.Component
import java.util.*

@Aspect
@Component
class LoggingAspect(private val env: Environment) {

    @Pointcut(
        "within(@org.springframework.stereotype.Repository *)" +
                " || within(@org.springframework.stereotype.Service *)" +
                " || within(@org.springframework.web.bind.annotation.RestController *)"
    )
    fun springBeanPointcut() {
    }

    @Pointcut("within(inc.evil.reviews.repo..*)" + " || within(inc.evil.reviews.service..*)" + " || within(inc.evil.reviews.web..*)")
    fun applicationPackagePointcut() {
    }


    private fun logger(joinPoint: JoinPoint): Logger {
        return LoggerFactory.getLogger(joinPoint.signature.declaringTypeName)
    }


    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    fun logAfterThrowing(joinPoint: JoinPoint, e: Throwable) {
        if (env.acceptsProfiles(Profiles.of("dev"))) {
            logger(joinPoint)
                .error(
                    "Exception in {}() with cause = '{}' and exception = '{}'",
                    joinPoint.signature.name,
                    if (e.cause != null) e.cause else "NULL",
                    e.message,
                    e
                )
        } else {
            logger(joinPoint)
                .error(
                    "Exception in {}() with cause = {}",
                    joinPoint.signature.name,
                    if (e.cause != null) e.cause else "NULL"
                )
        }
    }

    @Around("applicationPackagePointcut() && springBeanPointcut()")
    @Throws(Throwable::class)
    fun logAround(joinPoint: ProceedingJoinPoint): Any {
        val log = logger(joinPoint)
        if (log.isDebugEnabled) {
            log.debug("Enter: {}() with argument[s] = {}", joinPoint.signature.name, Arrays.toString(joinPoint.args))
        }
        try {
            val result = joinPoint.proceed()
            if (log.isDebugEnabled) {
                log.debug("Exit: {}() with result = {}", joinPoint.signature.name, result)
            }
            return result
        } catch (e: IllegalArgumentException) {
            log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.args), joinPoint.signature.name)
            throw e
        }
    }
}
