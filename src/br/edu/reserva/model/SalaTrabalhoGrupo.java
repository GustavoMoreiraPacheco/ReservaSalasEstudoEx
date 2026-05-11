package br.edu.reserva.model;

public class SalaTrabalhoGrupo extends Sala {

    public SalaTrabalhoGrupo(String id, String nome, int capacidade) {
        super(id, nome, capacidade);
    }

    @Override
    public String getTipo() {
        return "Trabalho em Grupo";
    }

    @Override
    public String getDescricao() {
        return String.format(
            "Sala para trabalho colaborativo. Capacidade: %d pessoas.", getCapacidade());
    }
}