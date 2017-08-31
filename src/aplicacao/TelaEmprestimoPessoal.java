package aplicacao;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Emprestimo;

public class TelaEmprestimoPessoal extends JFrame {
    
    private JPanel contentPane;
    private JTextArea textArea;
    private JScrollPane scroller;

    /**
     * Create the frame.
     */
    public TelaEmprestimoPessoal() {
        setTitle("Buscar Emprestimo");
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

        try{
            ArrayList<Emprestimo> aux = Fachada.listarEmprestimosUsuario();
            String texto = "Listagem de Emprestimos: \n\n";

            if (aux.size() <= 0) {
                texto = "Sem resultados.\n";
            } else {
                for(Emprestimo em: aux) {
                    texto += "ID: " + em.getId() + "\n";
                    texto +=  "Titulo: " + em.getLivro().getTitulo() + "\n";
                    texto += "Usuario: " + em.getUsuario().getNome() + "\n";
                    texto += "Data Emprestimo: " + em.getDataemp() + "\n";
                    if( em.isDevolvido() ) {
                        texto += "Data Devolção: " + em.getDatadev() + "\n";
                        if(em.getMulta() > 0) texto += "Multa: " + em.getMulta() + "\n";
                    }
                    texto += "\n";
                }
            }

            textArea.setText(texto);
        }
        catch(Exception erro){
            JOptionPane.showMessageDialog(null,erro.getMessage());
        }			
    }
}   