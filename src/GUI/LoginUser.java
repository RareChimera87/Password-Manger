package GUI;

import javax.swing.*;
import java.awt.*;

import Control.ControlUser;
import Model.User;


public class LoginUser extends JFrame {

    private JTextField txtUserName;
    private JPasswordField txtPass;

    private ControlUser controlUser;

    public LoginUser(ControlUser controlUser) {
        super("Login User");
        this.controlUser = controlUser;

        setSize(400, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        txtUserName = new JTextField(20);
        panelFormulario.add(txtUserName, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        txtPass = new JPasswordField(20);
        panelFormulario.add(txtPass, gbc);



        add(panelFormulario, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        panelButtons.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton btnSave = new JButton("go");
        btnSave.setPreferredSize(new Dimension(150, 35));
        btnSave.addActionListener(e -> loginUser());

        panelButtons.add(btnSave);
        add(panelButtons, BorderLayout.SOUTH);

        setVisible(true);

    }

    private void loginUser() {
        try {
            String Username = txtUserName.getText().trim().toLowerCase();
            char[] PasswordCharts = txtPass.getPassword();
            String Password = new String(PasswordCharts);

            if (Username.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Password is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println(Password);
            User usua = controlUser.loginUser(Username, Password);
            if(usua != null){
                JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new UserMenu(usua);
            }
            else {
                JOptionPane.showMessageDialog(this, "Login Failed! User not find or invalid", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

}
