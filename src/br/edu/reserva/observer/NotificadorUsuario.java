package br.edu.reserva.observer;

import br.edu.reserva.model.Reserva;

public class NotificadorUsuario implements Observador {

    private Reserva ultimaReservaAlterada;

@Override
    public void atualizar(Reserva reserva, String evento) {
        this.ultimaReservaAlterada = reserva;
        System.out.printf(
            "[NOTIFICAÇÃO → %s] %s | Evento: %-25s | Reserva: %s%n",
            reserva.getUsuario().getEmail(),
            reserva.getUsuario().getNome(),
            evento,
            reserva.getId());
    }

@Override
    public Reserva getUltimaReservaAlterada() {
        return ultimaReservaAlterada;
    }
}