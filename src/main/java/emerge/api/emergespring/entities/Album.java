package emerge.api.emergespring.entities;


import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;
    private String img;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String descripcion;



    @ManyToOne
    @JoinColumn(name = "artista_id")
    // @JsonIgnore
    private Artista artista;

    @OneToMany(mappedBy = "album")
    private List<Canciones> canciones;

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



    public String getImg() {
        return img;
    }



    public void setImg(String img) {
        this.img = img;
    }



    public Date getFecha() {
        return fecha;
    }



    @Override
    public String toString() {
        return "Album [id=" + id + ", titulo=" + titulo + ", img=" + img + ", fecha=" + fecha + ", descripcion="
                + descripcion + ", artista=" + artista + "]";
    }



    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }



    public String getDescripcion() {
        return descripcion;
    }



    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public Artista getArtista() {
        return artista;
    }



    public void setArtista(Artista artista) {
        this.artista = artista;
    }



    public Album(String titulo, String img, Date fecha, String descripcion, Artista artista) {
        this.titulo = titulo;
        this.img = img;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.artista = artista;
    }

    
}
