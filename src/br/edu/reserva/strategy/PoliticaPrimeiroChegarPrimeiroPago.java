package br.edu.reserva.strategy;

import br.edu.reserva.model.Reserva;
import br.edu.reserva.model.Sala;
import br.edu.reserva.model.StatusReserva;
import br.edu.reserva.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class PoliticaPrimeiroChegarPrimeiroPago implements PoliticaDeReserva {

    @Override
    public boolean podeReservar(Usuario usuario, Sala sala,
                                LocalDateTime inicio, LocalDateTime fim,
                                List<Reserva> reservasExistentes) {
        return reservasExistentes.stream()
            .filter(r -> r.getSala().getId().equals(sala.getId()))
            .filter(r -> r.getStatus() == StatusReserva.CONFIRMADA
                      || r.getStatus() == StatusReserva.MODIFICADA)
            .noneMatch(r -> r.conflitaCom(inicio, fim));
    }

    @Override
    public String getNome() {
        return "Primeiro a Reservar (FCFS)";
    }
}