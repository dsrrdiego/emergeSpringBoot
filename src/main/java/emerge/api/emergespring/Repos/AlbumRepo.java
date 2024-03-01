package emerge.api.emergespring.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import emerge.api.emergespring.DTOs.AlbumDto;
import emerge.api.emergespring.entities.Album;

public interface AlbumRepo extends JpaRepository<Album,Long>{
    Object xTitulo = null;

    @Query("SELECT new emerge.api.emergespring.DTOs.AlbumDto(a.id, a.titulo, a.img, a.fecha, a.descripcion, a.artista.nombre) FROM Album a")
    List<AlbumDto> dameAlbums();

    @Query("SELECT a FROM Album a WHERE a.artista.nombre=:nombre")
    public List<Album> albumId(@Param("nombre") String titulo);
 
    @Query("SELECT a FROM Album a WHERE a.titulo=:nombre")
    public Album xTitulo(@Param("nombre") String titulo);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Album a WHERE a.titulo = :titulo")
    boolean existe(@Param("titulo") String album);
    
}