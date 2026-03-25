package com.example.usuarioCursos.service;

import com.example.usuarioCursos.dto.*;
import com.example.usuarioCursos.entity.Alumno;
import com.example.usuarioCursos.entity.Curso;
import org.apache.catalina.startup.ContextRuleSet;
import org.springframework.stereotype.Service;
import com.example.usuarioCursos.repository.AlumnoRepository;
import com.example.usuarioCursos.repository.CursoRepository;

import java.util.HashSet;
import java.util.Set;

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


    /**
     * AGREGAR CURSO A UN ALUMNO
     * @param idAlumno
     * @param idCurso
     * @return
     */
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

    /**
     * BORRAR ALUMNO DE UN CURSO
     * @param idAlumno
     * @param idCurso
     * @return
     */
    public AlumnoResponse borrarCursoUsuario(Integer idAlumno, Integer idCurso){
        Alumno alumno = new Alumno();
        Curso curso = new Curso();

        //si no existe ese alumno
        alumno = alumnoRepository.findById(idAlumno).orElse(null);
        if (alumno == null) return null;

        //si no existe ese curso
        curso = cursoRepository.findById(idCurso).orElse(null);
        if(curso == null) return null;

        // borrar relacion
        alumno.removeCurso(curso);

        // guardar alumno de nuevo
        alumnoRepository.save(alumno);

        return toResponse(alumno);
    }

    // obtener usuario por id
    public AlumnoResponse obtenerAlumno(Integer id){
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno == null) return null;

        return toResponse(alumno);
    }


    /**
     * OBTENER CURSO POR ID
     * @param id
     * @return
     */
    public CursoResponse obterCurso(Integer id){
        Curso curso = cursoRepository.findById(id).orElse(null);
        if(curso == null) return null;

        return toResponse(curso);
    }


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

        // creamos un Set vacio tipo Integer para almacenar los cursos
        Set<Integer> idsCursos = new HashSet<>();

        // alumno.getCursos() devuelve un Set<Curso>
        // recorremos todos los Cursos de ese set y guardamos el id en el Set<Integer>
        for (Curso c : alumno.getCursos()){
            idsCursos.add(c.getId());
        }

        alumnoResponse.setCursos(idsCursos);
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
