package br.edu.reserva.decorator;

public class ReservaBase implements ServicoAdicional {

    private final String reservaId;

    public ReservaBase(String reservaId) {
        this.reservaId = reservaId;
    }

    @Override
    public String getDescricao() {
        return "Reserva básica [" + reservaId + "]";
    }

    @Override
    public double getCusto() {
        return 0.0;
    }
}