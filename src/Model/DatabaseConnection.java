package Model;

import java.nio.file.Files;
import java.nio.file.Path;
import config.Config;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        String file_route = Config.DB_PATH;

        Path ruta = Path.of(file_route);

        if (Files.exists(ruta)) {
            System.out.println("El Archivo Existe");
        } else {
            String url = "jdbc:sqlite:" + file_route;

            try (var conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    var meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }

    }
}
