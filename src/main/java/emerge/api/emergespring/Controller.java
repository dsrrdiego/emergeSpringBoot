package emerge.api.emergespring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import emerge.api.emergespring.DTOs.AlbumDto;
import emerge.api.emergespring.DTOs.CancionDto;
import emerge.api.emergespring.Repos.AlbumRepo;
import emerge.api.emergespring.Repos.ArtistaRepo;
import emerge.api.emergespring.Repos.CancionesRepo;
import emerge.api.emergespring.entities.Album;
import emerge.api.emergespring.entities.Artista;
import emerge.api.emergespring.entities.Canciones;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



@RestController
public class Controller {
    @Autowired
    private AlbumRepo albumRepo;

    @Autowired
    private ArtistaRepo artistaRepo;

    @Autowired
    private CancionesRepo cancionesRepo;

    
    @GetMapping("/artista")
    public List<Artista> artista(){
        return this.artistaRepo.findAll();
    }

    @GetMapping("/album")
    public ResponseEntity<List<AlbumDto>> album(){
        List<AlbumDto> x = this.albumRepo.dameAlbums();
        return new ResponseEntity<>(x,HttpStatus.OK);
    }

    @GetMapping("/albumXArtista/{titulo}")
    public ResponseEntity<List<Album>> albumId(@PathVariable String titulo){
        List<Album> a = albumRepo.albumId(titulo);
        System.out.println(a);
        return new ResponseEntity<>(a,HttpStatus.OK);
    }

    @GetMapping(value="/dameImagen/{artista}/{album}/{img}", produces =MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> dameImagen(@PathVariable String img,@PathVariable String artista, @PathVariable String album) throws IOException{
        System.out.println(artista+album+img);
        Resource resource = new ClassPathResource("emerge/"+artista+"/"+album+"/" + img);
        byte[] imagen = Files.readAllBytes(Path.of(resource.getURI()));
        return new ResponseEntity<byte[]>(imagen,HttpStatus.OK);
    }
    

    @GetMapping("/canciones/{albumId}")
    public ResponseEntity<List<Canciones>> canciones(@PathVariable Long albumId){
        List<Canciones> x = cancionesRepo.porId(albumId);
        return new ResponseEntity<List<Canciones>>(x,HttpStatus.OK);
    }
    

    @GetMapping(value="/dameCancion/{artista}/{album}/{cancion}" ,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> obtenerAudio(
        @PathVariable("artista") String artista,
        @PathVariable("album") String album,
        @PathVariable("cancion") String cancion ) throws IOException {
        Resource resource = new ClassPathResource("emerge"+"/"+artista+"/"+album+"/"+cancion+".mp3");
        byte[] audio = Files.readAllBytes(Path.of(resource.getURI()));
        // return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(audio);
        return new ResponseEntity<>(audio, HttpStatus.OK);
    }
  

    
}
