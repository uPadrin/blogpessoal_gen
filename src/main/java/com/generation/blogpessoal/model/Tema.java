package com.generation.blogpessoal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "tb_temas")
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "")
    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
