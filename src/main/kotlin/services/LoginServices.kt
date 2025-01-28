package services.services

import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.select
import models.Users

class LoginServices {

    // Función para autenticar al usuario sin verificar la contraseña encriptada
    fun authenticate(username: String, password: String): Boolean {
        return transaction {
            // Obtiene la contraseña almacenada del usuario (sin encriptación)
            val storedPassword = Users
                .select { Users.username eq username }
                .map { it[Users.password] }  // Usamos Users.password (sin encriptar)
                .firstOrNull()

            // Si no se encuentra el usuario, devuelve false
            if (storedPassword == null) {
                return@transaction false
            }

            // Compara la contraseña proporcionada con la almacenada (sin encriptación)
            storedPassword == password
        }
    }
}
