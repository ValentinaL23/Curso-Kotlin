package com.exampleAPI

import kotlinx.serialization.Serializable

@Serializable
data class Usuario (
    val id: Int,
    val nombre: String,
    val apellido: String,
    val nacimiento: String
)
