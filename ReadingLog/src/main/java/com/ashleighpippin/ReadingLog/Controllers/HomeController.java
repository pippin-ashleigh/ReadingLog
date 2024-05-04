package com.ashleighpippin.ReadingLog.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.ashleighpippin.ReadingLog.Models.LoginUser;
import com.ashleighpippin.ReadingLog.Models.Reading;
import com.ashleighpippin.ReadingLog.Models.Tag;
import com.ashleighpippin.ReadingLog.Models.User;
import com.ashleighpippin.ReadingLog.Services.ReadingService;
import com.ashleighpippin.ReadingLog.Services.TagService;
import com.ashleighpippin.ReadingLog.Services.UserService;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReadingService readingService;
	
	@Autowired
	TagService tagService;

	@GetMapping("/")
	public String index(@ModelAttribute("loginUser") LoginUser l, @ModelAttribute("registerUser") User u) {
		return "index.jsp";
	}

	@PostMapping("/register")
	public String registerRoute(@Valid @ModelAttribute("registerUser") User u, BindingResult result, Model model,
			HttpSession session) {
		userService.registerUser(u, result);

		if (result.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "index.jsp";
		}
		session.setAttribute("userId", u.getId());
		return "redirect:/dashboard";
	}

	@PostMapping("/login")
	public String loginRoute(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model,
			HttpSession session) {
		User user = userService.login(loginUser, result);
		if (result.hasErrors()) {
			model.addAttribute("registerUser", new User());
			return "index.jsp";
		}
		session.setAttribute("userId", user.getId());
		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("tags", tagService.allTags());
		model.addAttribute("readings", readingService.allReadings());
		return "dashboard.jsp";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/readings/new")
	public String newReadingPage(@ModelAttribute("reading") Reading reading, HttpSession session, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "add_reading.jsp";
	}
	
	@PostMapping("/readings/new")
	public String addReading(@Valid
			@ModelAttribute("reading") Reading reading,
			BindingResult result,
			@RequestParam("listOfTags") String listOfTags,
			RedirectAttributes redirectAttributes) {
	if(result.hasErrors()) {
		return "add_reading.jsp";
	}
	
	System.out.println(listOfTags);
	List<Tag> readingTags = checkTags(listOfTags);
	
	if(readingTags==null || readingTags.size()>10) {
		redirectAttributes.addFlashAttribute("error", "The number of tags must be between 1 and 10");
		return "redirect:/questions/new";
	}
	

	reading.setTags(readingTags);
	readingService.addReading(reading);
	
	return "redirect:/dashboard";
	}
		
	private List<Tag> checkTags(String tags){
		System.out.println("check tags function");
		System.out.println(tags.length());
		if(tags.length()>0) {
			
			ArrayList<Tag> readingTags = new ArrayList<Tag>();
			System.out.println("1");
			String[] tagList = tags.split(",");
			System.out.println(tagList);
			for(String tagString:tagList) {
				Tag tag = tagService.findBySubject(tagString.toLowerCase().strip());
				if(tag==null) {
					tag = new Tag(tagString.toLowerCase().strip());
					System.out.println(tag);
					tagService.addTag(tag);
				}
			readingTags.add(tag);
			System.out.println(readingTags);
			}
			return readingTags;
		}
		return null;
	}
	
	@GetMapping("readings/{id}/edit")
	public String editReadingPage(@PathVariable("id") Long id, HttpSession session, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.getUserById(userId);
		Reading reading = readingService.findByID(id);
		model.addAttribute("reading", reading);
		model.addAttribute("user", user);
		return "edit_reading.jsp";
	}

@PutMapping("/projects/{id}/edit")
public String editReading(@PathVariable("id") Long id, @Valid @ModelAttribute("reading") Reading reading,
		BindingResult result, HttpSession session, Model model) {
	if (session.getAttribute("userId") == null) {
		return "redirect:/";
	}

	if (result.hasErrors()) {
		model.addAttribute("reading", reading);
		return "edit_reading.jsp";
	} else {

		Reading r = readingService.findByID(id);
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.getUserById(userId);
		if (r.getUser().getId().equals(userId)) {
			reading.setId(id);
			reading.setUser(user);
			readingService.updateReading(reading);
		}
		return "redirect:/dashboard";
	}

}

@GetMapping("/readings/{id}")
public String viewReading(@PathVariable("id") Long id, Model model, HttpSession session) {
	Long userId = (Long) session.getAttribute("userId");
	if (userId == null) {
		return "redirect:/";
	}
	User user = userService.getUserById(userId);
	model.addAttribute("user", user);
	model.addAttribute("reading", readingService.findByID(id));
	return "view_reading.jsp";
}

@DeleteMapping("/readings/{id}")
public String deleteReading(@PathVariable("id") Long id, HttpSession session) {
	if (session.getAttribute("userId") == null) {
		return "redirect:/";
	}
	Long userId = (Long) session.getAttribute("userId");
	Reading r = readingService.findByID(id);
//	if (r.getUser().getId() == userId) {
		readingService.deleteReading(id);
//	}
	return "redirect:/dashboard";
}

}

