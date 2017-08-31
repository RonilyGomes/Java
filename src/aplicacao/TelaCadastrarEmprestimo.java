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
import modelo.Livro;

public class TelaCadastrarEmprestimo extends JFrame {
    
    private JPanel contentPane;
    private JTextField textField_1;
    private JLabel lblTitulo;
    private JButton btnEntrar;

    /**
     * Create the frame.
     */
    public TelaCadastrarEmprestimo() {
        setTitle("Emprestimo");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 199, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblTitulo = new JLabel("Titulo");
        lblTitulo.setBounds(10, 24, 46, 14);
        contentPane.add(lblTitulo);

        textField_1 = new JTextField();
        textField_1.setBounds(60, 21, 112, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        btnEntrar = new JButton("Cadastrar");
        btnEntrar.setBounds(40, 65, 110, 23);
        contentPane.add(btnEntrar);
        
        btnEntrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String titulo = textField_1.getText();
                    Livro livro = Fachada.localizarLivro(titulo);

                    if( livro == null ) {
                        JOptionPane.showMessageDialog(null,"Não há livro com este título!");
                        textField_1.setText(null);
                        return;
                    }

                    Emprestimo emp = Fachada.cadastrarEmprestimo(livro);
                    JOptionPane.showMessageDialog(null,"Id emprestimo: " + emp.getId() + "\n"
                            + "devolucao: " +  emp.getDatadev() +"\n");

                    dispose();
                }
                catch(Exception erro){
                    JOptionPane.showMessageDialog(null,erro.getMessage());
                }
            }
        });
    }
}