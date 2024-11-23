public class Customer implements Runnable {
    private final TicketPool ticketPool;

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }
    @Override
    public void run() {
        String methodDetails = "[Customer] -- run";
        while (true){
            try {
                ticketPool.removeTicket();

                if(ticketPool.checkTicketAvailability()){
                    Logger.info(methodDetails + "No more tickets available.");
                    break;
                }
                Thread.sleep(100);
            }catch (InterruptedException e){
                Logger.error(methodDetails + "Thread interrupted.");
                break;
            }
        }
    }
}
