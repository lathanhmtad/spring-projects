package com.shopme.admin.user;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class UserExceptionController {
	
	// for UI pages
	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundException(UserNotFoundException ex, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", ex.getMessage());
		return "redirect:/users";
	}
}	
