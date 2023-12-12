package com.generation.blogpessoal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.blogpessoal.model.Postagem;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo")String titulo);
}
