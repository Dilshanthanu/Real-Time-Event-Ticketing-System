import java.util.LinkedList;

public class TicketPool {

    private final LinkedList<Integer> ticketList = new LinkedList<>();
    private final Configuration configuration;
    private int releasedTicketCount;
    private final int poolSize;
    public TicketPool(Configuration configuration) {
        this.configuration = configuration;
        this.poolSize = configuration.getMaxTicketCapacity();
    }

    public synchronized void addTicket() {
        String methodDetails = "[TicketPool] -- addTicket";
        try{
            int ticketReleaseRate = configuration.getTicketReleaseRate();
            while (ticketList.size() == poolSize) {
                Logger.warn(methodDetails + "Ticket pull is full. wait until customer purchase tickets");
                wait();
            }
            ticketReleaseRate = Math.min(poolSize - ticketList.size(), ticketReleaseRate);
            for (int i = 0; i < ticketReleaseRate; i++) {
                if (releasedTicketCount >= configuration.getTotalTickets()) {
                    notifyAll();
                    return;
                }
                ticketList.add(releasedTicketCount++);
            }
            Logger.info(methodDetails + "Ticket added. Total tickets: " + ticketList.size());
            notifyAll();
        }catch (InterruptedException e) {
            Logger.error(methodDetails + "An error occurred while adding tickets to pool : " + e.getMessage());
        }
    }

    public synchronized void removeTicket() {
        String methodDetails = "[TicketPool] -- removeTicket";
        try{
            int customerRetrivalRate = configuration.getTicketReleaseRate();
           while (ticketList.isEmpty()){
               if (releasedTicketCount >=configuration.getTotalTickets()) {
                   Logger.error(methodDetails + "No more tickets to remove from event : ");
                   notifyAll();
               }else {
                   Logger.info(methodDetails + "Ticket pool is empty, wait untill vendor release tickets");
                   wait();
               }
           }
           customerRetrivalRate = Math.min(ticketList.size(), customerRetrivalRate);
           for (int i = 0; i < customerRetrivalRate; i++) {
               if (!ticketList.isEmpty()) {
                   ticketList.removeFirst();
               }
           }
           Logger.info(methodDetails + customerRetrivalRate + "Tickets purchased remaining" + ticketList.size());
        } catch (InterruptedException e) {
            Logger.error(methodDetails + "An error occurred while removing tickets from pool : " + e.getMessage());
        }
    }
    public boolean checkTicketAvailability() {
        return releasedTicketCount >= configuration.getTotalTickets() && ticketList.isEmpty();
    }
}
