package emerge.api.emergespring.DTOs;

import java.sql.Date;

public class AlbumDto {
    
    private Long id;
    private String titulo;
    private String img;
    private Date fecha;
    private String descripcion;
    private String artista;
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
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public AlbumDto(Long id, String titulo, String img, Date fecha, String descripcion, String artista) {
        this.id = id;
        this.titulo = titulo;
        this.img = img;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.artista = artista;
    }

    
}
