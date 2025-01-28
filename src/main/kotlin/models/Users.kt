package models


import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable() {

    val username = varchar("username", 50)
    val password = varchar("password_hash", 255) // Asegúrate de que el nombre sea correcto
}
