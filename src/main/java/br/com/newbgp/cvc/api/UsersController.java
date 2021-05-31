package br.com.newbgp.cvc.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.newbgp.cvc.model.User;
import br.com.newbgp.cvc.repo.UserRepo;

@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    private HttpSession session;
    @Autowired
    HttpServletResponse response;
    @Autowired
    EntityManager entityManager;

    @GetMapping("/account/{account}")
    User account(@PathVariable String account) {
        List<User> findByAccountContains = userRepo.findByAccountContains(account);
        if (findByAccountContains.size() > 0)
            return findByAccountContains.get(0);
        else
            return null;
    }

}