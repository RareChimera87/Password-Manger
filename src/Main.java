import config.DatabaseConnection;
import config.Config;

public class Main {
    private static final String file_route = Config.DB_PATH;
    static void main(String[] args) {

        if(DatabaseConnection.genDB(file_route)){
            DatabaseConnection.initilizeDB(file_route);
        } else{
            System.out.println("ERROR: Database not found");
        }
    }
}
