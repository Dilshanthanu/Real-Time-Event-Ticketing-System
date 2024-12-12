package com.example.Real_Time.Event.Ticketing.System.Repo;

import com.example.Real_Time.Event.Ticketing.System.Entity.TicketPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TicketPoolRepo extends JpaRepository<TicketPool, Integer> {

    @Query("SELECT t.maxTicketsCapacity FROM TicketPool t WHERE t.id = :id")
    int findMaxTicketsCapacityById(int id);

    @Modifying
    @Transactional
    @Query("UPDATE TicketPool tp SET tp.totalTickets = :totalTickets WHERE tp.id = :id")
    void updateTotalTicketsById(@Param("id") int id, @Param("totalTickets") int totalTickets);
}
