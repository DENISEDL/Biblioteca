package com.example.biblioteca.controllers;

import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libri")
public class LibroController {
    @Autowired
    private LibroService libroService;

    //  CREATE
    @PostMapping("/create")
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){
        Libro libro1 = libroService.addLibri(libro);
        return ResponseEntity.ok().body(libro1);
    }
    // READ ALL
    @GetMapping("/readAll")
    public ResponseEntity<List<Libro>> getAll(){
        return ResponseEntity.ok().body(libroService.getAll());
    }
    // READ BY ID
    @GetMapping("/readById/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id){
        Optional<Libro> libroOptional = libroService.findById(id);
        return ResponseEntity.ok().body(libroOptional.get());
    }
    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro,@PathVariable Long id){
        Optional<Libro> updateLibro = libroService.updateLibro(libro,id);
        if(updateLibro.isPresent()){
            return ResponseEntity.ok().body(updateLibro.get());
        }
        return ResponseEntity.notFound().build();
    }
    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Libro> deleteLibro(@PathVariable Long id){
        Optional<Libro> deleteOptional = libroService.deleteLibro(id);
        if(deleteOptional.isPresent()){
            return ResponseEntity.ok().body(deleteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    // RETURN BOOK
    @PutMapping("/restituisciLibro/{id}")
    public ResponseEntity<Libro> updateStatus(@PathVariable Long id) throws Exception{
        Optional<Libro> libroUpdateStatus = libroService.restituisciLibri(id);
        if(libroUpdateStatus.isPresent()){
            return ResponseEntity.ok().body(libroUpdateStatus.get());
        }
        return ResponseEntity.notFound().build();
    }
    //  LEND BOOK
    @PutMapping("/prestaLibro/{id}")
    public ResponseEntity<Libro> prestaLibro(@PathVariable Long id) throws Exception{
        Optional<Libro> libroReturn = libroService.prestaLibro(id);
        if(libroReturn.isPresent()){
            return ResponseEntity.ok().body(libroReturn.get());
        }
        return ResponseEntity.notFound().build();
    }


}
