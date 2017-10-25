package com.loas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.loas.model.LawType;
import com.loas.model.Lawyer;
import com.loas.model.Pager;
import com.loas.model.Task;
import com.loas.model.User;
import com.loas.search.LawyerSearch;
import com.loas.service.LawTypeService;
import com.loas.service.LawyerService;
import com.loas.service.UserService;

@Controller
public class LawyerController {
	@Autowired
	private LawyerService lawyerService;
	
	@Autowired
	private LawTypeService lawTypeService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private LawyerSearch lawyerSearch;

	// @Autowired
	// private LawyerSearch lawyerSearch;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 7, 10 };

	/**
	 * Handles all requests
	 * 
	 * @param pageSize
	 * @param page
	 * @return model and view
	 */
	@GetMapping("/admin/lawyerList")
	public ModelAndView showLawyerList(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = new ModelAndView("/lawyer/lawyer_list");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("user", "Welcome " + user.getName() + "");

		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Lawyer> lawyers = lawyerService.findAllPageable(new PageRequest(evalPage, evalPageSize));
		Pager pager = new Pager(lawyers.getTotalPages(), lawyers.getNumber(), BUTTONS_TO_SHOW);

		modelAndView.addObject("lawyers", lawyers);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}

	@GetMapping("/admin/lawyer/{id}/view")
	public ModelAndView view(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		model.addObject("lawyer", lawyerService.findOne(id));
		model.setViewName("/lawyer/lawyer_info");
		return model;
	}

	@GetMapping("/admin/lawyer/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirect, HttpServletRequest request) {
		lawyerService.delete(id);
		redirect.addFlashAttribute("success", "Deleted lawyer successfully!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	


	@GetMapping("/admin/lawyer/{id}/edit")
	public ModelAndView edit(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		model.addObject("lawyer", lawyerService.findOne(id));
		model.setViewName("/lawyer/lawyer_form");
		return model;
	}

	@GetMapping("/admin/lawyer/create")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView();
		Lawyer lawyer = new Lawyer();
		model.addObject("lawyer", lawyer);
		model.addObject("lawTypes", lawTypeService.getAllLawTypes());
		model.setViewName("/lawyer/lawyer_form");
		return model;
	}
	
	@PostMapping("/admin/addLawyer")
	public String createNewLawyer(@Valid Lawyer lawyer, BindingResult result, RedirectAttributes redirect,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return "/lawyer/lawyer_form";
		}
		lawyerService.save(lawyer);
		redirect.addFlashAttribute("success", "Saved lawyer successfully!");
		return "redirect:/admin/lawyerList";
	}

	@RequestMapping(value = "/admin/lawyer/deleteMulti", method = RequestMethod.POST)
	public String deleteMulti(HttpServletRequest request, RedirectAttributes redirect) {
		String[] idStr = request.getParameterValues("lawyerid");

		if (idStr != null) {
			for (String string : idStr) {
				int id = Integer.parseInt(string);
				lawyerService.delete(id);
			}
			redirect.addFlashAttribute("success", "Delete lawyers successfully!");
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		} else {
			redirect.addFlashAttribute("error", "Delete lawyers failed!");
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		}
	}

	@RequestMapping(value = "/admin/lawyer/updateMulti", method = RequestMethod.POST)
	public ModelAndView updateMulti(HttpServletRequest request) {
		String[] id = new String[255];
		String[] lname = new String[255];
		String[] fname = new String[255];
		String[] dob = new String[255];
		String[] gender = new String[255];
		String[] email = new String[255];
		String[] phone = new String[255];
		String[] address = new String[255];
		String[] join_date = new String[255];
		String[] law_type = new String[255];
		String[] certification = new String[255];
		String[] salary = new String[255];

		id = request.getParameterValues("id");
		lname = request.getParameterValues("lname");
		fname = request.getParameterValues("fname");
		address = request.getParameterValues("address");
		dob = request.getParameterValues("dob");
		gender = request.getParameterValues("gender");
		phone = request.getParameterValues("phone");
		law_type = request.getParameterValues("law_type_id");
		email = request.getParameterValues("email");
		join_date = request.getParameterValues("join_date");
		certification = request.getParameterValues("certification");
		salary = request.getParameterValues("salary_id");

		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				Lawyer lawyer = new Lawyer();
				int idint = Integer.parseInt(id[i]);

				lawyer.setLawyer_id(idint);
				lawyer.setLawyer_lastname(lname[i]);
				lawyer.setLawyer_firstname(fname[i]);
				lawyer.setAddress(address[i]);
				lawyer.setDateofbirth(dob[i]);
				lawyer.setGender(gender[i]);
				lawyer.setPhone(phone[i]);
				lawyer.setLaw_type_id(law_type[i]);
				lawyer.setEmail(email[i]);
				lawyer.setJoin_date(join_date[i]);
				lawyer.setSalary_id(salary[i]);
				lawyer.setCertification(certification[i]);
				lawyerService.save(lawyer);
			}
		}
		return new ModelAndView("redirect:/admin/lawyerList");
	}

	@RequestMapping(value = "/admin/lawyer/updateMultiProcess", method = RequestMethod.POST)
	public ModelAndView updateMultiProcess(HttpServletRequest request) {
		List<Lawyer> lawyersList = new ArrayList<>();
		for (String idStr : request.getParameterValues("lawyerid")) {
			int id = Integer.parseInt(idStr);
			Lawyer lawyer = lawyerService.findOne(id);
			lawyersList.add(lawyer);
		}
		request.setAttribute("lawyersList", lawyersList);
		return new ModelAndView("/lawyer/lawyer-multiUpdate");
	}

	 @RequestMapping("/admin/lawyer/search")
	 public String search(String q, Model model) {
	 List searchResults = null;
	 try {
	 searchResults = lawyerSearch.search(q);
	 }
	 catch (Exception ex) {
	 //
	 }
	 model.addAttribute("lawyers", searchResults);
	 return "/lawyer/lawyer_search";
	 }
	
	@RequestMapping(path="/staff/lawyerList", method=RequestMethod.GET)
	public String goHome(){
		return "/staff/lawyer";
	}

}
