package aplicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import java.util.Collection;
import modelo.Livro;
import modelo.Autor;

public class TelaListarLivro extends JFrame {
    
    private JPanel contentPane;
    private JTextArea textArea;
    private JScrollPane scroller;

    /**
     * Create the frame.
     */
    public TelaListarLivro() {
        setTitle("Listar Livros");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 385);
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
        
        Collection<Livro> aux = Fachada.listarLivros();
        String texto = "Listagem de Livros: \n\n";
        if (aux.isEmpty())
            texto += "n�o tem Livro cadastrado\n";
        else 	
            for(Livro p: aux) {
                texto +=  "Titulo: " + p.getTitulo() + "\n";
                texto +=  "Autores: ";
                for(Autor a : p.getAutores()) {
                    texto += a.getNome() + ", ";
                }
                texto += "\n";
                texto += "Exemplares: " + p.getQuantidade() + "\n";
                texto += "Emprestimos: " + p.getQtdEmprestimos() + "\n\n";
            }

        textArea.setText(texto);

    }
}   