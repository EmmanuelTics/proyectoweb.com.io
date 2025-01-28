package config

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils
import models.Users

object DatabaseFactory {
    fun init() {
        try {
            Database.connect(
                url = "jdbc:sqlserver://EMMANUEL:1433;databaseName=KotlinSystem;encrypt=false;trustServerCertificate=true",
                driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver",
                user = "sa",
                password = "12345"
            )

            println("Conexión a la base de datos establecida correctamente.")

            // Prueba de conexión con una consulta básica
            transaction {
                SchemaUtils.create(Users) // Si la tabla no existe, la crea
                println("Prueba de conexión exitosa: La tabla 'Users' está accesible.")
            }
        } catch (e: Exception) {
            println("Error al conectar a la base de datos: ${e.message}")
            e.printStackTrace()
        }
    }
}
