package com.example.Real_Time.Event.Ticketing.System.Entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id

    private Long id;

    @Column(nullable = false)
    private String status;

    @Column( updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private int ticketCount;
    @Version
    private Long version;
}
