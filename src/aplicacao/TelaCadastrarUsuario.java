package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import javax.swing.JComboBox;

public class TelaCadastrarUsuario extends JFrame {
    
    String[] tipo = { "Funcionario", "Aluno" };

    private static final long serialVersionUID = 121068593863541107L;
    private JPanel contentPane;
    private JTextField textNome, textEmail, textOpt;
    private JLabel lblNome, lblEmail, lblSenha, lblTipo, lblOpt;
    private JPasswordField passwordField;
    private JComboBox cmbTipo;
    private JButton btnEntrar;

    /**
     * Create the frame.
     */
    public TelaCadastrarUsuario() {
        setTitle("Cadastro de Usuarios");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 250, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblNome = new JLabel("Nome: ");
        lblNome.setBounds(10, 24, 80, 14);
        contentPane.add(lblNome);
        
        lblEmail = new JLabel("Email: ");
        lblEmail.setBounds(10, 55, 80, 14);
        contentPane.add(lblEmail);
        
        lblSenha = new JLabel("Senha: ");
        lblSenha.setBounds(10, 84, 80, 14);
        contentPane.add(lblSenha);
        
        lblTipo = new JLabel("Tipo: ");
        lblTipo.setBounds(10, 115, 80, 14);
        contentPane.add(lblTipo);
        
        lblOpt = new JLabel("Departamento: ");
        lblOpt.setBounds(10, 146, 180, 14);
        contentPane.add(lblOpt);

        textNome = new JTextField();
        textNome.setBounds(105, 21, 125, 20);
        contentPane.add(textNome);
        textNome.setColumns(10);
        
        textEmail = new JTextField();
        textEmail.setBounds(105, 52, 125, 20);
        contentPane.add(textEmail);
        textEmail.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(105, 84, 125, 20);
        contentPane.add(passwordField);
        
        cmbTipo = new JComboBox(tipo);
        cmbTipo.setSelectedIndex(0);
        cmbTipo.setEditable(false);
        cmbTipo.setBounds(105, 115, 125, 20);
        contentPane.add(cmbTipo);
        
        textOpt = new JTextField();
        textOpt.setBounds(105, 146, 125, 20);
        contentPane.add(textOpt);
        textOpt.setColumns(10);

        btnEntrar = new JButton("Cadastrar");
        btnEntrar.setBounds(65, 186, 100, 23);
        contentPane.add(btnEntrar);
        
        cmbTipo.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String tipo = (String)cmbTipo.getSelectedItem();
                    String tipoListener;
                    
                    tipoListener = (tipo.equals("Funcionario")) 
                            ? "Departamento" 
                            : "Curso";
                    lblOpt.setText(tipoListener);
                }
                catch(Exception erro){
                    JOptionPane.showMessageDialog(null,erro.getMessage());
                }
            }
        });
        
        btnEntrar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String nome = textNome.getText();
                    String email = textEmail.getText();
                    String senha = new String(passwordField.getPassword());
                    String tipo = (String)cmbTipo.getSelectedItem();
                    String opt = textOpt.getText();
                    
                    if(tipo.equals("Funcionario")) {
                        lblOpt.setName("Teste");
                    } else {
                        lblOpt.setName("Testenado");
                    }
                    
                    Fachada.cadastrarUsuario(tipo, nome, email, senha, opt);
                    JOptionPane.showMessageDialog(
                            null, 
                            "Cadastro Efetuado com sucesso!"
                    );
                    
                    dispose();
                }
                catch(Exception erro){
                    JOptionPane.showMessageDialog(null,erro.getMessage());
                }
            }
        });
        
    }
}