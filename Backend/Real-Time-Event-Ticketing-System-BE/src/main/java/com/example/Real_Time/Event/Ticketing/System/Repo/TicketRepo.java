package com.example.Real_Time.Event.Ticketing.System.Repo;

import com.example.Real_Time.Event.Ticketing.System.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    @Query("SELECT SUM(e.ticketCount) FROM Ticket e WHERE e.status = :status")
    Integer findTotalTicketCountByStatus(@Param("status") String status);


    @Query("SELECT t.id FROM Ticket t WHERE t.status = :status")
    Long findIdsByStatus(String status);

    @Query("SELECT t FROM Ticket t WHERE t.status = :status")
    Ticket findTopByStatus(@Param("status") String status);


    @Modifying
    @Transactional
    @Query("UPDATE Ticket t SET t.ticketCount = :ticketCount WHERE t.status = :status")
    int updateTicketCountByStatus(@Param("ticketCount") int ticketCount, @Param("status") String status);

    @Query("SELECT COALESCE(MAX(t.id), 0) FROM Ticket t")
    Long findMaxId();
}
