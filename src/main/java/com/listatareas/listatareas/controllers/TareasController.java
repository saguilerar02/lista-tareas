package com.listatareas.listatareas.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.listatareas.listatareas.classes.Tarea;
import com.listatareas.listatareas.data.TareaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
public class TareasController {
    
    @Autowired
    private TareaRepository tareaRepo;

    @GetMapping(value="/tareas")
    public ResponseEntity<Object> getAllTareas() {

        List<Tarea> tareas = this.tareaRepo.findAll();

        if(tareas.size()>0){
            return ResponseEntity.ok(tareas);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Aún no has añadido tareas...");
        }
    }

    @PostMapping(value="/nueva-tarea")
    public ResponseEntity<Object> postMethodName(@RequestBody Tarea entity) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Tarea tarea = this.tareaRepo.save(entity);

        return ResponseEntity.status(status).body(tarea);
    }
    
    @PutMapping(value="/tareas/{id}")
    public ResponseEntity<Object> putMethodName(@PathVariable Integer id, @RequestBody Tarea entity) {

        Tarea updated = null;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Optional<Tarea> tarea = this.tareaRepo.findById(id);
        
        if(tarea.isPresent()){
            updated = tarea.get();
            updated.setTitulo(entity.getTitulo() == null? updated.getTitulo() : entity.getTitulo() );
            updated.setDescripcion(entity.getDescripcion()  == null? updated.getDescripcion() : entity.getDescripcion());
            updated.setActivo(entity.getActive()  == null? updated.getActive() : entity.getActive());
            this.tareaRepo.save(updated);
            status = HttpStatus.CREATED;
        }else{
            status = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(status).body(updated);
    }

    @DeleteMapping(value="/tareas/{id}")
    public ResponseEntity<Map<String,Object>> requestMethodName(@PathVariable Integer id) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String,Object> response = new HashMap<String,Object>();
        response.put("message", "No se pudo eliminar la tarea especificada");
        response.put("deleted", null);

        Optional<Tarea> tarea = this.tareaRepo.findById(id);

        if(tarea.isPresent()){
            this.tareaRepo.delete(tarea.get());
            response.put("message", "La tarea especificada se ha eliminado con éxito");
            response.put("deleted", tarea);
            
        }
        
        return ResponseEntity.status(status).body(response);
    }
    
}
