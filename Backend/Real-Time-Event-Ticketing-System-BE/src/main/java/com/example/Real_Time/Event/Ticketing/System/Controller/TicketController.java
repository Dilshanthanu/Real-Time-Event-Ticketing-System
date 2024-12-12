package com.example.Real_Time.Event.Ticketing.System.Controller;

import com.example.Real_Time.Event.Ticketing.System.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/ticket")
@CrossOrigin
@RequiredArgsConstructor
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/getTicket")
    public int getTicket(@RequestParam String Status) {
        return ticketService.getTicketsCount(Status);
    }
}
