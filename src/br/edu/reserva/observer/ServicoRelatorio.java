package br.edu.reserva.observer;

import br.edu.reserva.model.Reserva;
import br.edu.reserva.model.StatusReserva;
import br.edu.reserva.singleton.RepositorioReservas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoRelatorio implements Observador {

    private Reserva ultimaReservaAlterada;

@Override
    public void atualizar(Reserva reserva, String evento) {
        this.ultimaReservaAlterada = reserva;
        System.out.printf("[RELATÓRIO] Evento registrado: %-25s → Reserva %s%n",
            evento, reserva.getId());
    }

@Override
    public Reserva getUltimaReservaAlterada() {
        return ultimaReservaAlterada;
    }

public void gerarRelatorioDiario(LocalDate data) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\n══════════════════════════════════════════════════════════");
        System.out.println("  RELATÓRIO DIÁRIO — " + data.format(fmt));
        System.out.println("══════════════════════════════════════════════════════════");

        List<Reserva> reservasDoDia = RepositorioReservas.getInstancia()
            .listarTodas().stream()
            .filter(r -> r.getStatus() == StatusReserva.CONFIRMADA
                      || r.getStatus() == StatusReserva.MODIFICADA)
            .filter(r -> r.getInicio().toLocalDate().equals(data))
            .sorted(Comparator
                .comparing((Reserva r) -> r.getSala().getNome())
                .thenComparing(Reserva::getInicio))
            .collect(Collectors.toList());

        if (reservasDoDia.isEmpty()) {
            System.out.println("  Nenhuma reserva confirmada para esta data.");
        } else {
            String salaNomeAtual = "";
            for (Reserva r : reservasDoDia) {
                if (!r.getSala().getNome().equals(salaNomeAtual)) {
                    salaNomeAtual = r.getSala().getNome();
                    System.out.println("\n  ▶ " + salaNomeAtual);
                }
                System.out.println("      " + r);
            }
        }
        System.out.println("══════════════════════════════════════════════════════════\n");
    }
}