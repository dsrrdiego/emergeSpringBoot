package emerge.api.emergespring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import emerge.api.emergespring.Repos.AlbumRepo;
import emerge.api.emergespring.entities.Album;


@RestController
public class Controller {
    @Autowired
    private AlbumRepo albumRepo;

    @GetMapping("/hola")
    public String hola(){
        return "hola";
    }

    @GetMapping("/artista")
    public List<Album> artista(){
        List<Album> x = this.albumRepo.findAll();
        return x;
    }
}
