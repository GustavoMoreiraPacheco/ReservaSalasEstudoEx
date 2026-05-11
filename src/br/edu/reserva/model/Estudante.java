package br.edu.reserva.model;

public class Estudante extends Usuario {

    private final String matricula;

    public Estudante(String id, String nome, String email, String matricula) {
        super(id, nome, email);
        this.matricula = matricula;
    }

    public String getMatricula() { return matricula; }

    @Override
    public String getTipo() { return "Estudante"; }
}