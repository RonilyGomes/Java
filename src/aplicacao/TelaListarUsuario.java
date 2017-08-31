package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Administrador;
import modelo.Aluno;
import modelo.Funcionario;
import modelo.Usuario;

public class TelaListarUsuario extends JFrame {
	
    private static final long serialVersionUID = -2214509078155931591L;
    private JPanel contentPane;
    private JTextArea textArea;
    private JScrollPane scroller;
    private JButton btnListarCaixaLivros;

    /**
     * Create the frame.
     */
    public TelaListarUsuario() {
        setTitle("Listar Usuarios");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroller.setBounds(24, 29, 450, 282);
        contentPane.add(scroller);
        
        ArrayList<Usuario> aux = Fachada.listarUsuarios();
        Fachada.ordenarUsuario(aux);

        String texto = "Listagem de Usuarios: \n\n";
        if (aux.isEmpty())
            texto += "não tem Usuario cadastrado\n";
        else {	
            for(Usuario a: aux) {
                if(!(a instanceof Administrador)) {
                    texto += "Nome: " + a.getNome() + "\n";
                    texto += "Email: " + a.getEmail() + "\n";
                    if(a instanceof Aluno){
                        texto += "Tipo: Aluno\n";
                        texto += "Curso: " + ((Aluno) a).getCurso() + "\n";
                    } else {
                        texto += "Tipo: Funcionario\n";
                        texto += "Departamento: " + ((Funcionario) a).getDepartamento() + "\n";
                    }       
                }
            }
        }
        textArea.setText(texto);
    }
}   