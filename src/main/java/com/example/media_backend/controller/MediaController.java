package com.example.media_backend.controller;

import com.example.media_backend.entity.Media;
import com.example.media_backend.repository.MediaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@CrossOrigin(origins = "*")
public class MediaController {

    private final MediaRepository mediaRepository;

    public MediaController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    // LISTAR
    @GetMapping
    public List<Media> getAll() {
        return mediaRepository.findAll();
    }

    // OBTENER POR ID (NECESARIO PARA EDITAR)
    @GetMapping("/{id}")
    public ResponseEntity<Media> getById(@PathVariable Long id) {
        return mediaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREAR
    @PostMapping
    public ResponseEntity<Media> create(@RequestBody Media media) {
        Media saved = mediaRepository.save(media);
        return ResponseEntity.ok(saved);
    }

    // EDITAR (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Media> update(@PathVariable Long id, @RequestBody Media media) {
        return mediaRepository.findById(id)
                .map(existing -> {
                    existing.setTitulo(media.getTitulo());
                    existing.setTipo(media.getTipo());
                    existing.setAnio(media.getAnio());
                    existing.setCalificacion(media.getCalificacion());
                    existing.setEstado(media.getEstado());
                    Media saved = mediaRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!mediaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        mediaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}