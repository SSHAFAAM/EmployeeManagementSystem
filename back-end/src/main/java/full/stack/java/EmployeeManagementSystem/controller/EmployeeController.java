package full.stack.java.EmployeeManagementSystem.controller;

import full.stack.java.EmployeeManagementSystem.model.Employee;
import full.stack.java.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest-api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // getting all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAll();
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        return employeeService.getById(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee){
        return employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable long id){
        return employeeService.deleteEmployeeById(id);
    }
}
