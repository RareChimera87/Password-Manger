import GUI.RegisterUser;
import config.DatabaseConnection;
import config.Config;
import Control.ControlUser;

public class Main {
    private static final String file_route = Config.DB_PATH;
    static void main(String[] args) {
        ControlUser controlUser = new ControlUser();

        if(DatabaseConnection.genDB(file_route)){
            DatabaseConnection.initilizeDB(file_route);
            new RegisterUser(controlUser);

        } else{
            System.out.println("ERROR: Database not found");
        }
    }
}
