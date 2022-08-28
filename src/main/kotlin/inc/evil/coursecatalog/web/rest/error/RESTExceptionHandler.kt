package inc.evil.coursecatalog.web.rest.error

import inc.evil.coursecatalog.common.dto.ErrorResponse
import inc.evil.coursecatalog.common.exceptions.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException

@Order
@ControllerAdvice(basePackages = ["inc.evil.coursecatalog.web.rest"])
class RESTExceptionHandler {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @ExceptionHandler(Exception::class)
    fun onException(e: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        log.error("Exception while handling request ${e.message}", e)
        val errorModel = ErrorResponse(request.servletPath, setOf(e.message ?: "n/a"))
        return ResponseEntity.internalServerError().body(errorModel)
    }

    @ExceptionHandler(ValidationException::class)
    fun onValidationException(e: ValidationException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        log.warn("Validation error while handling request: ${e.message}", e)
        val errorModel = ErrorResponse(request.servletPath, setOf(e.message ?: "n/a"))
        return ResponseEntity.badRequest().body(errorModel)
    }

    @ExceptionHandler(NotFoundException::class)
    fun onNotFoundException(e: NotFoundException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        log.warn("Entity not found: ${e.message}", e)
        val errorModel = ErrorResponse(request.servletPath, setOf(e.message ?: "n/a"))
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun onMissingServletRequestParameterException(e: MissingServletRequestParameterException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val message = "Parameter: '${e.parameterName}' of type ${e.parameterType} is required but is missing"
        log.warn("Exception while handling request: $message", e)
        val errorModel = ErrorResponse(request.servletPath, setOf(e.message))
        return ResponseEntity.badRequest().body(errorModel)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun onMethodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        log.warn("Exception while handling request: ${e.message}", e)
        val errorMessages = mutableSetOf<String>()
        e.bindingResult.allErrors.forEach { error ->
            if (error is FieldError) {
                errorMessages.add("Field '${error.field}' ${error.defaultMessage}, but value was [${error.rejectedValue}]")
            } else {
                errorMessages.add(Arrays.toString(error.codes))
            }
        }
        val errorModel = ErrorResponse(request.servletPath, errorMessages)
        return ResponseEntity.badRequest().body(errorModel)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun onConstraintViolationException(e: ConstraintViolationException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        log.warn("Exception while handling request: ${e.message}", e)
        val errorMessages = mutableSetOf<String>()
        e.constraintViolations.forEach { errorMessages.add("Field '${it.propertyPath}' ${it.message}, but value was [${it.invalidValue}]")}
        val errorModel = ErrorResponse(request.servletPath, errorMessages)
        return ResponseEntity.badRequest().body(errorModel)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun onMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException, request: HttpServletRequest): ResponseEntity<ErrorResponse?>? {
        val parameter = e.parameter
        val message = "Parameter: '${parameter.parameterName}' is not valid. Value '${e.value}' could not be bound to type: '${parameter.parameterType.simpleName.lowercase()}'"
        log.warn("Exception while handling request: $message", e)
        val errorModel = ErrorResponse(request.servletPath, setOf(message))
        return ResponseEntity.badRequest().body(errorModel)
    }
}
