package assessment;
import assessment.Model.User;
import assessment.dao.UserDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Assessment {
    static User user;
    static String menu;
    static UserDao userDao; 
    
    public static void main(String[] args) {        
        UserDao userConn = new UserDao();
        userConn.openConnection();
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
        Scanner sc = new Scanner(System.in);
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
    
     
    private static void consultarDados(User id) {
        PreparedStatement st = conn.prepareStatement("select * from user");
        ResultSet rs = st.executeQuery();
    }

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
    }

    private static void consultarCanais(String[] meusCanais) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      private static void mainOption(User user) {
         //limpar tela
         //gerar menu
        String mainMenu = ("[1] Consultar dados de cliente\n"
                + "[2] Consultar canais no plano\n"
                + "[3] Consultar programas de um canal\n" 
                + "[4] Adquirir canal\n"
                + "[5] Sair.");
        Assessment.user = user;
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        switch(op){
            case 1:
                consultarDados(user);
                break;
            case 2:
                consultarCanais(user.getPlano());
                break;
            case 3:
                consultarProgramacao();
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

    private static void logar(){
        //limpar tela
        clearConsole();
        System.out.println("Insira seu ID:");
        Scanner id = new Scanner(System.in);
        int verifica = id.nextInt();
        //pegar id;  
        user = userDao.find(verifica);
        if(user != null){
            mainOption(user);
        }else{
            System.out.println("Este ID ainda não foi cadastrado.");
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
