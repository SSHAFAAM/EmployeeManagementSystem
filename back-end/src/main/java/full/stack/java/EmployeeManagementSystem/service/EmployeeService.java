package full.stack.java.EmployeeManagementSystem.service;

import full.stack.java.EmployeeManagementSystem.exception.ResourceNotFoundException;
import full.stack.java.EmployeeManagementSystem.model.Employee;
import full.stack.java.EmployeeManagementSystem.repositroy.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee save(Employee emp){
        return employeeRepository.save(emp);
    }

    public ResponseEntity<Employee> getById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id : "+id));
        return ResponseEntity.ok(employee);
    }

    public ResponseEntity<Employee> updateEmployeeById(long id, Employee emp){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found wiht id : "+id));

        employee.setFirstName(emp.getFirstName());
        employee.setLastName(emp.getLastName());
        employee.setEmailId(emp.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    public ResponseEntity<Map<String, Boolean>> deleteEmployeeById(long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+id));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
