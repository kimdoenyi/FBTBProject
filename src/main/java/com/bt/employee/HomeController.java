package com.bt.employee;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bt.employee.controller.EmployeeController;

@Controller
public class HomeController {
	
	
	@Autowired
	private EmployeeController employeeController;
	
	@RequestMapping("/")
	public String home(HttpSession session) {
		
		return employeeController.loginPage(session);
	}
	
}
