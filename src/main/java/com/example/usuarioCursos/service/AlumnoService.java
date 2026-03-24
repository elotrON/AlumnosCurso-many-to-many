package com.example.usuarioCursos.service;

import com.example.usuarioCursos.dto.*;
import com.example.usuarioCursos.entity.Alumno;
import com.example.usuarioCursos.entity.Curso;
import org.apache.catalina.startup.ContextRuleSet;
import org.springframework.stereotype.Service;
import com.example.usuarioCursos.repository.AlumnoRepository;
import com.example.usuarioCursos.repository.CursoRepository;

@Service
public class AlumnoService {

    AlumnoRepository alumnoRepository;
    CursoRepository cursoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository, CursoRepository cursoRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
    }

    /**
     * CREA NUEVO USUARIO
     * @param alumnoRequest
     * @return
     */
    public AlumnoResponse crearAlumno(AlumnoRequest alumnoRequest){
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoRequest.getNombre());
        alumno.setApellidos(alumnoRequest.getApellidos());
        alumno.setDni(alumnoRequest.getDni());
        alumno.setDireccion(alumnoRequest.getDireccion());
        alumno.setTelefono(alumnoRequest.getTelefono());

        alumnoRepository.save(alumno);
        return toResponse(alumno);
    }


    /**
     * CREA UN NUEVO CURSO
     * @param cursoRequest
     * @return
     */
    public CursoResponse crearCurso(CursoRequest cursoRequest){
        Curso curso = new Curso();
        curso.setNombre(cursoRequest.getNombre());
        curso.setDuracion(cursoRequest.getDuracion());
        curso.setHabilidades(cursoRequest.getHabilidades());

        cursoRepository.save(curso);

        return toResponse(curso);

    }


    // añadir curso a usuario
    public AlumnoResponse agregarCursoAUsuario(Integer idAlumno, AgregarCursoRequest idCurso){
        Alumno alumno;
        Curso curso;

        alumno = alumnoRepository.findById(idAlumno).orElse(null);
        if(alumno == null) return null;

        curso = cursoRepository.findById(idCurso.getIdCurso()).orElse(null);
        if(curso == null) return null;

        alumno.addCurso(curso);
        alumnoRepository.save(alumno);
        return toResponse(alumno);
    }

    // quitar curso de usuario


    // obtener usuario por id


    // obtener curso por id


    // listar usuarios


    // listar cursos

    /**
     * CONVIERTE Alumno --> AlumnoResponse
     * @param alumno
     * @return
     */
    private AlumnoResponse toResponse(Alumno alumno){
        AlumnoResponse alumnoResponse = new AlumnoResponse();

        alumnoResponse.setId(alumno.getId());
        alumnoResponse.setNombre(alumno.getNombre());
        alumnoResponse.setApellidos(alumno.getApellidos());
        alumnoResponse.setDni(alumno.getDni());
        alumnoResponse.setDireccion(alumno.getDireccion());
        alumnoResponse.setTelefono(alumno.getTelefono());

        return alumnoResponse;
    }

    private CursoResponse toResponse(Curso curso){
        CursoResponse cursoResponse = new CursoResponse();

        cursoResponse.setId(curso.getId());
        cursoResponse.setNombre(curso.getNombre());
        cursoResponse.setDuracion(curso.getDuracion());
        cursoResponse.setHabilidades((curso.getHabilidades()));
        return cursoResponse;
    }

}
