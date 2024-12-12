package com.example.Real_Time.Event.Ticketing.System.Service;

import com.example.Real_Time.Event.Ticketing.System.Repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TicketRepo ticketRepo;

    public int getTicketsCount(String status){
       int count = ticketRepo.findTotalTicketCountByStatus(status);

       return count;
    }
}
