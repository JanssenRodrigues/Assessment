package assessment;
import assessment.Model.Canal;
import assessment.Model.Plano;
import assessment.Model.User;
import static assessment.dao.AbstractDao.conn;
import assessment.dao.UserDao;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Assessment {
    static User user = null;
    static Plano plano = null;
    static String menu;
    static UserDao userConn;
    static Scanner sc;
    
    public static void main(String[] args) {        
        userConn = new UserDao();
        sc = new Scanner(System.in);
        makeMenu();
    }
    
    public static void makeMenu(){
        menu = ("[1] Logar\n" +
                "[2] Sair.");
        System.out.println(menu);
        login();
    }    
    private static void login() {
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
     private static void logar(){
        System.out.println("Insira seu ID:");
        int userID = sc.nextInt();
        //System.out.println(userID);
        //pegar id; 
        user = userConn.find(userID);
        if(user != null){
            mainOption(user);
        }else{
            System.out.println("Este ID ainda não foi cadastrado.");
            logar();
        }
        //abre menu mainOption();
        
    }
    private static void consultarDados() {
       userConn.openConnection();
       PreparedStatement ps;
        try {
            ps = conn.prepareStatement("SELECT * FROM user where id = (?)");
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                plano = new Plano();
                
                user.setId(rs.getInt("id"));
                plano.setId(rs.getInt("plano"));
                
                ps = conn.prepareStatement("SELECT * FROM plano where id = (?)");
                ps.setInt(1, plano.getId());
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                plano.setName(rs2.getString("nome"));
                user.setPlano(plano);
                System.out.println("ID: "+user.id+"\nPlano: "+user.plano.getName());
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
                ps2.setInt(1, user.plano.getId());
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
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conn.prepareStatement("SELECT * FROM canal where id > 0");
            rs = ps.executeQuery();
            ArrayList todosCanais = new ArrayList();
            while(rs.next()){
                todosCanais.add(rs.getString("nome"));
            }
            System.out.println("Selecione um dos seguintes canais disponíveis para a compra: ");
            for(int i = 0; i < todosCanais.size(); i++){
                System.out.println("["+i+"] "+todosCanais.get(i));
            }
            int opcao = sc.nextInt();
            user.plano.canais.add((Canal) todosCanais.get(opcao));
            System.out.println("Seu plano possui os seguintes canais: " /*+ user.plano.canais*/);
            
        } catch (Exception e) {
        }
    }

   /* private static void adquirirCanal(boolean titular) {
        if(titular){
            String canaisDisponiveis = ("[1] Canal Esporte\n"
                    + "[2] Canal Filme\n"
                    + "[3] Canal Adulto\n"
                    + "[4] Sair.");
            Scanner canal = new Scanner(System.in);
            int compra = canal.nextInt();
            switch(compra){
                case 1:
                    if(user.plano("Canal Esporte")){
                        System.out.println("Você já possui este canal.");
                    }else{
                        System.out.println("Canal adquirido com sucesso.");
                    }
                    break;
                case 2:
                    if(("Canal Filme")){
                        System.out.println("Você já possui este canal.");
                    }else{
                        System.out.println("Canal adquirido com sucesso.");
                    }
                    break;
                case 3:
                    if(client.meusCanais.contains("Canal Adulto")){
                        System.out.println("Você já possui este canal.");
                    }else{
                        System.out.println("Canal adquirido com sucesso.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
                    System.exit(0);
            }
            
        }else{
            System.out.println("Apenas usuários titulares podem adquirir novos canais.");
        }
    }*/

    

    private static void mainOption(User user) {
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
