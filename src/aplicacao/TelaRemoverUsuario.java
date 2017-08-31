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
import modelo.Usuario;

public class TelaRemoverUsuario extends JFrame {

    private static final long serialVersionUID = 3787384543709940894L;
    private JPanel contentPane;
    private JTextField textField_1;
    private JLabel lblEmail;
    private JButton btnEntrar;

    /**
     * Create the frame.
     */
    public TelaRemoverUsuario() {
        setTitle("Emprestimo");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 199, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblEmail = new JLabel("Nome: ");
        lblEmail.setBounds(10, 24, 46, 14);
        contentPane.add(lblEmail);

        textField_1 = new JTextField();
        textField_1.setBounds(72, 21, 112, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        btnEntrar = new JButton("Excluir");
        btnEntrar.setBounds(40, 65, 110, 23);
        contentPane.add(btnEntrar);
        
        btnEntrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String nome = textField_1.getText();
                    Usuario aux = Fachada.buscarUsuario(nome);

                    if( aux == null ) {
                        JOptionPane.showMessageDialog(null,"Não há usuario com este nome!");
                        textField_1.setText(null);
                        
                        return;
                    }

                    Fachada.excluirUsuario(aux.getNome());
                    JOptionPane.showMessageDialog(null,"Usuario Excluido com sucesso\n");

                    dispose();
                }
                catch(Exception erro){
                    JOptionPane.showMessageDialog(null,erro.getMessage());
                }
            }
        });
    }
}