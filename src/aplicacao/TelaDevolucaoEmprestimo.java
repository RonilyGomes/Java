package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Emprestimo;

public class TelaDevolucaoEmprestimo extends JFrame {

    private static final long serialVersionUID = 6149828445504711715L;
    private JPanel contentPane;
    private JTextField textField_1;
    private JLabel lblId;
    private JButton btnEntrar;

    /**
     * Create the frame.
     */
    public TelaDevolucaoEmprestimo() {
        setTitle("Devolucao");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 199, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        lblId = new JLabel("ID:");
        lblId.setBounds(10, 33, 46, 14);
        contentPane.add(lblId);
        
        textField_1 = new JTextField();
        textField_1.setBounds(60, 33, 112, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        btnEntrar = new JButton("Devolver");
        btnEntrar.setBounds(60, 60, 60, 60);
        contentPane.add(btnEntrar);
        
        btnEntrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String idEmp = textField_1.getText();
                    Emprestimo emp = Fachada.devolverEmprestimo(Integer.parseInt(idEmp));

                    JOptionPane.showMessageDialog(null,"Multa: "+ emp.getMulta() +"\n");
                    dispose();
                }
                catch(Exception erro){
                    JOptionPane.showMessageDialog(null,erro.getMessage());
                    textField_1.setText(null);
                }
            }
        });
    }
}