package br.edu.reserva.decorator;

public abstract class ReservaDecorator implements ServicoAdicional {

    protected final ServicoAdicional componente;

    protected ReservaDecorator(ServicoAdicional componente) {
        this.componente = componente;
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao();
    }

    @Override
    public double getCusto() {
        return componente.getCusto();
    }
}