package com.example.media_backend.controller;

import com.example.media_backend.entity.Media;
import com.example.media_backend.repository.MediaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@CrossOrigin
public class MediaController {

    private final MediaRepository mediaRepository;

    public MediaController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @GetMapping
    public List<Media> obtenerTodos() {
        return mediaRepository.findAll();
    }

    @PostMapping
    public Media crear(@RequestBody Media media) {
        return mediaRepository.save(media);
    }
}
