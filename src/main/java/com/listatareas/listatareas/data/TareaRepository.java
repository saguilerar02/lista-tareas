package com.listatareas.listatareas.data;
import com.listatareas.listatareas.classes.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea,Integer> {
    

    
}
