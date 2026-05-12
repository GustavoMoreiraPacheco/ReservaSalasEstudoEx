package br.edu.reserva.observer;

import br.edu.reserva.model.Reserva;

public interface Observable {

    void adicionarObservador(Observador observador);

    void removerObservador(Observador observador);

void notificarObservadores(Reserva reserva, String evento);
}