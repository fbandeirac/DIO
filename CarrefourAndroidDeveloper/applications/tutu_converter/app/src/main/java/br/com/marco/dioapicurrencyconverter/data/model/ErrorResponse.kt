package dev.stoneworks.tutu_converter.data.model

data class ErrorResponse (
    val status: Long,
    val code: String,
    val message: String
)
