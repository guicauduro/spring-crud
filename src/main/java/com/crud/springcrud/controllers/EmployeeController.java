package com.crud.springcrud.controllers;

import com.crud.springcrud.dtos.EmployeeRecordDto;
import com.crud.springcrud.models.EmployeeModel;
import com.crud.springcrud.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public ResponseEntity<EmployeeModel> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        var employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employeeModel));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        List<EmployeeModel> employeeList = employeeRepository.findAll();
        if(!employeeList.isEmpty()){
            for(EmployeeModel employee : employeeList){
                UUID id = employee.getId();
                employee.add(linkTo(methodOn(EmployeeController.class).getOneEmployee(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeList);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getOneEmployee(@PathVariable(value = "id")UUID id){
        Optional<EmployeeModel> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
        }
        employee.get().add(linkTo(methodOn(EmployeeController.class).getAllEmployees()).withRel("Employees List"));
        return ResponseEntity.status(HttpStatus.OK).body(employee.get());
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable(value = "id") UUID id, @RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        Optional<EmployeeModel> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
        }
        var employeeModel = employee.get();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel);
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.save(employeeModel));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") UUID id) {
        Optional<EmployeeModel> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
        }
        employeeRepository.delete(employee.get());
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully.");
    }

}
