package emerge.api.emergespring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import emerge.api.emergespring.DTOs.AlbumDto;
import emerge.api.emergespring.Repos.AlbumRepo;
import emerge.api.emergespring.Repos.ArtistaRepo;
import emerge.api.emergespring.entities.Album;
import emerge.api.emergespring.entities.Artista;


@RestController
public class Controller {
    @Autowired
    private AlbumRepo albumRepo;

    @Autowired
    private ArtistaRepo artistaRepo;

    @GetMapping("/hola")
    public String hola(){
        return "hola";
    }

    @GetMapping("/artista")
    public List<Artista> artista(){
        return this.artistaRepo.findAll();
    }

    // @GetMapping("/album")
    // public ResponseEntity<List<Album>> album(){
    //     List<Album> x = this.albumRepo.findAll();
    //     List<AlbumDto> x2 = this.albumRepo.dameAlbums();
    //     System.out.println("anda!!!!!!!!!!!!!!!!!!!!!!");
    //     System.out.println(x2);
    //     return new ResponseEntity<>(x,HttpStatus.OK);
    // }
    @GetMapping("/album")
    public ResponseEntity<List<AlbumDto>> album(){
        List<AlbumDto> x = this.albumRepo.dameAlbums();
        return new ResponseEntity<>(x,HttpStatus.OK);
    }
}
