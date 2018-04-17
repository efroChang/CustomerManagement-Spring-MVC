package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.entity.Customer;
import com.spring.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService; // Dependency Injection

	// ----------------------------------------
	// GET: Display All Customers
	// ----------------------------------------
	@GetMapping("/list")
	public String listCustomers(Model model) {

		List<Customer> customers = customerService.getCustomers();
		model.addAttribute("customers", customers);

		return "customer-list";
	}

	// ----------------------------------------
	// GET: Show Add Customer Form
	// ----------------------------------------
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		// Create a new Customer object to bind with the JSP
		Customer newCustomer = new Customer();
		model.addAttribute("customer", newCustomer);

		return "customer-form";
	}

	// ----------------------------------------
	// POST: Save Customer
	// ----------------------------------------
	@PostMapping("/saveCustomer")
	public String saveCustomerForm(@ModelAttribute("customer") Customer theCustomer, BindingResult bindingResult) {

		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list"; // [KEY]: "redirect:"
	}

	// ----------------------------------------
	// GET: Show Update Customer Form
	// ----------------------------------------
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model) {

		Customer foundCustomer = customerService.getCustomer(customerId);
		model.addAttribute("customer", foundCustomer);

		return "customer-form";
	}

	// ----------------------------------------
	// GET: Delete a Customer
	// ----------------------------------------
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId) {

		customerService.deleteCustomer(customerId);

		return "redirect:/customer/list";
	}

	// ----------------------------------------
	// GET: Search for Customers
	// ----------------------------------------
	@GetMapping("/search")
	public String search(@RequestParam("searchCriteria") String criteria, Model model) {

		List<Customer> customers = customerService.searchCustomers(criteria);
		model.addAttribute("customers", customers);

		return "customer-list";
	}
}
