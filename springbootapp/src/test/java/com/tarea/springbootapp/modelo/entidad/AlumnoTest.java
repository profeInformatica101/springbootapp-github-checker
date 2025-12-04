package com.tarea.springbootapp.modelo.entidad;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class AlumnoTest {

    @Test
    void testGettersAndSettersExceptId() {
        Alumno alumno = new Alumno();

        alumno.setGithubUser("profeinformatica101");
        alumno.setIpasen("usuario.ipasen");

        assertEquals("profeinformatica101", alumno.getGithubUser());
        assertEquals("usuario.ipasen", alumno.getIpasen());
        
        // id debe ser nulo porque JPA aún no lo generó
        assertNull(alumno.getId(), "El ID debe ser null si no ha sido persistido");
    }

    @Test
    void testAddEntregaMantieneRelacionBidireccional() {
        Alumno alumno = new Alumno();
        alumno.setGithubUser("alumno1");
        alumno.setIpasen("ipasen1");

        EntregaRama entrega = new EntregaRama();
        entrega.setRama("ej01-hola-thymeleaf");
        entrega.setEntregada(true);

        alumno.addEntrega(entrega);

        List<EntregaRama> entregas = alumno.getEntregas();

        assertEquals(1, entregas.size());
        assertSame(entrega, entregas.get(0));
        assertSame(alumno, entrega.getAlumno());
    }

    @Test
    void testRemoveEntregaRompeRelacionBidireccional() {
        Alumno alumno = new Alumno();
        alumno.setGithubUser("alumno2");
        alumno.setIpasen("ipasen2");

        EntregaRama entrega = new EntregaRama();
        entrega.setRama("ej02-tabla-productos");
        entrega.setEntregada(false);

        alumno.addEntrega(entrega);
        assertEquals(1, alumno.getEntregas().size());

        alumno.removeEntrega(entrega);

        assertTrue(alumno.getEntregas().isEmpty());
        assertNull(entrega.getAlumno());
    }
}
