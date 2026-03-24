package com.example.usuarioCursos.controller;

import com.example.usuarioCursos.dto.*;
import org.springframework.web.bind.annotation.*;
import com.example.usuarioCursos.service.AlumnoService;

@RestController
public class AlumnoController {

    AlumnoService alumnoService;


    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping("/alumno")
    public AlumnoResponse nuevoAlumno(@RequestBody AlumnoRequest alumnoRequest){
        return alumnoService.crearAlumno(alumnoRequest);
    }

    @PostMapping("/curso")
    public CursoResponse crearCurso(@RequestBody CursoRequest cursoRequest){
        return alumnoService.crearCurso(cursoRequest);
    }

    @PostMapping("/alumno/{idAlumno}")
    public AlumnoResponse agregarCursoAAlumno(@PathVariable Integer idAlumno, @RequestBody AgregarCursoRequest idCurso){

        return alumnoService.agregarCursoAUsuario(idAlumno, idCurso);
    }


    @GetMapping("/test")
    public String test() {
        return "ok";
    }

}
