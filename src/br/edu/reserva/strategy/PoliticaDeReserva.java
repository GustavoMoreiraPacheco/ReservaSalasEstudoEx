package br.edu.reserva.strategy;

import br.edu.reserva.model.Reserva;
import br.edu.reserva.model.Sala;
import br.edu.reserva.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public interface PoliticaDeReserva {

boolean podeReservar(Usuario usuario, Sala sala,
                         LocalDateTime inicio, LocalDateTime fim,
                         List<Reserva> reservasExistentes);

String getNome();
}