package com.example.Real_Time.Event.Ticketing.System.Controller;

import com.example.Real_Time.Event.Ticketing.System.AplicationConfig.Configaration;
import com.example.Real_Time.Event.Ticketing.System.DTO.ResponseDTO;
import com.example.Real_Time.Event.Ticketing.System.Service.TicketPoolService;
import com.example.Real_Time.Event.Ticketing.System.util.VarList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "addTicket")
    public ResponseEntity addTickets(@RequestParam(required = false) Integer count){
        try{
           String res = ticketPoolService.addTicket(count);
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
}
