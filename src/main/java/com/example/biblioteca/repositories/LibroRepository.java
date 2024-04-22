package com.example.biblioteca.repositories;

import com.example.biblioteca.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query(value = "SELECT * FROM libro WHERE libro.disponibilta_libro_enum = 'DISPONIBILE'",nativeQuery = true)
    List<Libro> findAllLibro();
}
