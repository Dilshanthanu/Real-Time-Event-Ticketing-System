package com.example.Real_Time.Event.Ticketing.System.Controller;

import com.example.Real_Time.Event.Ticketing.System.AplicationConfig.Configaration;
import com.example.Real_Time.Event.Ticketing.System.DTO.AddTicketsDto;
import com.example.Real_Time.Event.Ticketing.System.DTO.ResponseDTO;
import com.example.Real_Time.Event.Ticketing.System.Entity.TicketPool;
import com.example.Real_Time.Event.Ticketing.System.Service.TicketPoolService;
import com.example.Real_Time.Event.Ticketing.System.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/ticketPool")
@CrossOrigin
@RequiredArgsConstructor
public class TicketPoolController {

    @Autowired
    ResponseDTO responseDTO;

    @Autowired
    TicketPoolService ticketPoolService;


    @PostMapping(value = "/initialize")
    public ResponseEntity initializeTicketPool(@RequestBody Configaration configaration) {

        try {
            String res = ticketPoolService.initialize(configaration);
            if (res.equals("00")) {
                responseDTO.setMessage("Ticket Pool initialization successful");
                responseDTO.setContent(configaration);
                responseDTO.setCode(res);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setMessage("Ticket Pool initialization failed");
                responseDTO.setContent(configaration);
                responseDTO.setCode(res);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(configaration);
            responseDTO.setCode(VarList.RSP_FAIL);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/addTicket")
    public ResponseEntity<?> addTickets(@RequestBody AddTicketsDto countDto) {
        try {
            // Extract the integer value from AddTicketsDto
            int count = countDto.getCount(); // Assuming `getCount()` is a method in AddTicketsDto

            // Pass the integer value to ticketPoolService
            String res = ticketPoolService.addTicket(count);

            if (res.equals("00")) {
                responseDTO.setMessage("Ticket added successful");
                responseDTO.setContent(count);
                responseDTO.setCode(res);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setMessage("Ticket addition failed");
                responseDTO.setContent(count);
                responseDTO.setCode(res);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            responseDTO.setCode(VarList.RSP_FAIL);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping(value = "/removeTickets")
    public ResponseEntity removeTickets(@RequestParam(required = false) Integer count){
        try{
            String res = ticketPoolService.removeTicket(count);
            if (res.equals("00")) {
                responseDTO.setMessage("Ticket added successful");
                responseDTO.setContent(count);
                responseDTO.setCode(res);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setMessage("Ticket added failed");
                responseDTO.setContent(count);
                responseDTO.setCode(res);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(count);
            responseDTO.setCode(VarList.RSP_FAIL);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getTicketPool")
    public List<TicketPool> getTicketPool(){
        return ticketPoolService.ticketPool();
    }
}
