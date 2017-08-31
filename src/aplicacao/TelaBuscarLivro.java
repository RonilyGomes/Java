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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import modelo.Livro;

public class TelaBuscarLivro extends JFrame{
    
    private JPanel contentPane;
    private JTextArea textArea;
    private JScrollPane scroller;
    private JButton btnListarCaixaLivros;
    private JTextField textField_1;
    private JRadioButton autor, titulo;
    private RadioButtonHandler handler;
    private ButtonGroup grupo;
    private String radioSelected;

    /**
     * Create the frame.
     */
    public TelaBuscarLivro() {
        setTitle("Buscar Livros");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 425);
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
        
        handler = new RadioButtonHandler();
        
        titulo = new JRadioButton("Titulo", true);
        titulo.setBounds(100, 325, 100, 23);
        contentPane.add(titulo);
        
        autor = new JRadioButton("Autor", false);
        autor.setBounds(100, 350, 100, 23);
        contentPane.add(autor);
        
        grupo = new ButtonGroup();
        grupo.add(autor);
        grupo.add(titulo);
        
        titulo.addItemListener(handler);
        autor.addItemListener(handler);
        
        textField_1 = new JTextField();
        textField_1.setBounds(200, 325, 180, 23);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        btnListarCaixaLivros = new JButton("Buscar");
        btnListarCaixaLivros.setBounds(200, 353, 180, 23);
        contentPane.add(btnListarCaixaLivros);
        
        btnListarCaixaLivros.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String campo = textField_1.getText();
                    
                    ArrayList<Livro> aux = (radioSelected.equals("titulo")) 
                        ? Fachada.buscarLivroPorTitulo(campo)
                        : Fachada.buscarLivroPorAutor(campo);
                    
                    String texto = "Listagem de Livros: \n\n";
                    
                    if (aux.isEmpty())
                        texto = "Não há livros com esse titulo!\n";
                    else 	
                        for(Livro p: aux) {
                            texto +=  "Titulo: " + p.getTitulo() + "\n";
                            texto +=  "Autores: ";
                            texto += p.getAutores().get(0).getNome() + " e ";
                            texto += p.getAutores().get(1).getNome() + "\n\n";
                        }
                    textArea.setText(texto);
                }
                catch(Exception erro){
                    JOptionPane.showMessageDialog(null,erro.getMessage());
                }			
            }
        });
    }
    private class RadioButtonHandler implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent event) {
            radioSelected = (titulo.isSelected()) ? "titulo" : "autor";
        }
    }
}   