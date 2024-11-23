import java.util.Scanner;

public class TicketBookingCLI {
    public static void main(String[] args) {
        String methodDetails = "[TicketingSystem] -- main";
        try{
           Banner banner = new Banner();
           banner.printBanner();

            TicketBookingCLI ticketBookingCLI = new TicketBookingCLI();
            ticketBookingCLI.loginUser();
        }catch (Exception e){
            System.out.println(e);
        }



        }

        public void loginUser(){

            System.out.println();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            Login userLogin = new AddminLogin(email, password);

            if(userLogin.login()){
                Logger.info("Login Successful");
                ControlPanel controlPanel = new ControlPanel();
                controlPanel.selectOption();

            }else {
                Logger.error("Login Failed Username or Password Incorrect");
                loginUser();
            }
        }
    }
