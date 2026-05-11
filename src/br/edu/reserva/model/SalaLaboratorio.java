package br.edu.reserva.model;

public class SalaLaboratorio extends Sala {

    private final String equipamentos;

    public SalaLaboratorio(String id, String nome, int capacidade, String equipamentos) {
        super(id, nome, capacidade);
        this.equipamentos = equipamentos;
    }

    public String getEquipamentos() {
        return equipamentos;
    }

    @Override
    public String getTipo() {
        return "Laboratório";
    }

    @Override
    public String getDescricao() {
        return String.format(
            "Laboratório com: %s. Capacidade: %d pessoas.", equipamentos, getCapacidade());
    }
}