package com.forohub.forohub_creative_ai.domain.topico.repository;

import com.forohub.forohub_creative_ai.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Consulta para verificar si ya existe un tópico con el mismo título y mensaje
    // Se usa @Query para hacer una consulta especidfica a la base de datos
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje")
    boolean existsByTituloAndMensaje(@Param("titulo") String titulo, @Param("mensaje") String mensaje);
}
