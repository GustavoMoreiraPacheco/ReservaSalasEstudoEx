package br.edu.reserva.observer;

import br.edu.reserva.model.Reserva;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorEventos implements Observable {

private static volatile GerenciadorEventos instancia;

    private GerenciadorEventos() {}

    public static GerenciadorEventos getInstancia() {
        if (instancia == null) {
            synchronized (GerenciadorEventos.class) {
                if (instancia == null) {
                    instancia = new GerenciadorEventos();
                }
            }
        }
        return instancia;
    }

private final List<Observador> observadores = new ArrayList<>();

    @Override
    public void adicionarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

@Override
    public void notificarObservadores(Reserva reserva, String evento) {
        for (Observador obs : observadores) {
            obs.atualizar(reserva, evento);
        }
    }
}