package net.springboot.javaguids.controll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.springboot.javaguids.models.Employee;
import net.springboot.javaguids.services.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService services;
	
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return getPageable(1,model,"name","asc");
		
	}
	
	@GetMapping("/showNewEmployee")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
         Employee employee = new Employee();
        model.addAttribute("employe", employee);
        return "new_employee";
    }
	
	@PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employe") Employee employee) {
        // save employee to database
        services.savedEmployee(employee);
        return "redirect:/";
    }
	
	@GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Employee employee = services.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method 
        this.services.deleteEmployeeById(id);
        return "redirect:/";
    }
    
    // page/1?sortField=Age&sortDirec=ASC
    @GetMapping("/page/{pageNo}")
    public String getPageable(@PathVariable int pageNo,Model model,
    		@RequestParam("sortField") String field,
    		@RequestParam("sortDirec") String direc
    		
    		) {
    	int size=5;
    	Page<Employee> page =services.findPaginated(pageNo, size,field,direc);
    	List<Employee> listEmployees=page.getContent();
    	
    	model.addAttribute("currentPage",pageNo);
    	model.addAttribute("totalPgaes",page.getTotalPages());
    	model.addAttribute("totalItems",page.getTotalElements());
    	model.addAttribute("listofemployes",listEmployees);
    	model.addAttribute("sortField",field);
    	model.addAttribute("sortDirec",direc);
    	model.addAttribute("reverseSortDir",direc.equals("asc")?"desc":"asc");
    	return "index";
    	
    }
	
	
	
	
	
	
	
	

}
