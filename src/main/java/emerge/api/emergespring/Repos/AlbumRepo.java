package emerge.api.emergespring.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import emerge.api.emergespring.DTOs.AlbumDto;
import emerge.api.emergespring.entities.Album;

public interface AlbumRepo extends JpaRepository<Album,Long>{
    @Query("SELECT new emerge.api.emergespring.DTOs.AlbumDto(a.id, a.titulo, a.img, a.fecha, a.descripcion, a.artista.nombre) FROM Album a")
    List<AlbumDto> dameAlbums();

    @Query("SELECT a FROM Album a WHERE a.artista.nombre=:nombre")
    public List<Album> albumId(@Param("nombre") String titulo);
}
