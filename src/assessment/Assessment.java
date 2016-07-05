package assessment;
import assessment.Model.Plano;
import assessment.Model.User;
import static assessment.dao.AbstractDao.conn;
import assessment.dao.PlanoDao;
import assessment.dao.UserDao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Assessment {
    static User user = null;
    static Plano plano = null;
    static String menu;
    static UserDao userConn;
    static PlanoDao planoConn;
    static Scanner sc;    
    
    public static void main(String[] args) throws IOException {        
        userConn = new UserDao();
        sc = new Scanner(System.in);
        makeMenu();
    }
    
    public static void makeMenu() throws IOException{
        menu = ("[1] Logar\n" +
                "[2] Sair.");
        System.out.println(menu);
        login();
    }    
    private static void login() throws IOException {
        int op = sc.nextInt();
        switch(op){
            case 1:
                logar();
                break;
            case 2:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida.");
        }    
    }
     private static void logar() throws IOException{
        System.out.println("Insira seu ID:");
        int userID = sc.nextInt();
        //pegar id; 
        user = userConn.find(userID);
        if(user != null){
            mainOption(user);
        }else{
            System.out.println("Este ID ainda não foi cadastrado.");
            logar();
        }        
    }
    private static void consultarDados() throws IOException {
       userConn.openConnection();
       PreparedStatement ps;
       String nomeArq = "Assessment.txt";
       Formatter arq = new Formatter(nomeArq);
       FileReader leitor = new FileReader(nomeArq);
       BufferedReader br = new BufferedReader(leitor);
        try {
            ps = conn.prepareStatement("SELECT * FROM user where id = (?)");
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            String[] aux = new String[10];
            if(rs.next()){
                user = new User();
                plano = new Plano();
                
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("nome"));
                user.setNascimento(rs.getDate("nascimento"));
                plano.setId(rs.getInt("plano"));
                
                ps = conn.prepareStatement("SELECT * FROM plano where id = (?)");
                ps.setInt(1, plano.getId());
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                plano.setName(rs2.getString("nome"));
                user.setPlano(plano);   
                StringBuilder nomeCompleto = new StringBuilder();
                aux = user.getName().split(" ");
                for (String aux1 : aux) {
                    if (aux1 != null) {
                        nomeCompleto.append(aux1).append(" ");
                    }
                }
                String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(user.getNascimento());
                try{
                    arq.format("ID: " + user.getId() + "\nNome Completo: " + nomeCompleto + "\nPlano: " + user.getPlano().getName() + "\nNascimento: " + dataFormatada);
                    arq.close();
                    String s;
                    while ((s = br.readLine()) != null) {
                            System.out.println(s);
                    }
                    leitor.close();
                }catch(Exception erro){
                    JOptionPane.showMessageDialog(null,"Arquivo nao pode ser gerado!","Erro",0);
                }
                
            }else{
                System.out.println("DEU ERRO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Assessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void consultarCanais() {
        userConn.openConnection();
        PreparedStatement ps,ps2;
        ResultSet rs;
        try {
            ps = conn.prepareStatement("SELECT * FROM user where id = (?)");
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();                
            if(rs.next()){
                user = new User();
                plano = new Plano();
                ArrayList listCanais = new ArrayList();
                user.setId(rs.getInt("id"));
                plano.setId(rs.getInt("id"));
                user.setPlano(plano);                
                
                ps2 = conn.prepareStatement("SELECT * FROM plano where id = (?)");
                ps2.setInt(1, user.getPlano().getId());
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                listCanais.add(rs2.getString("canais"));
                System.out.println("Seu plano contem os seguintes canais: "+listCanais);
                userConn.closeConnection();
            }else{
                System.out.println("Error");
            }  
        } catch (SQLException ex) {
            Logger.getLogger(Assessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static void adquirirCanal() {
        userConn.openConnection();
        PreparedStatement ps, ps2;
        ResultSet rs, rs2;
        if(user.isTitular()){
            try {
                ps = conn.prepareStatement("SELECT * FROM canal where id > 0");
                rs = ps.executeQuery();
                ArrayList<String> todosCanais = new ArrayList<String>();
                while(rs.next()){
                    todosCanais.add(rs.getString("nome"));
                }
                System.out.println("Selecione um dos seguintes canais disponíveis para a compra: ");
                for(int i = 0; i < todosCanais.size(); i++){
                    System.out.println("["+i+"] "+todosCanais.get(i));
                }
                int opcao = sc.nextInt();
                String canalEscolhido = todosCanais.get(opcao);
                user.getPlano().getCanais2().add(canalEscolhido);
                System.out.println("Seu plano possui os seguintes canais: " + user.getPlano().getCanais2());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Apenas titulares podem adquirir novos canais.");
        }
    }

    private static void mainOption(User user) throws IOException {
         //gerar menu
        String mainMenu = ("[1] Consultar dados de cliente\n"
                + "[2] Consultar canais no plano\n"
                + "[3] Consultar programas de um canal\n" 
                + "[4] Adquirir canal\n"
                + "[5] Sair.");
        System.out.println(mainMenu);
        int op = sc.nextInt();
        switch(op){
            case 1:
                consultarDados();
                break;
            case 2:
                consultarCanais();
                break;
            case 3:
              //  consultarProgramacao();
                break;
            case 4: 
                adquirirCanal();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static String toSring(Object get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
