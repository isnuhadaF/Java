package com.lastma.data.repositories;

import com.lastma.data.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketRepoImpl implements TicketRepo {

    private List<Ticket> tickets = new ArrayList<>();
    private int numberOfTickets;

    @Override
    public Ticket save(Ticket ticket) {
        ticket.setId(++numberOfTickets);
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public long count() {
        return numberOfTickets;
    }

    @Override
    public Ticket findById(int id) {
        return null;
    }

    @Override
    public List<Ticket> findAll() {
        return List.of();
    }

    @Override
    public void delete(int ticketId) {

    }

    @Override
    public void deleteAll() {

    }
}