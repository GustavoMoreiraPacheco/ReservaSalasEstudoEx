package br.edu.reserva.strategy;

import br.edu.reserva.model.Professor;
import br.edu.reserva.model.Reserva;
import br.edu.reserva.model.Sala;
import br.edu.reserva.model.StatusReserva;
import br.edu.reserva.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PoliticaPrioridadeDocente implements PoliticaDeReserva {

    @Override
    public boolean podeReservar(Usuario usuario, Sala sala,
                                LocalDateTime inicio, LocalDateTime fim,
                                List<Reserva> reservasExistentes) {

        boolean temConflito = reservasExistentes.stream()
            .filter(r -> r.getSala().getId().equals(sala.getId()))
            .filter(r -> r.getStatus() == StatusReserva.CONFIRMADA
                      || r.getStatus() == StatusReserva.MODIFICADA)
            .anyMatch(r -> r.conflitaCom(inicio, fim));

        if (!temConflito) {
            return true;
        }

return usuario instanceof Professor;
    }

public List<Reserva> getReservasParaCancelar(Usuario usuario, Sala sala,
                                                  LocalDateTime inicio, LocalDateTime fim,
                                                  List<Reserva> reservasExistentes) {
        if (!(usuario instanceof Professor)) {
            return List.of();
        }

        return reservasExistentes.stream()
            .filter(r -> r.getSala().getId().equals(sala.getId()))
            .filter(r -> r.getStatus() == StatusReserva.CONFIRMADA
                      || r.getStatus() == StatusReserva.MODIFICADA)
            .filter(r -> r.conflitaCom(inicio, fim))
            .filter(r -> !(r.getUsuario() instanceof Professor))
            .collect(Collectors.toList());
    }

    @Override
    public String getNome() {
        return "Prioridade Docente";
    }
}