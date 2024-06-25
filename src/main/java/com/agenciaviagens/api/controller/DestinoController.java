package com.agenciaviagens.api.controller;

import com.agenciaviagens.api.model.Destino;
import com.agenciaviagens.api.service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @PostMapping
    public ResponseEntity<Destino> cadastrarDestino(@RequestBody Destino destino) {
        Destino novoDestino = destinoService.cadastrarDestino(destino);
        return new ResponseEntity<>(novoDestino, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Destino>> listarDestinos() {
        List<Destino> destinos = destinoService.listarDestinos();
        return new ResponseEntity<>(destinos, HttpStatus.OK);
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<Optional<Destino>> buscarDestinoPorNomeOuLocalizacao(@RequestParam String termo) {
        Optional<Destino> destino = destinoService.buscarDestinoPorNomeOuLocalizacao(termo);
        return new ResponseEntity<>(destino, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Destino>> visualizarDestino(@PathVariable Long id) {
        Optional<Destino> destino = destinoService.visualizarDestino(id);
        return new ResponseEntity<>(destino, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        destinoService.excluirDestino(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/avaliar")
    public ResponseEntity<Destino> avaliarDestino(@PathVariable Long id, @RequestParam double nota) {
        Destino destinoAvaliado = destinoService.avaliarDestino(id, nota);
        if (destinoAvaliado != null) {
            return new ResponseEntity<>(destinoAvaliado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
