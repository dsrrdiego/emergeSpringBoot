package emerge.api.emergespring.DTOs;



public class AlbumDto {
    
    private Long id;
    private String titulo;
    private String img;
    private String fecha;
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
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
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
    public AlbumDto(Long id, String titulo, String img, String fecha, String descripcion, String artista) {
        this.id = id;
        this.titulo = titulo;
        this.img = img;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.artista = artista;
    }

    
}
