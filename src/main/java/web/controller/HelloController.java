package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.CarDAO;
import web.models.Car;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
	private final CarDAO carDAO;

//	@Autowired
	public HelloController(CarDAO carDAO) {
		this.carDAO = carDAO;
	}

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "index";
	}

	@GetMapping(value = "/cars")
	public String getCarTable(@RequestParam(value = "count", defaultValue = "5",
			required = false) Integer count, Model model) {

		model.addAttribute("cars", carDAO.getCountCars(count));

		return "cars";
	}


	@GetMapping("/{id}")
	public String showCar(@PathVariable("id") int id, Model model) {
		model.addAttribute("car", carDAO.show(id));
		return "show";
	}

	@GetMapping("/new")
	public String newCar(Model model) {
		model.addAttribute("car", new Car());
		return "new";
	}

	@PostMapping()
	public String create(@ModelAttribute("car") Car car) {
		carDAO.save(car);
		return "redirect:/cars";
	}

	@GetMapping("/{id}/edit")
	public String edit( @PathVariable("id") int id, Model model) {
		model.addAttribute("car", carDAO.show(id));
		return "edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("car") Car car, @PathVariable("id") int id) {
		carDAO.update(id, car);
		return "redirect:/cars";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		carDAO.delete(id);
		return "redirect:/cars";
	}
}























