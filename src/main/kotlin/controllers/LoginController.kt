package controllers

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import services.services.LoginServices
import io.ktor.http.HttpStatusCode

fun Route.loginController() {
    val loginService = LoginServices()

    post("/login") {
        val params = call.receiveParameters()
        val username = params["username"] ?: ""
        val password = params["password"] ?: ""

        // Mostrar la contraseña ingresada en la consola para depuración (no recomendado en producción)


        if (username.isEmpty() || password.isEmpty()) {
            call.respondText("Usuario o contraseña vacíos", status = HttpStatusCode.BadRequest)
            return@post
        }

        // Llama al servicio para autenticar al usuario
        val isAuthenticated = loginService.authenticate(username, password)
        if (isAuthenticated) {
            // Redirige al usuario a Home.html si la autenticación es exitosa
            call.respondRedirect("/views/Home.html")
        } else {
            call.respondText("Usuario o contraseña incorrectos", status = HttpStatusCode.Unauthorized)
        }
    }
}
