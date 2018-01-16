package com.example.demo.controllers;

import com.example.demo.models.Employees;
import com.example.demo.models.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    EmployeesRepo employeesRepo;

    @RequestMapping("/")
    public ModelAndView doHome() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("lists", employeesRepo.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView doSave(@RequestParam("id") String id, @RequestParam("name") String name,
                               @RequestParam("salary") Double salary) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        Employees employees;
        if (!id.isEmpty()) {
            employees = (Employees)employeesRepo.findOne(Integer.parseInt(id));
        }
        else {
            employees = new Employees();
        }
        employees.setName(name);
        employees.setSalary(salary);
        employeesRepo.save(employees);
        return modelAndView;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView doView(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("lists", employeesRepo.findOne(id));
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView doDelete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        employeesRepo.delete(id);
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView doUpdate(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("lists", employeesRepo.findOne(id));
        return modelAndView;
    }

}
