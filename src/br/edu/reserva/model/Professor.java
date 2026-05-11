package br.edu.reserva.model;

public class Professor extends Usuario {

    private final String siape;

    public Professor(String id, String nome, String email, String siape) {
        super(id, nome, email);
        this.siape = siape;
    }

    public String getSiape() { return siape; }

    @Override
    public String getTipo() { return "Professor"; }
}