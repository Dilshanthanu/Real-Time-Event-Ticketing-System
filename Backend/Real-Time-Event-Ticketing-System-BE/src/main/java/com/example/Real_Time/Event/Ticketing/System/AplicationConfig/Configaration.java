package com.example.Real_Time.Event.Ticketing.System.AplicationConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Configaration {
    private int totalTickets;
    private int ticketsReleaseRate;
    private int maxTicketsCapacity;
}