package com.loas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loas.model.Pager;
import com.loas.model.Task;
import com.loas.model.User;
import com.loas.service.BookingService;
import com.loas.service.TaskService;
import com.loas.service.UserService;

@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private BookingService bookService;
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private TaskSearch taskSearch;
	
	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = {5, 7, 10};
	
	
	
	
	@RequestMapping(value = {"/createTaskSuccess"}, method = RequestMethod.GET)
	public String createTaskSuccess()
	{
		return "task/createTaskSuccess/";
	}
	
	@GetMapping("/admin/taskList")
	public ModelAndView showTaskList(@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page)
	{
		ModelAndView modelAndView = new ModelAndView("/task/task_list");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("user", "Welcome" + user.getName() + "");
		
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		
		Page<Task> tasks = taskService.findAllPagable(new PageRequest(evalPage, evalPageSize));
		Pager pager = new Pager(tasks.getTotalPages(), tasks.getNumber(), BUTTONS_TO_SHOW);
		
		modelAndView.addObject("tasks", tasks);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", pager);
		
		return modelAndView;
		
	}
	
	@GetMapping("/admin/task/{id}/view")
	public ModelAndView view(@PathVariable int id)
	{
		ModelAndView model = new ModelAndView();
		model.addObject("task", taskService.findOne(id));
		model.setViewName("task/task_info");
		return model;
	}
	
	@GetMapping("/admin/task/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirect, HttpServletRequest request)
	{
		taskService.delete(id);
		redirect.addFlashAttribute("success", "Deleted task successfully !");
		String referer = request.getHeader("Referer");
		System.out.println(referer);
		return "redirect:" + referer;		
	}	
	
	@GetMapping("/admin/task/create")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView();
		Task task = new Task();
		model.addObject("task", task);
		model.addObject("bookings", bookService.getPendingBookings());
		model.setViewName("task/task_add_form");
		return model;
	}
	
	@RequestMapping(value = "/admin/addTask", method = RequestMethod.POST)
	public ModelAndView createNewTask(@Valid Task task, BindingResult bindingResult)
	{
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors())
		{
			modelAndView.setViewName("task_add_form");
			return modelAndView;
		}
		taskService.addTask(task);
		return new ModelAndView("redirect:/createTaskSuccess");
	}
	

	
	@GetMapping("/admin/task/{id}/edit")
	public ModelAndView edit(@PathVariable int id)
	{
		ModelAndView model = new ModelAndView();
		model.addObject("task", taskService.findOne(id));
		model.setViewName("/task/task_form");
		return model;
	}
	
	@PostMapping("/admin/task/save")
	public String save(@Valid Task task, BindingResult result, RedirectAttributes redirect,
			HttpServletRequest request)
	{
		if (result.hasErrors())
		{
			return "task/task_form";
		}
		taskService.save(task);
		redirect.addFlashAttribute("success", "Save task successfully");
		return "redirect:/admin/taskList/";
	}
	
//	@RequestMapping(value = "admin/task/deleteMulti", method = RequestMethod.POST)
//	public String deleteMulti(HttpServletRequest request)
//	{
//		String[] idStr = request.getParameterValues("task_id");
//		
//		if (idStr != null)
//		{
//			for (String string : idStr)
//			{
//				int id = Integer.parseInt(string);
//				taskService.delete(id);
//			}
//			String referer = request.getHeader("Referer");
//			return "redirect:" + referer;
//		}
//		else {
//			return "redirect:/fail";
//		
//		} 
//	}
//	
//	@RequestMapping(value = "admin/task/updateMulti", method = RequestMethod.POST)
//	public ModelAndView updateMulti(HttpServletRequest request)
//	{
//		String[] task_id = new String[255];
//		String[] booking_id = new String[255];
//		String[] booking_prefer_date = new String[255];
//		String[] booking_prefer_time = new String[255];
//		String[] lawyer_id = new String[255];
//		String[] task_note = new String[255];
//		
//		task_id = request.getParameterValues("task_id");
//		booking_id = request.getParameterValues("booking_id");
//		booking_prefer_date = request.getParameterValues("booking_prefer_date");
//		booking_prefer_time = request.getParameterValues("booking_prefer_time");
//		lawyer_id = request.getParameterValues("lawyer_id");
//		task_note = request.getParameterValues("task_note");
//		
//		if (task_id != null)
//		{
//			for (int i = 0; i < task_id.length; i++)
//			{
//				Task task = new Task();
//				int taskIdInt = Integer.parseInt(task_id[i]);
//				int bookingIdInt = Integer.parseInt(booking_id[i]);
//				int lawyerIdInt = Integer.parseInt(lawyer_id[i]);
//				
//				
//				task.setTask_id(taskIdInt);
//				task.setBooking_id(bookingIdInt);
//				task.setPrefered_date(booking_prefer_date[i]);
//				task.setPrefered_time(booking_prefer_time[i]);
//				task.setLawyer_id(lawyerIdInt);
//				task.setTask_note(task_note[i]);				
//			}
//		}
//		
//		return new ModelAndView("redirect:/admin/taskList");		
//	}
//	
//	@RequestMapping(value = "/admin/task/updateMultiProcess", method = RequestMethod.POST)
//	public ModelAndView updateMultiProcess(HttpServletRequest request)
//	{
//		List<Task> taskList = new ArrayList<>();
//		
//		for(String idStr : request.getParameterValues("task_id"))
//		{
//			int id = Integer.parseInt(idStr);
//			Task student = taskService.findTaskById(id);
//			taskList.add(student);
//		}
//		request.setAttribute("taskList", taskList);
//		return new ModelAndView("task/task_multi_update");
//	}
//	
//	@RequestMapping("/admin/task/search")
//	public String search(String q, Model model)
//	{
//		List searchResults = null;
//		searchResults = taskSearch.search(q);
//		model.addAttribute("tasks", searchResults);
//		return "task/taskSearch";
//	}

}
