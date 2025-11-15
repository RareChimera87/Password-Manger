package Control;

import DAO.userDAO;
import Model.User;
import security.PasswordHasher;
import security.SaltGenerator;

import java.time.LocalDateTime;
import java.util.List;


public class ControlUser {
    User usuario;
    userDAO userDAO;
    int bits = 16;

    public byte[] GenerateSalt(int bits) {
        return SaltGenerator.generateSalt(bits);
    }

    public String ByteToString(byte[] bytes) {
        return SaltGenerator.encodeSalt(bytes);
    }

    public byte[] StringToByte(String sal) {
        return SaltGenerator.decodeString(sal);
    }

    public void RegisterUser(String username, String password) {
        byte[] saltBits = GenerateSalt(bits);
        try {
            userDAO = new userDAO();
            byte[] password_hashed = PasswordHasher.hashPassword(password, saltBits);
            String salt = ByteToString(saltBits);
            String pass_hass = ByteToString(password_hashed);
            usuario = new User(username, pass_hass, salt, LocalDateTime.now());
            userDAO.insert(usuario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User loginUser(String username, String password) {
        try {
            User user = GetUserByUsername(username);
            if (user == null) {
                return null;
            }

            byte[] salt = StringToByte(user.getSalt());
            byte[] attempted_pass_bytes = PasswordHasher.hashPassword(password, salt);

            String attempted_pass = ByteToString(attempted_pass_bytes);

            boolean password_match = attempted_pass.equals(user.getPasswordHash());

            if( password_match){
                return user;
            }return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> GetAllUsers() {
        try {
            userDAO = new userDAO();
            List<User> usuarios = userDAO.getAllUsers();
            for (User user : usuarios) {
                System.out.println(user.getId());
                System.out.println(user.getUsername());
                System.out.println(user.getPasswordHash());
                System.out.println(user.getSalt());
                System.out.println(user.getCreatedAt());
            }
            return usuarios;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User GetUserById(int id) {
        try {
            userDAO = new userDAO();
            User usuario = userDAO.getUserById(id);
            System.out.println(usuario.getId());
            System.out.println(usuario.getUsername());
            System.out.println(usuario.getPasswordHash());
            System.out.println(usuario.getSalt());
            System.out.println(usuario.getCreatedAt());
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User GetUserByUsername(String username) {
        try {
            userDAO = new userDAO();
            User usuario = userDAO.getUserByUsername(username);
            if (usuario != null) {
                System.out.println(usuario.getId());
                System.out.println(usuario.getUsername());
                System.out.println(usuario.getPasswordHash());
                System.out.println(usuario.getSalt());
                System.out.println(usuario.getCreatedAt());
                return usuario;
            } else  {
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteUserById(int id) {
        try {
            userDAO = new userDAO();
            User usuario = userDAO.getUserById(id);
            System.out.println("El Usuario con la siguiente informacion sera removido de la base de datos: ");
            System.out.println(usuario.getId());
            System.out.println(usuario.getUsername());
            System.out.println(usuario.getCreatedAt());
            userDAO.delete(id);
            System.out.println("Usuario eliminado");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
