package com.example.Real_Time.Event.Ticketing.System.Service;

import com.example.Real_Time.Event.Ticketing.System.AplicationConfig.Configaration;
import com.example.Real_Time.Event.Ticketing.System.Entity.Ticket;
import com.example.Real_Time.Event.Ticketing.System.Entity.TicketPool;
import com.example.Real_Time.Event.Ticketing.System.Repo.TicketPoolRepo;
import com.example.Real_Time.Event.Ticketing.System.Repo.TicketRepo;
import com.example.Real_Time.Event.Ticketing.System.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketPoolService {

    @Autowired
    TicketRepo ticketRepo;

    @Autowired
    TicketPoolRepo ticketPoolRepo;

    @Autowired
    ModelMapper modelMapper;

    public String initialize(Configaration configaration) {
        try {
            // Delete all existing data
            ticketRepo.deleteAll();
            ticketPoolRepo.deleteAll();

            // Create and save a new ticket
            Ticket ticket = new Ticket();
            Long newId = ticketRepo.findMaxId() + 1; // Custom query to get the highest id
            ticket.setId(newId);
            ticket.setStatus("AVAILABLE");
            ticket.setTicketCount(configaration.getTotalTickets());
            ticketRepo.save(ticket);

            // Map and save a new ticket pool
            TicketPool ticketPool = modelMapper.map(configaration, TicketPool.class);
            ticketPoolRepo.save(ticketPool);

            return VarList.RSP_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.RSP_ERROR;
        }
    }


    public synchronized String addTicket(int count) {
        try {
            int available = ticketRepo.findTotalTicketCountByStatus("AVAILABLE");
            int maxCapacity = ticketPoolRepo.findMaxTicketsCapacityById(1);

            if (available + count > maxCapacity) {
                count = maxCapacity - available;
            }
            if (count <= 0) {
                return VarList.RSP_ERROR;
            }

            Ticket ticket = ticketRepo.findTopByStatus("AVAILABLE");
            if (ticket == null) {
                ticket = new Ticket();
                ticket.setStatus("AVAILABLE");
                ticket.setCreatedAt(LocalDateTime.now());
            }
            ticketPoolRepo.updateTotalTicketsById(1,ticket.getTicketCount() + count);
            ticket.setTicketCount(ticket.getTicketCount() + count);

            Long newId = ticketRepo.findMaxId() + 1;
            ticket.setId(newId);


            System.out.println("Ticket updated successfully.");
            return VarList.RSP_SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            return VarList.RSP_ERROR;
        }

    }

    public synchronized String removeTicket(int count) {

       int availableTickets = ticketRepo.findTotalTicketCountByStatus("AVAILABLE");

       if (availableTickets == 0) {
           return VarList.RSP_NO_DATA_FOUND;
       }
        ticketPoolRepo.updateTotalTicketsById(1,ticketRepo.
                findTotalTicketCountByStatus("AVAILABLE") - count);
        ticketRepo.updateTicketCountByStatus(availableTickets - count,"AVAILABLE");

       Ticket ticket = new Ticket();
       ticket.setId(2L);
        ticket.setStatus("PURCHASED");
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setTicketCount(count);
        ticketRepo.save(ticket);

        return VarList.RSP_SUCCESS;

    }

    public List<TicketPool> ticketPool(){
       return ticketPoolRepo.findAll();
    }
}
