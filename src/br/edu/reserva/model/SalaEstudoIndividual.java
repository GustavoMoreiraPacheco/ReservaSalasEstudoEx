package br.edu.reserva.model;

public class SalaEstudoIndividual extends Sala {

    public SalaEstudoIndividual(String id, String nome) {
        super(id, nome, 1);
    }

    @Override
    public String getTipo() {
        return "Estudo Individual";
    }

    @Override
    public String getDescricao() {
        return "Sala silenciosa para estudo individual. Capacidade: 1 pessoa.";
    }
}