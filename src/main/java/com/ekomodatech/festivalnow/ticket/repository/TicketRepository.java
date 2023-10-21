package com.ekomodatech.festivalnow.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ekomodatech.festivalnow.ticket.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long>{
    
}
