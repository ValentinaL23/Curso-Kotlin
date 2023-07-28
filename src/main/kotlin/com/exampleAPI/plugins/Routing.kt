package com.exampleAPI.plugins

import com.exampleAPI.rutas.usuarioRouting
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
      usuarioRouting()
    }
}
