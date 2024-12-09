package com.example.Real_Time.Event.Ticketing.System.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TicketPool")
public class TicketPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_tickets")
    private int totalTickets;

    @Column(name = "tickets_release_rate")
    private int ticketsReleaseRate;

    @Column(name = "max_tickets_capacity") // Corrected column name
    private int maxTicketsCapacity;
}
