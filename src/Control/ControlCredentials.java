package Control;

import DAO.credentialsDAO;
import Model.Credentials;
import security.PasswordHasher;
import security.SaltGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class ControlCredentials {
    Credentials credentials;
    credentialsDAO credentialsDAO;
    public void RegisteCredentia(int user_id, String serviceName, String username, String password, String notas ) {
        try {
            byte[] saltBits = SaltGenerator.generateSalt(16);
            credentialsDAO = new credentialsDAO();
            byte[] password_hashed = PasswordHasher.hashPassword(password, saltBits);
            String salt = SaltGenerator.encodeSalt(saltBits);
            String pass_hass = SaltGenerator.encodeSalt(password_hashed);
            credentials = new Credentials(user_id, serviceName, username, password, notas, salt, LocalDateTime.now(), LocalDateTime.now());
            credentialsDAO.insert(credentials);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Credentials> GetAllCredentials(int userid) {
        try {
            credentialsDAO = new credentialsDAO();
            List<Credentials> lista = credentialsDAO.getAllCredentials(userid);
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
