package br.edu.reserva.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {

    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final String id;
    private final Sala   sala;
    private final Usuario usuario;
    private LocalDateTime  inicio;
    private LocalDateTime  fim;
    private StatusReserva  status;

    public Reserva(String id, Sala sala, Usuario usuario,
                   LocalDateTime inicio, LocalDateTime fim) {
        this.id      = id;
        this.sala    = sala;
        this.usuario = usuario;
        this.inicio  = inicio;
        this.fim     = fim;
        this.status  = StatusReserva.CONFIRMADA;
    }

public String        getId()      { return id; }
    public Sala          getSala()    { return sala; }
    public Usuario       getUsuario() { return usuario; }
    public LocalDateTime getInicio()  { return inicio; }
    public LocalDateTime getFim()     { return fim; }
    public StatusReserva getStatus()  { return status; }

public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }
    public void setFim(LocalDateTime fim)       { this.fim    = fim; }
    public void setStatus(StatusReserva status) { this.status = status; }

public boolean conflitaCom(LocalDateTime novoInicio, LocalDateTime novoFim) {
        return this.inicio.isBefore(novoFim) && novoInicio.isBefore(this.fim);
    }

    @Override
    public String toString() {
        return String.format("Reserva[%s] Sala: %-12s | Usuário: %-20s | %s → %s | %s",
            id, sala.getNome(), usuario.getNome(),
            inicio.format(FMT), fim.format(FMT), status);
    }
}