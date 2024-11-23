public class AddminLogin extends Login{

    public AddminLogin(String email, String password) {
        super(email, password);
    }
    @Override
    public boolean login() {
        return (super.username.equals("admin") && super.password.equals("admin"));
    }
}
