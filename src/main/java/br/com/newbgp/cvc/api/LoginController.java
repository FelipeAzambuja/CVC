package br.com.newbgp.cvc.api;

import br.com.newbgp.cvc.model.User;
import br.com.newbgp.cvc.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpSession;

//for access from nuxtjs dev mode
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/session")
public class LoginController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	private HttpSession session;
	@Autowired
	EntityManager entityManager;

	@PostMapping("/users")
	List<User> users(@RequestParam String account) {
		return userRepo.findByAccountContains(account);
	}

	@PostMapping(value = "/login")
	ResponseEntity<User> login(@ModelAttribute User user) {
		User loginUser = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (loginUser != null)
			session.setAttribute("user", loginUser);
		return ResponseEntity.ok(loginUser);
	}

	@GetMapping("/logout")
	void logout() {
		session.invalidate();
	}

	@GetMapping("/user")
	ResponseEntity<Object> user(HttpSession session) {
		if (session.getAttribute("user") != null)
			return ResponseEntity.ok(session.getAttribute("user"));
		else
			return ResponseEntity.badRequest().body("");

	}

}
