package config

import config.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import controllers.loginController
import io.ktor.http.*
import io.ktor.server.http.content.*
import java.io.File

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    // Inicializar la base de datos
    DatabaseFactory.init()

    routing {
        // Ruta para login
        loginController()
        get("/") {
            val indexFile = File("index.html")
            if (indexFile.exists()){

                call.respondFile(indexFile)
            } else {
                call.respondText("Archivo index.html no encontrado", status = HttpStatusCode.NotFound)
            }
        }


        get("/Login.html") {
            val loginFile = File("src/main/resources/public/views/Login.html")
            if (loginFile.exists()) {
                call.respondFile(loginFile)
            } else {
                call.respondText("Archivo Login.html no encontrado", status = HttpStatusCode.NotFound)
            }
        }

        get("/Home.html") {
            val homeFile = File("src/main/resources/public/views/Home.html")
            if (homeFile.exists()) {
                call.respondFile(homeFile)
            } else {
                call.respondText("Archivo Home.html no encontrado", status = HttpStatusCode.NotFound)
            }
        }


        static("/") {
            resources("public")
        }
    }
}
