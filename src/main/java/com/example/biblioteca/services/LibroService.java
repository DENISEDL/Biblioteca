package com.example.biblioteca.services;

import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.enums.LibroDisponibilitaEnum;
import com.example.biblioteca.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public Libro addLibri(Libro libroAggiunto){
        return libroRepository.save(libroAggiunto);
    }
    public List<Libro> getAll(){
        return libroRepository.findAll();
    }
    public Optional<Libro> findById(Long id){
        Optional<Libro> libroById = libroRepository.findById(id);
        return libroById;
    }
    public Optional<Libro> updateLibro(Libro libroUpdate,Long id){
        Optional<Libro> updateById = libroRepository.findById(id);
        if(updateById.isPresent()){
            updateById.get().setAutore(libroUpdate.getAutore());
            updateById.get().setAnnoPubblicazione(libroUpdate.getAnnoPubblicazione());
            updateById.get().setTitolo(libroUpdate.getTitolo());
            updateById.get().setDisponibilitaEnum(libroUpdate.getDisponibilitaEnum());
            Libro libri = libroRepository.save(updateById.get());
            return Optional.of(libri);
        }else{
             return Optional.empty();
        }
    }
    public Optional<Libro> deleteLibro(Long id){
        Optional<Libro> deleteLibro = libroRepository.findById(id);
        if(deleteLibro.isPresent()){
            libroRepository.delete(deleteLibro.get());
        }else{
            return Optional.empty();
        }
        return deleteLibro;
    }
    public Optional<Libro> prestaLibro(Long id) throws Exception{
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        if (optionalLibro.isPresent()) {
            Libro libro = optionalLibro.get();
            if (libro.getDisponibilitaEnum() == LibroDisponibilitaEnum.DISPONIBILE) {
                libro.setDisponibilitaEnum(LibroDisponibilitaEnum.PRESTATO);
                libroRepository.save(libro);
                return Optional.of(libro);
            } else {
                throw new Exception("Libro non disponibile");
            }
        } else {
            throw new Exception("Libro non trovato");
        }
    }
    public Optional<Libro> restituisciLibri(Long id) throws Exception{
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        if (optionalLibro.isPresent()) {
            Libro libro = optionalLibro.get();
            if (libro.getDisponibilitaEnum() == LibroDisponibilitaEnum.PRESTATO) {
                libro.setDisponibilitaEnum(LibroDisponibilitaEnum.DISPONIBILE);
                libroRepository.save(libro);
                return Optional.of(libro);
            } else {
                throw new Exception("Libro non prestato");
            }
        }
         else {
            throw new Exception("Libro non trovato");
        }
    }




}

