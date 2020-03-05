package com.parameta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parameta.repository.EmpleadoRepository;
import com.parameta.entity.Empleado;

@RestController
@RequestMapping("/api/v1")
public class EmpleadoController {
  @Autowired
  private EmpleadoRepository empleadoRepository;
  /**
   * Traer todos los empleados
   */
  @GetMapping("/empleados")
  public List<Empleado> getAllEmpleados() {
    return empleadoRepository.findAll();
  }
  /**
   * Obtener un empleado por id
   */
  @GetMapping("/empleados/{id}")
  public ResponseEntity<Empleado> getEmpleadosById(@PathVariable(value = "id") Long userId)
      throws Exception {
    Empleado empleado =
        empleadoRepository
            .findById(userId)
            .orElseThrow(() -> new Exception("User not found on :: " + userId));
    return ResponseEntity.ok().body(empleado);
  }
  /**
   * Crear un empleado
   */
  @PostMapping("/saveempleado")
  public Empleado createEmpleado(@Valid @RequestBody Empleado empleado) {
    return empleadoRepository.save(empleado);
  }
  /**
   * Actualizar un empleado por id
   */
  @PutMapping("/empleado/{id}")
  public ResponseEntity<Empleado> updateEmpelado(
      @PathVariable(value = "id") Long empleadoId, @Valid @RequestBody Empleado empleadoDetails)
      throws Exception  {
    Empleado empleado =
        empleadoRepository
            .findById(empleadoId)
            .orElseThrow(() -> new Exception("Empleado not found on :: " + empleadoId));
    
    empleado.setNombres(empleadoDetails.getNombres());
    empleado.setApellidos(empleadoDetails.getApellidos());
    empleado.setEmail(empleadoDetails.getEmail());
    empleado.setTipo_documento(empleadoDetails.getTipo_documento());
    empleado.setNumero_documento(empleadoDetails.getNumero_documento());
    empleado.setFecha_nacimiento(empleadoDetails.getFecha_nacimiento());
    empleado.setFecha_vinculacion(empleadoDetails.getFecha_vinculacion());
    empleado.setCargo(empleadoDetails.getCargo());
    empleado.setSalario(empleadoDetails.getSalario());
    
    final Empleado updatedEmpleado = empleadoRepository.save(empleado);
    return ResponseEntity.ok(updatedEmpleado);
  }
  /**
   * Eliminar un empleado por id
   */
  @DeleteMapping("/empleado/{id}")
  public Map<String, Boolean> deleteEmpleado(@PathVariable(value = "id") Long empleadoId) throws Exception {
    Empleado empleado =
        empleadoRepository
            .findById(empleadoId)
            .orElseThrow(() -> new Exception("Empleado no encontrado :: " + empleadoId));
    empleadoRepository.delete(empleado);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
