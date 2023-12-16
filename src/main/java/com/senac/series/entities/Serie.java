package com.senac.series.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_series")
public class Serie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "O campo deve ser preenchido.")
    private String titulo;

    @NotEmpty(message = "O campo deve ser preenchido.")
    private String genero;

    @Min(value = 1, message = "Deve ser maior que 0.")
    private int anoLancamento;

    
    public Serie() {
    }


    public Serie(Long id, String titulo, String genero, int anoLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getGenero() {
        return genero;
    }


    public void setGenero(String genero) {
        this.genero = genero;
    }


    public int getAnoLancamento() {
        return anoLancamento;
    }


    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    

}
