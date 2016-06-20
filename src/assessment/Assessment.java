package assessment;
import assessment.Model.Canal;
import assessment.Model.Plano;
import assessment.Model.User;
import static assessment.dao.AbstractDao.conn;
import assessment.dao.UserDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                "[2] Cadastrar\n" +
                "[3] Sair.");
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
                cadastrar();
                break;
            case 3:
            default:
                System.out.println("Opção inválida.");
                System.exit(0);
        }    
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
/*
    private static void adquirirCanal(boolean titular) {
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

    @SuppressWarnings("empty-statement")
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
                
                System.out.println("chegou no if");
                user.setId(rs.getInt("id"));
                plano.setId(rs.getInt("id"));
                user.setPlano(plano);                
                
                ps2 = conn.prepareStatement("SELECT * FROM plano where id = (?)");
                ps2.setInt(1, user.plano.getId());
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                
                System.out.println("aeee");
                
                System.out.println(plano.getCanais());
            }else{
                System.out.println("falo");
            }  
        } catch (SQLException ex) {
            Logger.getLogger(Assessment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      private static void mainOption(User user) {
         //limpar tela
         clearConsole();
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
              //  adquirirCanal();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void logar(){
        //limpar tela
        clearConsole();
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
    
    public final static void clearConsole(){
        try{
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }catch (final Exception e){
             //  Handle any exceptions.
        }
    }

    private static void cadastrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
