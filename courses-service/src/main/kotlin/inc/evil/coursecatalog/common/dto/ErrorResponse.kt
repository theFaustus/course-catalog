package inc.evil.coursecatalog.common.dto

data class ErrorResponse(val path: String, val messages: Set<String>)
