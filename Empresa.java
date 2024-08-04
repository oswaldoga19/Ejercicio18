import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;




/**
 *
 * @author Oswaldo
 */
public class Empresa extends JFrame implements ActionListener {

    private JTextField txtCodigo;
    private JTextField txtNombres;
    private JTextField txtNumeroHoras;
    private JTextField txtValorHora;
    private JTextField txtPorcentaje;

    private JTextArea txtAreaResultado;
    private List<Empleado> empleados;

    public Empresa() {
        super("Empresa");

        empleados = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        setResizable(true);
        setLocationRelativeTo(null);

        //panel de formulario para agregar empleados
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2, 10, 100));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel lblCodigo = new JLabel("Codigo: ");
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 16));
        txtCodigo = new JTextField();
        txtCodigo.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel lblNombres = new JLabel("Nombres: ");
        lblNombres.setFont(new Font("Arial", Font.BOLD, 16));
        txtNombres = new JTextField();
        txtNombres.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel lblNumeroHoras = new JLabel("Numero Horas: ");
        lblNumeroHoras.setFont(new Font("Arial", Font.BOLD, 16));
        txtNumeroHoras = new JTextField();
        txtNumeroHoras.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel lblValorHora = new JLabel("Valor Hora: ");
        lblValorHora.setFont(new Font("Arial", Font.BOLD, 16));
        txtValorHora = new JTextField();
        txtValorHora.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel lblPorcentaje = new JLabel("Porcentaje: ");
        lblPorcentaje.setFont(new Font("Arial", Font.BOLD, 16));
        txtPorcentaje = new JTextField();
        txtPorcentaje.setFont(new Font("Arial", Font.PLAIN, 16));

        panelFormulario.add(lblCodigo);
        panelFormulario.add(txtCodigo);
        panelFormulario.add(lblNombres);
        panelFormulario.add(txtNombres);
        panelFormulario.add(lblNumeroHoras);
        panelFormulario.add(txtNumeroHoras);
        panelFormulario.add(lblValorHora);
        panelFormulario.add(txtValorHora);
        panelFormulario.add(lblPorcentaje);
        panelFormulario.add(txtPorcentaje);

        //boton añadir Empleado
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(this);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.setBackground(new Color(0, 153, 51));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setPreferredSize(new Dimension(200, 40));

        //boton Mostrar Empleados
        JButton btnInventario = new JButton("Ver empleados");
        btnInventario.addActionListener(this);
        btnInventario.setFont(new Font("Arial", Font.BOLD, 16));
        btnInventario.setBackground(new Color(255, 153, 0));
        btnInventario.setForeground(Color.WHITE);
        btnInventario.setPreferredSize(new Dimension(200, 40));

        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnInventario);

        //area de resultados
        txtAreaResultado = new JTextArea();
        txtAreaResultado.setEditable(false);
        txtAreaResultado.setFont(new Font("Arial", Font.PLAIN, 16));
        txtAreaResultado.setLineWrap(true);
        txtAreaResultado.setWrapStyleWord(true);
        txtAreaResultado.setBackground(new Color(240, 240, 240));
        txtAreaResultado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(txtAreaResultado);
        scrollPane.setPreferredSize(new Dimension(560, 200));

        //panel principal 
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.SOUTH);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panelPrincipal);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Agregar")) {
            String codigo = txtCodigo.getText();
            String nombres = txtNombres.getText();
            String numero_horas1 =  txtNumeroHoras.getText();
            float numero_horas = Float.parseFloat(numero_horas1);
            String valor_hora1 = txtValorHora.getText();
            float valor_hora = Float.parseFloat(valor_hora1);
            String porcentaje1 = txtPorcentaje.getText();
            float porcentaje = Float.parseFloat(porcentaje1);


            if (!codigo.isEmpty() && !nombres.isEmpty() && !numero_horas1.isEmpty()&& !valor_hora1.isEmpty()&& !porcentaje1.isEmpty()) {
                agregarEmpleado(codigo, nombres, numero_horas, valor_hora, porcentaje);
                txtCodigo.setText("");
                txtNombres.setText("");
                txtNumeroHoras.setText("");
                txtValorHora.setText("");
                txtPorcentaje.setText("");

                txtAreaResultado.setText("El Empleado se ha añadido correcteamente a la Empresa");
            } else {
                txtAreaResultado.setText("Por favor, introduce la información completa del empleado");
            }
        } 
        else if (e.getActionCommand().equals("Ver empleados")) {
            txtAreaResultado.setText("");
            List<String> inventario = getInventario();
            if (inventario.isEmpty()) {
                txtAreaResultado.setText("Aún no se han registrado empleado en la empresa.");
            } else {
                for (String empleado : inventario) {
                    txtAreaResultado.append(empleado + "\n");
                }
            }
            txtCodigo.setText("");
            txtNombres.setText("");

        }

    }

    public void agregarEmpleado(String codigo, String nombres, float numero_horas, float valor_hora, float porcentaje) {
        Empleado nuevoEmpleado = new Empleado(codigo, nombres, numero_horas, valor_hora, porcentaje);
        empleados.add(nuevoEmpleado);

        
    }

    public List<String> getInventario() {
        List<String> inventario = new ArrayList<>();
        for (Empleado empleado : empleados) {
            inventario.add(empleado.toString());
        }
        return inventario;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }

            new Empresa();
        });
    }
}
