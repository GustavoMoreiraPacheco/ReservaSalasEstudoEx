import br.edu.reserva.singleton.RepositorioReservas;
import br.edu.reserva.model.Sala;
import br.edu.reserva.model.Usuario;

import java.time.LocalDateTime;
import java.util.*;

public class proxy {
    private RepositorioReservas repositorio;
    
    // Cache de salas disponíveis
    private Map<String, Sala> cacheSalas = new HashMap<>();
    private LocalDateTime ultimaAtualizacaoCache;
    private static final long TEMPO_CACHE_MINUTOS = 15;
    
    // Controle de acesso
    private Set<Usuario> usuariosAutorizados = new HashSet<>();

    public proxy() {
        this.repositorio = RepositorioReservas.getInstancia();
        this.ultimaAtualizacaoCache = LocalDateTime.now();
    }

   
    
    public void autorizarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuariosAutorizados.add(usuario);
            System.out.println("[PROXY] Usuário autorizado: " + usuario.getNome());
        }
    }

    
     //Verifica se um usuário possui acesso
    
    private boolean podeAcessarSala(Usuario usuario) {
        if (usuario == null) {
            throw new SecurityException("[PROXY] Usuário não pode ser nulo para acessar salas");
        }
        
        boolean temAcesso = usuariosAutorizados.contains(usuario);
        if (!temAcesso) {
            System.err.println("[PROXY] Acesso negado para: " + usuario.getNome());
        }
        return temAcesso;
    }

   
     //Verifica se o cache está desatualizado
    
    private boolean cacheDesatualizado() {
        return ultimaAtualizacaoCache.plusMinutes(TEMPO_CACHE_MINUTOS).isBefore(LocalDateTime.now());
    }

    /**Limpa o cache quando desatualizado*/
    private void limparCacheSeNecessario() {
        if (cacheDesatualizado()) {
            cacheSalas.clear();
            ultimaAtualizacaoCache = LocalDateTime.now();
            System.out.println("[PROXY] Cache de salas limpo e atualizado");
        }
    }

  

    /**Obtém uma sala pelo ID com cache, controle de acesso e tratamento de exceções*/
    public Sala getidSala(String id, Usuario usuario) {
        try {
            // Validação de entrada
            if (id.trim().isEmpty()) {
                throw new IllegalArgumentException("[PROXY] ID da sala não pode ser nulo ou vazio");
            }
            
            // Controle de acesso
            if (!podeAcessarSala(usuario)) {
                throw new SecurityException("[PROXY] Usuário não tem permissão para acessar salas");
            }
            
            // Verificar cache
            if (cacheSalas.containsKey(id)) {
                System.out.println("[PROXY] Sala retornada do cache: " + id);
                return cacheSalas.get(id);
            }
            
            // Buscar do repositório
            Optional<Sala> salaOpt = repositorio.buscarSalaPorId(id);
            if (salaOpt.isPresent()) {
                Sala sala = salaOpt.get();
                cacheSalas.put(id, sala);
                return sala;
            }
            
            System.out.println("[PROXY] Sala não encontrada: " + id);
            return null;
            
        } catch (SecurityException e) {
            System.err.println(e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("[PROXY] Erro ao buscar sala: " + e.getMessage());
            throw new RuntimeException("[PROXY] Falha ao acessar sala " + id, e);
        }
    }

    /*Lista salas disponíveis com cache, controle de acesso e tratamento de exceções*/
    public List<Sala> read(Usuario usuario) {
        try {
            // Controle de acesso
            if (!podeAcessarSala(usuario)) {
                throw new SecurityException("[PROXY] Usuário não tem permissão para listar salas");
            }
            
            // Limpar cache se desatualizado
            limparCacheSeNecessario();
            
            // Se cache está preenchido, usar cache
            if (!cacheSalas.isEmpty()) {
                System.out.println("[PROXY] Retornando " + cacheSalas.size() + " salas do cache");
                return new ArrayList<>(cacheSalas.values());
            }
            
            // Buscar salas disponíveis
            LocalDateTime now = LocalDateTime.now();
            List<Sala> salasDisponiveis = repositorio.listarSalasDisponiveis(now, now.plusHours(1));
            
            // Armazenar em cache
            salasDisponiveis.forEach(sala -> cacheSalas.put(sala.getId(), sala));
            System.out.println("[PROXY] " + salasDisponiveis.size() + " salas carregadas em cache");
            
            return salasDisponiveis;
            
        } catch (SecurityException e) {
            System.err.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("[PROXY] Erro ao listar salas: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /*Limpa o cache manualmente*/
    public void limparCache() {
        cacheSalas.clear();
        ultimaAtualizacaoCache = LocalDateTime.now();
        System.out.println("[PROXY] Cache limpo manualmente");
    }
}
