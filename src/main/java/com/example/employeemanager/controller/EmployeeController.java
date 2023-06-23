package com.example.employeemanager.controller;


import com.example.employeemanager.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.employeemanager.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {employeeService = theEmployeeService;}

    // add mapping for "list"

    @GetMapping("/list")

    public String listEmployees(Model theModel){

        //get employees in db
        List<Employee> theEmployees = employeeService.findAll();

        //add to spring model
        theModel.addAttribute("employees",theEmployees);

        return "employees/list-employees";
    }

   @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        // create model attribute to bind from data
       Employee theEmployee = new Employee();
       theModel.addAttribute("employee",theEmployee);

       return "employees/employee-form";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){
        //get employee from the service

        Employee theEmployee = employeeService.findById(theId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee",theEmployee);

        //send over to our form
        return "employees/employee-form";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        // save employee
        employeeService.save(theEmployee);

        //tra ve trang list

        return "redirect:/employees/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employeeId")int theId){
        //delete the employee
        employeeService.deleteById(theId);

        // redirect to employees/list
        return "redirect:/employees/list";
    }



}
