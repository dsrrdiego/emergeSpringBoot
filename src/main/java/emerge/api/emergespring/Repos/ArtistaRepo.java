package emerge.api.emergespring.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import emerge.api.emergespring.entities.Artista;

public interface ArtistaRepo extends JpaRepository<Artista,Long>{
    
}
