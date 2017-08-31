package aplicacao;

import fachada.Fachada;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import modelo.Administrador;

public class TelaPrincipal {
    private JFrame frmPrincipal;
    private JMenu mnLog;
    private JMenuItem mntmLogoff;
    private JMenuItem mntmLogin;
    
    private JMenu mnLivro;
    private JMenuItem mntmBuscarPorTitulo;
    private JMenuItem mntmLivrosLista;
    
    private JMenu mnEmprestimo;
    private JMenuItem mntmFazerEmprestimo;
    private JMenuItem mntmdevolucao;
    private JMenuItem mntmEmprestimoPessoal;
    
    private JMenu mnUsuario;
    private JMenuItem mntmListaUsuario;
    private JMenuItem mntmCadastroUsuario;
    private JMenuItem mntmExcluirUsuario;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal window = new TelaPrincipal();
                    window.frmPrincipal.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public TelaPrincipal() {
        initialize();
    }
    
    private void initialize() {
        frmPrincipal = new JFrame();
        frmPrincipal.setResizable(false);
        frmPrincipal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent arg0) {
                try {
                    
                    Fachada.cadastrarUsuario("Administrador","admin", "admin@email.com", "123", "gerencia");
                    
                    // Livro 1
                    Fachada.cadastrarLivro("O reino das nozes 2", 2, "Marcela", "Maria");
                    Fachada.cadastrarLivro("O reino das nozes", 2, "João", "Maria");
                    
                    // Livro 2
                    Fachada.cadastrarLivro("A cabana", 2, "Fernando", "Luiz");
                    
                    // Livro 3
                    Fachada.cadastrarLivro("A cabana do sol", 2, "Marcela", "Gloria");
                    
                    // Livro 4
                    Fachada.cadastrarLivro("O ladrão de destinos", 2, "Hugo", "João");
                    
                    // Livro 5
                    Fachada.cadastrarLivro("Fallen", 2, "Joo", "Fernando");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "Até breve!");
            }
        });
		
        frmPrincipal.setTitle("Projeto Biblioteca");
        frmPrincipal.setBounds(100, 100, 436, 300);
        frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmPrincipal.getContentPane().setLayout(null);
        makeMenu();
    }
    
    private void makeMenu() {
        JMenuBar menuBar = new JMenuBar();
        frmPrincipal.setJMenuBar(menuBar);

        mnLog = new JMenu("Logar");
        menuBar.add(mnLog);
        
        if( Fachada.getLogado() == null ) {
	        mntmLogin = new JMenuItem("Login");
	        mntmLogin.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	                TelaLogin telalogin = new TelaLogin();
	                telalogin.setVisible(true);
	                
	                // Atualiza o menu após fechar a janela
	                telalogin.addWindowListener(new WindowAdapter() {
	                    @Override
	                    public void windowClosed(WindowEvent e) {
	                    	frmPrincipal.setJMenuBar(null);
	                    	makeMenu();
	                    	frmPrincipal.revalidate();
	                    }
	                });
	            }
	        });
	        mnLog.add(mntmLogin);
        } 
        else {
            mntmLogoff = new JMenuItem("Logoff");
            mntmLogoff.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    Fachada.logoff();
                    JOptionPane.showMessageDialog(null,"Até breve!");
                    frmPrincipal.setJMenuBar(null);
                    makeMenu();
                    frmPrincipal.revalidate();
                }
            });
            mnLog.add(mntmLogoff);
        }
       
        mnLivro = new JMenu("Livro");
        menuBar.add(mnLivro);
        
        mntmBuscarPorTitulo = new JMenuItem("Buscar");
        mnLivro.add(mntmBuscarPorTitulo);
        mntmBuscarPorTitulo.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                TelaBuscarLivro tela = new TelaBuscarLivro();
                tela.setVisible(true);
            }
        });
        
        mntmLivrosLista = new JMenuItem("Listar");
        mnLivro.add(mntmLivrosLista);
        mntmLivrosLista.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                TelaListarLivro tela = new TelaListarLivro();
                tela.setVisible(true);
            }
        });
        
        mnEmprestimo = new JMenu("Empréstimo");
        menuBar.add(mnEmprestimo);
        
        JMenuItem mntmEmprestimoLista = new JMenuItem("Listar");
        mnEmprestimo.add(mntmEmprestimoLista);
        mntmEmprestimoLista.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                TelaListarEmprestimo tela = new TelaListarEmprestimo();
                tela.setVisible(true);
            }
        });
        
	    if( Fachada.getLogado() != null ) {
	        mntmFazerEmprestimo = new JMenuItem("Empréstimo");
	        mnEmprestimo.add(mntmFazerEmprestimo);
	        mntmFazerEmprestimo.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	                TelaCadastrarEmprestimo tela = new TelaCadastrarEmprestimo();
	                tela.setVisible(true);
	            }
	        });
	        
	        mntmdevolucao = new JMenuItem("Devolução");
	        mnEmprestimo.add(mntmdevolucao);
	        mntmdevolucao.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	                TelaDevolucaoEmprestimo tela = new TelaDevolucaoEmprestimo();
	                tela.setVisible(true);
	            }
	        });
	        
	        mntmEmprestimoPessoal = new JMenuItem("Meus Empréstimos");
	        mnEmprestimo.add(mntmEmprestimoPessoal);
	        mntmEmprestimoPessoal.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent arg0) {
	                TelaEmprestimoPessoal tela = new TelaEmprestimoPessoal();
	                tela.setVisible(true);
	            }
	        });
        } // only for logged
            
        mnUsuario = new JMenu("Usuario");
        menuBar.add(mnUsuario);
        
        mntmListaUsuario = new JMenuItem("Listar");
        mnUsuario.add(mntmListaUsuario);
        mntmListaUsuario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                TelaListarUsuario tela = new TelaListarUsuario();
                tela.setVisible(true);
            }
        });
        
        if( Fachada.getLogado() instanceof Administrador ) {
            mntmCadastroUsuario = new JMenuItem("Cadastrar");
            mnUsuario.add(mntmCadastroUsuario);
            mntmCadastroUsuario.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    TelaCadastrarUsuario tela = new TelaCadastrarUsuario();
                    tela.setVisible(true);
                }
            });

            mntmExcluirUsuario = new JMenuItem("Excluir");
            mnUsuario.add(mntmExcluirUsuario);
            mntmExcluirUsuario.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    TelaRemoverUsuario tela = new TelaRemoverUsuario();
                    tela.setVisible(true);
                }
            });
        }
    }
}