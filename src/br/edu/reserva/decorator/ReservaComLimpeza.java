package br.edu.reserva.decorator;

public class ReservaComLimpeza extends ReservaDecorator {

    private static final double CUSTO_LIMPEZA = 30.00;

    public ReservaComLimpeza(ServicoAdicional componente) {
        super(componente);
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao() + " + Serviço de Limpeza";
    }

    @Override
    public double getCusto() {
        return componente.getCusto() + CUSTO_LIMPEZA;
    }
}