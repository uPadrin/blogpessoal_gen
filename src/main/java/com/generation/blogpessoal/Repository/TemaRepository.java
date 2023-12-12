package com.generation.blogpessoal.Repository;

import com.generation.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Postagem, Long> {
}
