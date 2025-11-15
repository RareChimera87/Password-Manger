package GUI;

import javax.swing.*;
import java.awt.*;

import Control.ControlCredentials;
import Model.User;


public class RegisterService extends JFrame {

    private JTextField txtUserName, txtServiceName;
    private JTextArea txtNotas;
    private JPasswordField txtPass;
    private User user;

    private ControlCredentials controlCredentials;

    public RegisterService(User usuario, ControlCredentials controlCredentials) {
        super("Register Service");
        this.controlCredentials = controlCredentials;
        this.user = usuario;

        setSize(400, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Register Service", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("Service Name:"), gbc);

        gbc.gridx = 1;
        txtServiceName = new JTextField(20);
        panelFormulario.add(txtServiceName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        txtUserName = new JTextField(20);
        panelFormulario.add(txtUserName, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        txtPass = new JPasswordField(20);
        panelFormulario.add(txtPass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFormulario.add(new JLabel("Notas:"), gbc);

        gbc.gridx = 1;
        txtNotas = new JTextArea(15,15);
        panelFormulario.add(txtNotas, gbc);



        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        panelButtons.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(150, 35));
        btnSave.addActionListener(e -> saveUser());

        panelButtons.add(btnSave);
        add(panelButtons, BorderLayout.SOUTH);

        setVisible(true);

    }

    private void saveUser() {
        try {
            String Username = txtUserName.getText().trim().toLowerCase();
            String ServiceName = txtServiceName.getText().trim().toLowerCase();
            String Notas = txtNotas.getText().trim().toLowerCase();
            char[] PasswordCharts = txtPass.getPassword();
            String Password = new String(PasswordCharts);

            if (ServiceName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }if (Username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Password is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println(Password);
            //controlUser.RegisterUser(Username, Password);
            controlCredentials.RegisteCredentia(user.getId(),ServiceName, Username, Password, Notas);
            JOptionPane.showMessageDialog(this, "Servicio registrado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}
