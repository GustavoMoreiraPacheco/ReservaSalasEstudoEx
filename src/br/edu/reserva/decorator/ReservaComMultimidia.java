package br.edu.reserva.decorator;

public class ReservaComMultimidia extends ReservaDecorator {

    private static final double CUSTO_MULTIMIDIA = 50.00;

    public ReservaComMultimidia(ServicoAdicional componente) {
        super(componente);
    }

    @Override
    public String getDescricao() {
        return componente.getDescricao() + " + Equipamento Multimídia (projetor, caixas de som)";
    }

    @Override
    public double getCusto() {
        return componente.getCusto() + CUSTO_MULTIMIDIA;
    }
}