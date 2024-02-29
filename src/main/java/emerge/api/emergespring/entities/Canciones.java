package emerge.api.emergespring.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Canciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Canciones() {
    }

    public Canciones(Long id, String titulo, Album album) {
        this.id = id;
        this.titulo = titulo;
        this.album = album;
    }

    @Override
    public String toString() {
        return "\nCanciones [id=" + id + ", titulo=" + titulo + ", album=" + album + "]";
    }

    public Canciones(String titulo, Album album) {
        this.titulo = titulo;
        this.album = album;
    }

    
    
}
