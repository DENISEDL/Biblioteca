package com.example.biblioteca;

import com.example.biblioteca.controllers.LibroController;
import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.enums.LibroDisponibilitaEnum;
import com.example.biblioteca.repositories.LibroRepository;
import com.example.biblioteca.services.LibroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibroControllerTest {
    @Autowired
    private LibroController libroController;
    @Autowired
    private LibroService libroService;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoad() {
    }

    // test per creare un nuovo libro
    @Test
    @Order(1)
    void createLibri() throws Exception {
        Libro libro = new Libro();
        libro.setId(3L);
        libro.setTitolo("Opippo");
        libro.setAutore("lillo");
        libro.setAnnoPubblicazione(1813);
        libro.setDisponibilitaEnum(LibroDisponibilitaEnum.DISPONIBILE);

        String libriJSON = objectMapper.writeValueAsString(libro);
        MvcResult result = this.mockMvc.perform(post("/libri/create")
                        .contentType(MediaType.APPLICATION_JSON).content(libriJSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    // test per avere la lista completa dei libri
    @Test
    @Order(3)
    void getAllLibri() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/libri/readAll"))
                .andDo(print()).andReturn();

        List<Libro> userFromResponseList = objectMapper.readValue(result.getResponse().getContentAsString(),
                List.class);
        assertThat(userFromResponseList.size()).isNotZero();
    }

    // test per cercare un libro tramite id
    @Test
    @Order(4)
    void getLibriId() throws Exception {
        Long id = 52L;

        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/libri/readById/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id)).andReturn();
    }

    // test per aggiornare un libro tramite id
    @Test
    @Order(2)
    void updateLibriId() throws Exception {
        Long libriId = 52L;
        Libro updateLibro = new Libro(libriId, "Denise", "De Leo",
                2023, LibroDisponibilitaEnum.PRESTATO);
        String libriJSON = objectMapper.writeValueAsString(updateLibro);

        MvcResult resultUpdate = this.mockMvc.perform(MockMvcRequestBuilders.put("/libri/update/{id}", libriId)
                        .contentType(MediaType.APPLICATION_JSON).content(libriJSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = resultUpdate.getResponse().getContentAsString();
        Assertions.assertNotNull(content);

    }

    // test per eliminare un libro tramite id
    @Test

    @Order(5)
    void deleteLibri() throws Exception {
        Long libriDeleteId = 2L;

        MvcResult result = mockMvc.perform(delete("/libri/delete/{id}", libriDeleteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    // test per verificare se il libro è stato restituito
    @Test
    @Order(6)
    void restitutisciLibri() throws Exception {
        Long returnLibri = 52L;
        MvcResult result = mockMvc.perform(put("/libri/restituisciLibro/{id}", returnLibri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Order(7)
    void prestaLibro() throws Exception {
        Long prestaLibro = 52L;
        MvcResult result = mockMvc.perform(put("/libri/prestaLibro/{id}", prestaLibro)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }
}


