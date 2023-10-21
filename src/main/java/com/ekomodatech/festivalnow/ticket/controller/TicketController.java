package com.ekomodatech.festivalnow.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.ekomodatech.festivalnow.ticket.entity.Ticket;
import com.ekomodatech.festivalnow.ticket.repository.TicketRepository;
import com.ekomodatech.festivalnow.ticket.services.TicketService;


@RestController
@RequestMapping("/ticket")
public class TicketController {
    
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket newTicket) {
        try {
            String codigo = ticketService.randomCode();

            newTicket.setCode(codigo);

            Ticket createdTicket = ticketRepository.save(newTicket);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Ticket> findTicket(@PathVariable Long id) {
        try {
            Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));
            return ResponseEntity.ok(ticket);
        } catch (ResponseStatusException ex) {
            throw ex;
        }  catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @GetMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Ticket>> listTickets(){
        try{
            List<Ticket> tickets = ticketRepository.findAll();
            return ResponseEntity.ok(tickets);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> updateTicket(@PathVariable Long id,@RequestBody Ticket updatedTicket){
        try {
            Ticket ticket = ticketRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

            ticket.setCode(updatedTicket.getCode());
            ticket.setIdEvent(updatedTicket.getIdEvent());
            ticket.setPrice(updatedTicket.getPrice());

            ticketRepository.save(ticket);
            return ResponseEntity.ok("Event updated successfully");
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        try {
            Ticket ticket = ticketRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
            ticketRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex);
        }
    }

}
