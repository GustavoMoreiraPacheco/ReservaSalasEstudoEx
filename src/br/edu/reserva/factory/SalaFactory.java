package br.edu.reserva.factory;

import br.edu.reserva.model.Sala;

public abstract class SalaFactory {

public abstract Sala criarSala(String id, String nome, int capacidade);

public Sala criar(String id, String nome, int capacidade) {
        Sala sala = criarSala(id, nome, capacidade);
        System.out.println("[Factory] Sala criada: " + sala);
        return sala;
    }
}