package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;
import com.generation.blogpessoal.model.Postagem;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
// Em um ambiente de produção real, substituiríamos o asterisco ("*") // pelo endereço do servidor front-end.

public class PostagemController {

    @Autowired // Injeção de dependência.
    private PostagemRepository postagemRepository;

    @Autowired
    private TemaRepository temaRepository;

    @GetMapping // Metodo getAll
    public ResponseEntity<List<Postagem>> getAll() {
        return ResponseEntity.ok(postagemRepository.findAll()); // SELECT * FROM tb_postagens
    }

    @GetMapping("/{id}") //Metodo getById
    public ResponseEntity<Postagem> getById(@PathVariable Long id) {

        //		Optional<Postagem> resposta = postagemRepository.findById(id);
        //
        //		if (resposta.isPresent())
        //			ResponseEntity.ok(resposta);
        //		else
        //			ResponseEntity.notFound().build();

        return postagemRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // SELECT * FROM tb_postagens WHERE id = "id"
    }

    @GetMapping("/titulo/{titulo}") //Metodo getByTitulo
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {

        if (temaRepository.existsById(postagem.getTema().getId()))
            return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem)); // INSERT INTO
        // tb_postagens
        // (titulo,
        // texto) VALUE
        // (?, ?);

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema inexistente!", null);
    }

    @PutMapping
    public ResponseEntity<Postagem> update(@Valid @RequestBody Postagem postagem) {

        if (postagemRepository.existsById(postagem.getId())) {
            if (temaRepository.existsById(postagem.getTema().getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema inexistente!", null);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Optional<Postagem> postagem = postagemRepository.findById(id);

        if (postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        postagemRepository.deleteById(id); // DELETE FROM tb_postagens WHERE id = ?;
    }
}

