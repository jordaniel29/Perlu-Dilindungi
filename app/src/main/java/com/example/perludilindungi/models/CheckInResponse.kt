package com.example.perludilindungi.models

data class CheckInResponse (
    val success: Boolean,
    val code: Int,
    val message: String,
    val data: CheckInStatus,
)