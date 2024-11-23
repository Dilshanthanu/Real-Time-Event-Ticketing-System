public class Vendor implements Runnable{

    private final TicketPool ticketPool;

    public Vendor(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }
    @Override
    public void run() {
    String methodDetails = "[Vendor] -- run";
    while (true){
        try {
            ticketPool.addTicket();
            Thread.sleep(200);
            if(ticketPool.checkTicketAvailability()){
                Logger.info(methodDetails + "No more tickets available.");
                break;
            }
        }catch (InterruptedException e){
            Logger.error(methodDetails + "Thread interrupted.");
            break;
        }
    }
    }
}
