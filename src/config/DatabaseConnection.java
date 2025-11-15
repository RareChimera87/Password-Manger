package config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {


   public static Connection getConnection(String route) throws Exception {
       return DriverManager.getConnection("jdbc:sqlite:" + route);
   }

   public static boolean genDB(String route){
       Path ruta = Path.of(route);

       if (Files.exists(ruta)) {
           System.out.println("El Archivo Existe");
           return true;
       } else {
           String url = "jdbc:sqlite:" + route;

           try (var conn = DriverManager.getConnection(url)) {
               if (conn != null) {
                   var meta = conn.getMetaData();
                   System.out.println("The driver name is " + meta.getDriverName());
                   System.out.println("A new database has been created.");
                   return true;
               }
           } catch (SQLException e) {
               System.err.println(e.getMessage());
               return false;
           }
           return false;

       }

   }

   public static void initilizeDB(String route){
       try (Connection conn = getConnection(route); Statement stmt = conn.createStatement()){
           // Tabla system_settings
           stmt.execute("""
                CREATE TABLE IF NOT EXISTS system_settings (
                    key TEXT PRIMARY KEY,
                    value TEXT NOT NULL
                );
            """);

           // Tabla de usuarios
           stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    password_hash TEXT NOT NULL,
                    salt TEXT NOT NULL,
                    created_at TEXT NOT NULL
                );
            """);

           // Tabla de contraseñas
           stmt.execute("""
                CREATE TABLE IF NOT EXISTS password_entries (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    service_name TEXT NOT NULL,
                    username TEXT NOT NULL,
                    password_encrypted TEXT NOT NULL,
                    notes_encrypted TEXT,
                    created_at TEXT NOT NULL,
                    updated_at TEXT NOT NULL,
                    user_id INTEGER,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );
            """);

           System.out.println("Base de datos inicializada correctamente.");
       } catch (Exception e){
           e.printStackTrace();
       }
   }

}
