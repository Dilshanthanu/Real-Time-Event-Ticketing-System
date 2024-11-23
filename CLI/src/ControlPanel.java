import java.util.Scanner;

public class ControlPanel {


    Configuration configuration = new Configuration();

    public void selectOption() {

        if (configuration.getTotalTickets() == 0){
            System.out.println();
            System.out.println("Configuration From : ");
            executeOption("y");
        }else{
            Scanner scanner = new Scanner(System.in);
            System.out.println("1 - Start the System");
            System.out.println("2 - Stop the System");
            System.out.println();
            System.out.println("Enter option: ");
            String option = scanner.nextLine();
            executeOption(option);
        }
    }

    public void executeOption(String option) {
        String methodDetails = "[Control] -- executeOption : ";
        switch (option) {
            case "y":
                configuration.setConfiguration();
                break;

            case "1":
                TicketPool ticketPool = new TicketPool(configuration);
                Vendor vendor = new Vendor(ticketPool);
                Customer customer = new Customer(ticketPool);

                Thread threadVendor = new Thread(vendor);
                threadVendor.start();

                Thread threadCustomer = new Thread(customer);
                threadCustomer.start();

                try {
                    threadVendor.join();
                    threadCustomer.join();
                } catch (InterruptedException e) {
                    Logger.error(methodDetails + "An error occurred while waiting for thread termination: "
                            + Thread.currentThread() + ": " + e.getMessage());
                }
                break; // Add break to prevent fall-through to case "2"

            case "2":
                configuration.resetConfiguration();
                Logger.info(methodDetails + "System Stopped");
                break;

            default:
                Logger.error(methodDetails + "Invalid option");
                System.out.println();
                break;
        }

        if (!option.equals("2")) {
            selectOption();
        }
    }

}
