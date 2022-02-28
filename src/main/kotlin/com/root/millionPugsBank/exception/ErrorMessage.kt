package com.root.millionPugsBank.exception

import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

class ErrorMessage(
    val date: ZonedDateTime,
    val statusCode: HttpStatus,
    val message: String?,
) {
}