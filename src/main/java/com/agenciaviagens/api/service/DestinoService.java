package com.agenciaviagens.api.service;

import com.agenciaviagens.api.model.Destino;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {
    private List<Destino> destinos = new ArrayList<>();
    private Long currentId = 1L;

    public Destino cadastrarDestino(Destino destino) {
        destino.setId(currentId++);
        destinos.add(destino);
        return destino;
    }

    public List<Destino> listarDestinos() {
        return destinos;
    }

    public Optional<Destino> buscarDestinoPorNomeOuLocalizacao(String termo) {
        return destinos.stream()
                .filter(d -> d.getNome().equalsIgnoreCase(termo) || d.getLocalizacao().equalsIgnoreCase(termo))
                .findFirst();
    }

    public Optional<Destino> visualizarDestino(Long id) {
        return destinos.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    public void excluirDestino(Long id) {
        destinos.removeIf(d -> d.getId().equals(id));
    }

    public Destino avaliarDestino(Long id, double nota) {
        Optional<Destino> destinoOptional = visualizarDestino(id);
        if (destinoOptional.isPresent()) {
            Destino destino = destinoOptional.get();
            double novaAvaliacao = ((destino.getAvaliacaoMedia() * destino.getNumeroAvaliacoes()) + nota) / (destino.getNumeroAvaliacoes() + 1);
            destino.setAvaliacaoMedia(novaAvaliacao);
            destino.setNumeroAvaliacoes(destino.getNumeroAvaliacoes() + 1);
            return destino;
        }
        return null;
    }
}
