import java.io.Console;

public class Main {
    public static void main(String[] args) {

    
        Console console = System.console();
        String email;
        String password;

        System.out.println("Please log into your Warframe market account to proceed.");
        email = new String(console.readLine("Please enter your E-Mail: "));
        password = new String(console.readPassword("Please enter your password: "));

        Authentication authentication = new Authentication(email,password);
        authentication.Authenticate();

        authentication.CreateOrder();

        
    }
}
