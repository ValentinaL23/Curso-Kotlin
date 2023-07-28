package com.exampleAPI.rutas

import com.exampleAPI.Usuario
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val usuarios = mutableListOf(
  Usuario(1, "Alejandro", "Pérez", "01-05-2002")
)

fun Route.usuarioRouting() {
  route("/usuario") {
    get {
      if (usuarios.isNotEmpty()) {
        call.respond(usuarios)
      } else {
        call.respondText("No hay usuarios", status = HttpStatusCode.OK)
      }
    }
    get("{id?}") {
      val id = call.parameters["id"] ?: return@get call.respondText(
        "ID no encontrado",
        status = HttpStatusCode.BadRequest
      )
      val usuario = usuarios.find { it.id == id.toInt() } ?: return@get call.respondText(
        "Usuario con $id no encontrado",
        status = HttpStatusCode.NotFound
      )
      call.respond(usuario)
    }
    post {
      val usuario = call.receive<Usuario>()
      usuarios.add(usuario)
      call.respondText("Usuario creado", status = HttpStatusCode.Created)
    }
    put {
      val usuario = call.receive<Usuario>()
      usuarios.add(usuario)
      call.respondText("Usuario actualizado", status = HttpStatusCode.Created)
    }
    delete("{id?}") {
      val id = call.parameters["id"] ?: return@delete call.respondText(
        "Id no encontrado",
        status = HttpStatusCode.Accepted
      )
      if (usuarios.removeIf { it.id == id.toInt() }) {
        call.respondText("Usuario eliminado correctamente", status = HttpStatusCode.Accepted)
      } else {
        call.respondText("No encontrado", status = HttpStatusCode.NotFound)
      }
    }
  }
}