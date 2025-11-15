package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import Control.ControlCredentials;
import Model.Credentials;
import Model.User;

public class Services extends JFrame {

    public Services(ControlCredentials controlCredentials, User user) {
        super("Services");

        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Services Registered", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        TablePanel tablePanel = new TablePanel(controlCredentials, user);
        add(tablePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public class TablePanel extends JPanel {
        private JTable tabla;
        private DefaultTableModel modelo;

        public TablePanel(ControlCredentials controlCredentials, User user) {
            setLayout(new BorderLayout());

            modelo = new DefaultTableModel();
            tabla = new JTable(modelo);

            // Columnas
            modelo.addColumn("Service Name");
            modelo.addColumn("Username");
            modelo.addColumn("Password");
            modelo.addColumn("Notas");
            modelo.addColumn("Created At");

            // Llenar datos desde BD
            cargarDatos(controlCredentials, user);

            add(new JScrollPane(tabla), BorderLayout.CENTER);
        }

        private void cargarDatos(ControlCredentials controlCredentials, User user) {
            List<Credentials> lista = controlCredentials.GetAllCredentials(user.getId());

            for (Credentials u : lista) {
                modelo.addRow(new Object[]{
                        u.getServiceName(),
                        u.getUsername(),
                        u.getPasswordHash(),
                        u.getNotasHash(),
                        u.getCreatedAt()
                });
            }
        }
    }

}
