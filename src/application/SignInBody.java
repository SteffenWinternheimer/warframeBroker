package application;
public class SignInBody {
    private String email;
    private String password;
    private String auth_type;

    public SignInBody(String email, String password, String auth_type){
        this.email = email;
        this.password = password;
        this.auth_type = auth_type;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getAuthType(){
        return auth_type;
    }

}
