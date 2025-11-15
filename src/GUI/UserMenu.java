package GUI;

import Control.ControlCredentials;
import Control.ControlUser;
import Model.User;

import javax.swing.*;
import java.awt.*;

public class UserMenu extends JFrame {
    public UserMenu(User u) {
        super("Password Management System");
        ControlUser controlUser = new ControlUser();
        ControlCredentials controlCredentials = new ControlCredentials();

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Menu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblInstruccion = new JLabel("¿Qué desea hacer?", SwingConstants.CENTER);
        lblInstruccion.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        panelCentral.add(lblInstruccion, gbc);

        Dimension tamañoBoton = new Dimension(300, 45);
        gbc.insets = new Insets(8, 0, 8, 0);

        JButton btnServices = crearBoton("Ver Servicio registrados", tamañoBoton);
        gbc.gridy = 1;
        panelCentral.add(btnServices, gbc);

        JButton btnRegister = crearBoton("Registrar Servicios", tamañoBoton);
        gbc.gridy = 2;
        panelCentral.add(btnRegister, gbc);

        JButton btnSalir = crearBoton("Salir", tamañoBoton);
        btnSalir.setBackground(new Color(220, 53, 69));
        btnSalir.setForeground(Color.WHITE);
        gbc.gridy = 7;
        gbc.insets = new Insets(20, 0, 0, 0);
        panelCentral.add(btnSalir, gbc);

        add(panelCentral, BorderLayout.CENTER);

        btnServices.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Abriendo registro...");
            new Services(controlCredentials, u);

        });

        btnRegister.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Abriendo formulario de registro...");
            new RegisterService(u, controlCredentials);
        });

        btnSalir.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea salir?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });


        setVisible(true);
    }

    private JButton crearBoton(String texto, Dimension tamaño) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(tamaño);
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
}
