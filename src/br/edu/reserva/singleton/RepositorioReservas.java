package br.edu.reserva.singleton;

import br.edu.reserva.model.Reserva;
import br.edu.reserva.model.Sala;
import br.edu.reserva.model.StatusReserva;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class RepositorioReservas {

private static volatile RepositorioReservas instancia;

    private RepositorioReservas() {}

    public static RepositorioReservas getInstancia() {
        if (instancia == null) {
            synchronized (RepositorioReservas.class) {
                if (instancia == null) {
                    instancia = new RepositorioReservas();
                }
            }
        }
        return instancia;
    }

private final List<Sala>    salas    = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();
    private final ReadWriteLock lock     = new ReentrantReadWriteLock();

public void adicionarSala(Sala sala) {
        lock.writeLock().lock();
        try { salas.add(sala); }
        finally { lock.writeLock().unlock(); }
    }

    public List<Sala> listarSalas() {
        lock.readLock().lock();
        try { return Collections.unmodifiableList(new ArrayList<>(salas)); }
        finally { lock.readLock().unlock(); }
    }

    public Optional<Sala> buscarSalaPorId(String id) {
        lock.readLock().lock();
        try {
            return salas.stream().filter(s -> s.getId().equals(id)).findFirst();
        } finally { lock.readLock().unlock(); }
    }

public List<Sala> listarSalasDisponiveis(LocalDateTime inicio, LocalDateTime fim) {
        lock.readLock().lock();
        try {
            List<String> ocupadas = reservas.stream()
                .filter(r -> r.getStatus() == StatusReserva.CONFIRMADA
                          || r.getStatus() == StatusReserva.MODIFICADA)
                .filter(r -> r.conflitaCom(inicio, fim))
                .map(r -> r.getSala().getId())
                .collect(Collectors.toList());

            return salas.stream()
                .filter(s -> !ocupadas.contains(s.getId()))
                .collect(Collectors.toList());
        } finally { lock.readLock().unlock(); }
    }

public void salvarReserva(Reserva reserva) {
        lock.writeLock().lock();
        try { reservas.add(reserva); }
        finally { lock.writeLock().unlock(); }
    }

    public Optional<Reserva> buscarReservaPorId(String id) {
        lock.readLock().lock();
        try {
            return reservas.stream().filter(r -> r.getId().equals(id)).findFirst();
        } finally { lock.readLock().unlock(); }
    }

    public List<Reserva> listarTodas() {
        lock.readLock().lock();
        try { return Collections.unmodifiableList(new ArrayList<>(reservas)); }
        finally { lock.readLock().unlock(); }
    }

    public List<Reserva> listarPorSala(String salaId) {
        lock.readLock().lock();
        try {
            return reservas.stream()
                .filter(r -> r.getSala().getId().equals(salaId))
                .collect(Collectors.toList());
        } finally { lock.readLock().unlock(); }
    }
}