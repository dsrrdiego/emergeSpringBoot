package emerge.api.emergespring.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import emerge.api.emergespring.entities.Album;

public interface AlbumRepo extends JpaRepository<Album,Long>{
    
}
