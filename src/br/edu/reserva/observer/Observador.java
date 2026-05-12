package br.edu.reserva.observer;

import br.edu.reserva.model.Reserva;

public interface Observador {

void atualizar(Reserva reserva, String evento);

Reserva getUltimaReservaAlterada();
}