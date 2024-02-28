package emerge.api.emergespring.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatusCode;

// import emerge.api.emergespring.DTOs.CancionDto;
import emerge.api.emergespring.entities.Canciones;

public interface CancionesRepo extends JpaRepository<Canciones,Long>{
    // @Query("SELECT new emerge.api.emergespring.DTOs.CancionDto(a.id,a.titulo, a.album.id) FROM Canciones a WHERE a.album.id = :albumId")
    // List<CancionDto> dameCanciones(@Param("albumId") Long albumId);
    // @Query ("SELECT c FROM canciones c WHERE c.album.id =:albumId")
    // List<Canciones> dameCanciones(@Param("albumId") Long albumId);

    @Query("Select c FROM Canciones c WHERE c.album.id=:albumId")
    public List<Canciones> porId(@Param("albumId") Long albumId);

}
