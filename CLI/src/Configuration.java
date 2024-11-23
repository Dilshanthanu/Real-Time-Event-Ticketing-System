import java.util.InputMismatchException;
import java.util.Scanner;

public class Configuration {

    private int totalTickets ;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public void setConfiguration() {
        String methodDetails = "[Configuration] -- setConfiguration: ";
        Scanner scanner = new Scanner(System.in);

        resetConfiguration();

        while (totalTickets <= 0 || maxTicketCapacity <= 0 || ticketReleaseRate <= 0 || customerRetrievalRate <= 0
                || maxTicketCapacity > totalTickets || ticketReleaseRate > totalTickets || ticketReleaseRate > maxTicketCapacity
                || customerRetrievalRate > totalTickets || customerRetrievalRate > maxTicketCapacity) {

            try {
                if (totalTickets <= 0) {
                    System.out.print("Enter Total Tickets: ");
                    totalTickets = scanner.nextInt();
                    if (totalTickets <= 0) {
                        Logger.warn(methodDetails + "Total Tickets must be greater than 0");
                    }
                }

                if (maxTicketCapacity <= 0) {
                    System.out.print("Enter Max Ticket Capacity: ");
                    maxTicketCapacity = scanner.nextInt();
                    if (maxTicketCapacity <= 0) {
                        Logger.warn(methodDetails + "Max Ticket Capacity must be greater than 0");
                    } else if (maxTicketCapacity > totalTickets) {
                        Logger.warn(methodDetails + "Max Ticket Capacity must not exceed Total Tickets");
                        maxTicketCapacity = 0; // Reset to re-enter
                    }
                }

                if (ticketReleaseRate <= 0) {
                    System.out.print("Enter Ticket Release Rate: ");
                    ticketReleaseRate = scanner.nextInt();
                    if (ticketReleaseRate <= 0) {
                        Logger.warn(methodDetails + "Ticket Release Rate must be greater than 0");
                    } else if (ticketReleaseRate > totalTickets || ticketReleaseRate > maxTicketCapacity) {
                        Logger.warn(methodDetails + "Ticket Release Rate must not exceed Total Tickets or Max Ticket Capacity");
                        ticketReleaseRate = 0; // Reset to re-enter
                    }
                }

                if (customerRetrievalRate <= 0) {
                    System.out.print("Enter Customer Retrieval Rate: ");
                    customerRetrievalRate = scanner.nextInt();
                    if (customerRetrievalRate <= 0) {
                        Logger.warn(methodDetails + "Customer Retrieval Rate must be greater than 0");
                    } else if (customerRetrievalRate > totalTickets || customerRetrievalRate > maxTicketCapacity) {
                        Logger.warn(methodDetails + "Customer Retrieval Rate must not exceed Total Tickets or Max Ticket Capacity");
                        customerRetrievalRate = 0; // Reset to re-enter
                    }
                }

            } catch (InputMismatchException e) {
                Logger.warn("[Input Error] Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }

        Logger.info(methodDetails + "Configuration set successfully.");
        System.out.println();
    }

    public void resetConfiguration() {
        totalTickets = 0;
        ticketReleaseRate = 0;
        customerRetrievalRate = 0;
        maxTicketCapacity = 0;
    }


    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
}
