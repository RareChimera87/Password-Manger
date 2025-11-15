package GUI;

import java.util.List;
import java.util.Scanner;

import Model.User;

import java.time.LocalDateTime;

import DAO.userDAO;

public class RegisterUser {
    static void main() throws Exception {
        User usuario;
        userDAO userDAO = new userDAO();

        System.out.println("Registrar Usuario");
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de Usuario");
        String username = sc.nextLine();
        System.out.println("Password de Usuario");
        String password = sc.nextLine();
        LocalDateTime creaated_at = LocalDateTime.now();

        usuario = new User(username, password, "algomasgisdsa", creaated_at);

        userDAO.insert(usuario);

        List<User> usuarios = userDAO.getAllUsers();
        for (User user : usuarios) {
            System.out.println(user.getId());
            System.out.println(user.getUsername());
            System.out.println(user.getPasswordHash());
            System.out.println(user.getSalt());
            System.out.println(user.getCreatedAt());
        }
        User prueba = userDAO.getUserById(2);

        System.out.println(prueba.getId());
        System.out.println(prueba.getUsername());
        System.out.println(prueba.getPasswordHash());
        System.out.println(prueba.getSalt());
        System.out.println(prueba.getCreatedAt());

    }
}
