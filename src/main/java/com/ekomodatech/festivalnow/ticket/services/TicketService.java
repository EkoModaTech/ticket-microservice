package com.ekomodatech.festivalnow.ticket.services;

import java.util.List;

import com.ekomodatech.festivalnow.ticket.entity.Ticket;

public interface TicketService {
    public Ticket save(Ticket ticket);
    public Ticket findByIdTicket(Long id);
    public List<Ticket> findAllTicket();
    public void deleteTicket(Long id);
    public String randomCode();
}
