package com.loas.controller;

import java.io.File;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loas.helper.CSVHelper;
import com.loas.helper.UploadFileHelper;
import com.loas.model.Booking;
import com.loas.model.CSVFile;
import com.loas.model.Pager;
import com.loas.model.User;
import com.loas.search.BookingSearch;
import com.loas.service.BookingService;
import com.loas.service.UserService;

@Controller
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookingSearch bookingSearch;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 7, 10 };

	@RequestMapping(value = "/booking", method = RequestMethod.GET)
	public ModelAndView addBooking() {
		ModelAndView modelAndView = new ModelAndView();
		Booking booking = new Booking();
		modelAndView.addObject("booking", booking);
		modelAndView.setViewName("booking");
		return modelAndView;
	}

	@RequestMapping(value = "/addBooking", method = RequestMethod.POST)
	public ModelAndView createNewBooking(@Valid Booking booking, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("booking");
			return modelAndView;
		}
		bookingService.addBooking(booking);
		return new ModelAndView("redirect:/bookingSuccess");
	}

	@RequestMapping(value = { "/bookingSuccess" }, method = RequestMethod.GET)
	public String bookingSuccess() {
		return "/booking/booking_success";
	}

	/**
	 * Handles all requests
	 * 
	 * @param pageSize
	 * @param page
	 * @return model and view
	 */
	@GetMapping("/admin/bookingList")
	public ModelAndView showBookingList(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = new ModelAndView("/booking/booking_list");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("user", "Welcome " + user.getName() + "");

		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Booking> bookings = bookingService
				.findAllPageable(new PageRequest(evalPage, evalPageSize, Direction.ASC, "bookingId"));
		Pager pager = new Pager(bookings.getTotalPages(), bookings.getNumber(), BUTTONS_TO_SHOW);

		modelAndView.addObject("bookings", bookings);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}

	@GetMapping("/admin/booking/{id}/view")
	public ModelAndView view(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		model.addObject("booking", bookingService.findOne(id));
		model.setViewName("/booking/booking_info");
		return model;
	}

	@GetMapping("/admin/booking/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirect, HttpServletRequest request) {
		bookingService.delete(id);
		redirect.addFlashAttribute("success", "Deleted booking successfully!");
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping("/admin/booking/create")
	public String create(Model model) {
		model.addAttribute("booking", new Booking());
		return "/booking/booking_form";
	}

	@GetMapping("/admin/booking/{id}/edit")
	public ModelAndView edit(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		model.addObject("booking", bookingService.findOne(id));
		model.setViewName("/booking/booking_form");
		return model;
	}

	@PostMapping("/admin/booking/save")
	public String save(@Valid Booking booking, BindingResult result, RedirectAttributes redirect,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			return "/booking/booking_form";
		}
		bookingService.save(booking);
		redirect.addFlashAttribute("success", "Saved booking successfully!");
		return "redirect:/admin/bookingList";
	}

	@RequestMapping(value = "/admin/booking/deleteMulti", method = RequestMethod.POST)
	public String deleteMulti(HttpServletRequest request, RedirectAttributes redirect) {
		String[] idStr = request.getParameterValues("bookingid");

		if (idStr != null) {
			for (String string : idStr) {
				int id = Integer.parseInt(string);
				bookingService.delete(id);
			}
			redirect.addFlashAttribute("success", "Delete bookings successfully!");
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		} else {
			redirect.addFlashAttribute("error", "Delete bookings failed!");
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		}
	}

	@RequestMapping(value = "/admin/booking/updateMulti", method = RequestMethod.POST)
	public ModelAndView updateMulti(HttpServletRequest request) {
		String[] id = new String[255];
		String[] lname = new String[255];
		String[] fname = new String[255];
		String[] dob = new String[255];
		String[] gender = new String[255];
		String[] email = new String[255];
		String[] phone = new String[255];
		String[] address = new String[255];
		String[] prefer_date = new String[255];
		String[] prefer_time = new String[255];
		String[] case_cat = new String[255];
		String[] status = new String[255];
		String[] note = new String[255];

		id = request.getParameterValues("id");
		lname = request.getParameterValues("lname");
		fname = request.getParameterValues("fname");
		address = request.getParameterValues("address");
		dob = request.getParameterValues("dob");
		gender = request.getParameterValues("gender");
		phone = request.getParameterValues("phone");
		case_cat = request.getParameterValues("case");
		email = request.getParameterValues("email");
		prefer_date = request.getParameterValues("prefer_date");
		prefer_time = request.getParameterValues("prefer_time");
		status = request.getParameterValues("status");
		note = request.getParameterValues("note");

		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				Booking booking = new Booking();
				int idint = Integer.parseInt(id[i]);
				int caseidint = Integer.parseInt(case_cat[i]);
				booking.setBookingId(idint);
				booking.setLastName(lname[i]);
				booking.setFirstName(fname[i]);
				booking.setAddress(address[i]);
				booking.setDob(dob[i]);
				booking.setGender(gender[i]);
				booking.setPhone(phone[i]);
				booking.setCase_cat_id(caseidint);
				booking.setEmail(email[i]);
				booking.setPrefer_date(prefer_date[i]);
				booking.setPrefer_time(prefer_time[i]);
				booking.setStatus(status[i]);
				booking.setNote(note[i]);
				bookingService.save(booking);
			}
		}
		return new ModelAndView("redirect:/admin/bookingList");
	}

	@RequestMapping(value = "/admin/booking/updateMultiProcess", method = RequestMethod.POST)
	public ModelAndView updateMultiProcess(HttpServletRequest request) {
		List<Booking> bookingsList = new ArrayList<>();
		for (String idStr : request.getParameterValues("bookingid")) {
			int id = Integer.parseInt(idStr);
			Booking student = bookingService.findBookingById(id);
			bookingsList.add(student);
		}
		request.setAttribute("bookingsList", bookingsList);
		return new ModelAndView("/booking/booking-multiUpdate");
	}

	@RequestMapping("/admin/booking/search")
	public String search(String q, Model model) {
		List searchResults = null;
		try {
			searchResults = bookingSearch.search(q);
		} catch (Exception ex) {
			//
		}
		model.addAttribute("bookings", searchResults);
		return "/booking/booking_search";
	}

	@RequestMapping(value = "/admin/booking/import", method = RequestMethod.POST)
	public String upload(@ModelAttribute("csvFile") CSVFile csvFile, ModelMap modelMap, RedirectAttributes redirect,
			HttpServletRequest request) {

		try {
			File file = UploadFileHelper.simpleUpload(csvFile.getFile(), request, true, "img");
			List<Booking> bookings = CSVHelper.read(file.getAbsolutePath());
			for (Booking booking2 : bookings) {
				bookingService.save(booking2);
			}
			redirect.addFlashAttribute("success", "Import bookings successfully!");
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		} catch (Exception e) {
			modelMap.put("csvFile", new CSVFile());
			redirect.addFlashAttribute("error", "Import bookings failed!");
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		}
	}
	
	@RequestMapping(path="/staff/bookingList", method=RequestMethod.GET)
	public String goHome(){
		return "/staff/booking";
	}
	
	@RequestMapping(path="/admin/dashboard", method=RequestMethod.GET)
	public String goDashboard(){
		return "/layout2";
	}

}
