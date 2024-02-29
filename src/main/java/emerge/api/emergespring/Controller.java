package emerge.api.emergespring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class Controller {
    @Autowired
    private AlbumRepo albumRepo;

    @Autowired
    private ArtistaRepo artistaRepo;

    @Autowired
    private CancionesRepo cancionesRepo;

    @GetMapping("/artista")
    public List<Artista> artista() {
        return this.artistaRepo.findAll();
    }

    @GetMapping("/album")
    public ResponseEntity<List<AlbumDto>> album() {
        List<AlbumDto> x = this.albumRepo.dameAlbums();
        return new ResponseEntity<>(x, HttpStatus.OK);
    }

    @GetMapping("/albumXArtista/{nombre}")
    public ResponseEntity<List<Album>> albumId(@PathVariable String nombre) {
        List<Album> a = albumRepo.albumId(nombre);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @GetMapping("/dameAlbumXId/{id}")
    public ResponseEntity<Optional<Album>> dameAlbumTitulo(@PathVariable Long id) {
        Optional<Album> x = albumRepo.findById(id);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }

    @GetMapping(value = "/dameImagen/{artista}/{album}/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> dameImagen(@PathVariable String img, @PathVariable String artista,
            @PathVariable String album) throws IOException {
        System.out.println(artista + album + img);
        Resource resource = new ClassPathResource("emerge/" + artista + "/" + album + "/" + img);
        byte[] imagen = Files.readAllBytes(Path.of(resource.getURI()));
        return new ResponseEntity<byte[]>(imagen, HttpStatus.OK);
    }

    @GetMapping("/canciones/{albumId}")
    public ResponseEntity<List<Canciones>> canciones(@PathVariable Long albumId) {
        List<Canciones> x = cancionesRepo.porId(albumId);
        return new ResponseEntity<List<Canciones>>(x, HttpStatus.OK);
    }

    @GetMapping(value = "/dameCancion/{artista}/{album}/{cancion}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> obtenerAudio(
            @PathVariable("artista") String artista,
            @PathVariable("album") String album,
            @PathVariable("cancion") String cancion) throws IOException {
        Resource resource = new ClassPathResource("emerge" + "/" + artista + "/" + album + "/" + cancion + ".mp3");
        byte[] audio = Files.readAllBytes(Path.of(resource.getURI()));
        // return
        // ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(audio);
        return new ResponseEntity<>(audio, HttpStatus.OK);
    }

    @PostMapping("/subir/{artista}/{album}/{fecha}/{descripcion}")
    public ResponseEntity<String> subir(
            @PathVariable String artista,
            @PathVariable String album,
            @PathVariable Date fecha,
            @PathVariable String descripcion,
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("img") MultipartFile img) {

        Artista a=new Artista(artista);
        artistaRepo.save(a);

        Album al=new Album(album, img.getOriginalFilename(), fecha, descripcion, a);
        albumRepo.save(al);

        for (MultipartFile cancion : files) {
            Canciones c=new Canciones(cancion.getOriginalFilename(),al);
            cancionesRepo.save(c);
        }
        
        crearCarpeta(artista);
        crearCarpeta(artista+"/"+album);

        if (files.isEmpty()) {
            return new ResponseEntity<>("Archivos no seleccionados", HttpStatus.BAD_REQUEST);
        }

        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    return new ResponseEntity<>("Uno o más archivos están vacíos", HttpStatus.BAD_REQUEST);
                }

                // Guardar el archivo en el sistema de archivos
                byte[] bytes = file.getBytes();
                Path path = Paths.get("src/main/resources/nuevos/" +artista+"/"+album+"/"+ file.getOriginalFilename());
                Files.write(path, bytes);

                // Guardar el archivo en la base de datos
                // Audio audio = new Audio();
                // audio.setNombre(file.getOriginalFilename());
                // audio.setBytes(bytes);
                // audioRepository.save(audio);
            }
            if (img.isEmpty()) {
                return new ResponseEntity<>("Uno o más archivos están vacíos", HttpStatus.BAD_REQUEST);
            }

            // Guardar el archivo en el sistema de archivos
            byte[] bytes = img.getBytes();
            Path path = Paths.get("src/main/resources/nuevos/" +artista+"/"+album+"/"+ img.getOriginalFilename());
            Files.write(path, bytes);

            return new ResponseEntity<>("Archivos subidos correctamente", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al subir los archivos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void crearCarpeta(String carpeta) {
        try {
            Path path = Paths.get("src/main/resources/nuevos/" + carpeta);
            Files.createDirectory(path);

        } catch (IOException e) {
            // e.printStackTrace();
            // System.out.println("Error al crear la carpeta");
        }
    }

}
