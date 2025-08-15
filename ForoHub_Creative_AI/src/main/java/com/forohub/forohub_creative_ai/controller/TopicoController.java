package com.forohub.forohub_creative_ai.controller;

import com.forohub.forohub_creative_ai.domain.topico.Topico;
import com.forohub.forohub_creative_ai.domain.topico.dto.DatosActualizarTopico;
import com.forohub.forohub_creative_ai.domain.topico.dto.DatosListadoTopico;
import com.forohub.forohub_creative_ai.domain.topico.dto.DatosRegistroTopico;
import com.forohub.forohub_creative_ai.domain.topico.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        // Validación para evitar topicos duplicados
        if (topicoRepository.existsByTituloAndMensaje(datos.getTitulo(), datos.getMensaje())) {
            // Devuelve un error si el tppico ya existe
            return ResponseEntity.badRequest().body("Error: Ya existe un tópico con el mismo título y mensaje.");
        }
        // Creamos una nueva entidad Topico para guardar en la base de datos
        Topico topico = new Topico(null, datos.getTitulo(), datos.getMensaje(), LocalDateTime.now(),
                "ACTIVO", datos.getAutor(), datos.getCurso());
        // Guardamos la entidad en la base de datos
        topicoRepository.save(topico);
        return ResponseEntity.ok("Tópico registrado correctamente.");
    }

    @GetMapping
    public Page<DatosListadoTopico> listarTopicos(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {
        // El metodo findAll de JpaRepository ahora acepta un objeto Pageable
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> obtenerTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            DatosListadoTopico datos = new DatosListadoTopico(topico);
            return ResponseEntity.ok(datos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosListadoTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        // Verifica si el tópico existe
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Topico topico = optionalTopico.get();
        // Actualiza los datos del tópico
        topico.actualizarDatos(datos.getTitulo(), datos.getMensaje(), datos.getAutor(), datos.getCurso());
        // Devuelve el tópico actualizado
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        // Verifica si el tópico existe antes de intentar eliminarlo
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            // Devuelve una respuesta 204 No Content para indicar que la operación fue exitosa
            return ResponseEntity.noContent().build();
        } else {
            // Si el tópico no existe, devuelve un 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}
