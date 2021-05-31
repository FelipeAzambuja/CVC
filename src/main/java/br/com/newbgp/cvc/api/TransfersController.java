package br.com.newbgp.cvc.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.temporal.ChronoUnit;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.newbgp.cvc.model.TaxConfig;
import br.com.newbgp.cvc.model.Transfers;
import br.com.newbgp.cvc.model.User;
import br.com.newbgp.cvc.repo.TransfersRepo;
import br.com.newbgp.cvc.repo.UserRepo;

//for access from nuxtjs dev mode
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/transfers")
public class TransfersController {

	@Autowired
	TransfersRepo transfersRepo;
	@Autowired
	UserRepo userRepo;

	@Autowired
	private HttpSession session;
	@Autowired
	HttpServletResponse response;
	@Autowired
	EntityManager entityManager;

	// criar método para informar a taxa antes de criar ?

	@PostMapping("/new")
	ResponseEntity<Object> newTransfer(@ModelAttribute Transfers transf) {
		LocalDate now = LocalDate.now();
		transf.setOrigin((User) session.getAttribute("user"));
		if (transf.getOrigin() == null)
			return ResponseEntity.badRequest().body("Usuário não está logado");
		if (transf.getOrigin().getAccount().equals(transf.getDestiny().getAccount()))
			return ResponseEntity.badRequest().body("Você não pode transferir para você mesmo");
		if (transf.getScheduling() == null)
			return ResponseEntity.badRequest().body("Inserir a data de agendamento");
		int days = (int) ChronoUnit.DAYS.between(now, transf.getScheduling());
		Query q = entityManager.createQuery(
				"from TaxConfig where ( days_start <= :day and  ( days_end >= :day or days_end is null ) ) and ( value_start = :value or value_start is null )");
		q.setParameter("day", days);
		q.setParameter("value", transf.getValue());
		List<TaxConfig> configs = q.getResultList();
		if (configs.size() > 0) {
			TaxConfig taxConfig = configs.get(0);
			Float value = transf.getValue();
			Float tax_value = 0f;
			if (taxConfig.getTax_percent() > 0) // null safe ?
				tax_value = value * (taxConfig.getTax_percent() / 100);
			if (taxConfig.getTax_value() > 0)
				tax_value += taxConfig.getTax_value();
			if (taxConfig.getMultiply_by_day())
				tax_value += tax_value * days;

			transf.setTax(tax_value);
			transf.setDays(days);
			transf.setTax(tax_value);
			transf.setDate(now);
			transf.setType(taxConfig.getName());
			Transfers saved = (Transfers) transfersRepo.save(transf);
			return ResponseEntity.ok(saved);
		} else {
			return ResponseEntity.badRequest().body("Configuração não encontrada");
		}

	}

	@GetMapping("/my")
	List<Transfers> my_transfers() {
		User user = (User) session.getAttribute("user");
		var q =  this.entityManager.createQuery("FROM Transfers where origin = :origin or destiny = :destiny");
		q.setParameter("origin", user);
		q.setParameter("destiny", user);
		return q.getResultList();
	}

}
