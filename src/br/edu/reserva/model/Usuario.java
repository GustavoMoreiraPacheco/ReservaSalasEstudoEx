package br.edu.reserva.model;

public abstract class Usuario {

    private final String id;
    private final String nome;
    private final String email;

    protected Usuario(String id, String nome, String email) {
        this.id    = id;
        this.nome  = nome;
        this.email = email;
    }

    public String getId()    { return id; }
    public String getNome()  { return nome; }
    public String getEmail() { return email; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", getTipo(), nome, email);
    }
}