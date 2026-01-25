package com.example.media.controller;

import com.example.media.entity.Media;
import com.example.media.repository.MediaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@CrossOrigin
public class MediaController {

    private final MediaRepository repository;

    public MediaController(MediaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Media> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Media crear(@RequestBody Media media) {
        return repository.save(media);
    }
}
