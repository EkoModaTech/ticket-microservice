package com.ekomodatech.festivalnow.ticket.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekomodatech.festivalnow.ticket.entity.Ticket;
import com.ekomodatech.festivalnow.ticket.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService{
    
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket findByIdTicket(Long id){
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ticket> findAllTicket(){
        return ticketRepository.findAll();
    }

    @Override
    public void deleteTicket(Long id){
        ticketRepository.deleteById(id);
    }

    @Override
    public String randomCode(){
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder codigoAleatorio = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(caracteres.length());
            codigoAleatorio.append(caracteres.charAt(index));
        }

        return codigoAleatorio.toString();
    }

}
