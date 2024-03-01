package emerge.api.emergespring.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import emerge.api.emergespring.entities.Artista;

public interface ArtistaRepo extends JpaRepository<Artista,Long>{
    

    @Query("SELECT a FROM Artista a WHERE a.nombre=:nombre")
    Artista dameArtistaNombre(@Param("nombre") String artista);
    
}
