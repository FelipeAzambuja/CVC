package br.com.newbgp.cvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.DigestUtils;

import br.com.newbgp.cvc.model.TaxConfig;
import br.com.newbgp.cvc.model.User;
import br.com.newbgp.cvc.repo.TaxConfigRepo;
import br.com.newbgp.cvc.repo.TransfersRepo;
import br.com.newbgp.cvc.repo.UserRepo;

@SpringBootApplication
public class CVCApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(CVCApplication.class, args);
		UserRepo userRepo = run.getBean(UserRepo.class);
		TaxConfigRepo taxconfigRepo = run.getBean(TaxConfigRepo.class);
		TransfersRepo transfersRepo = run.getBean(TransfersRepo.class);
		//users----------------------------------------------------------
		User user = new User();
		user.setName("Felipe");
		user.setEmail("felipe@newbgp.com.br");
		user.setPrivilege(9);// adm
		user.setActive(true);
		user.setPassword(DigestUtils.md5DigestAsHex("123".getBytes()));
		user.setAccount("111111");
		userRepo.save(user);
		user = new User();
		user.setName("Paola");
		user.setEmail("paola@newbgp.com.br");
		user.setPrivilege(1);// user
		user.setActive(true);
		user.setPassword(DigestUtils.md5DigestAsHex("123".getBytes()));
		user.setAccount("222222");
		userRepo.save(user);
		//taxConfig----------------------------------------------------------
		TaxConfig taxConfig = new TaxConfig();
		taxConfig.setName("A").setDays_start(0).setDays_end(0).setValue_start(null).setValue_end(null)
				.setMultiply_by_day(false).setTax_value(3.0f).setTax_percent(3.0f);
		taxconfigRepo.save(taxConfig);
		taxConfig = new TaxConfig();
		taxConfig.setName("B").setDays_start(1).setDays_end(10).setValue_start(null).setValue_end(null)
				.setMultiply_by_day(true).setTax_value(12.0f).setTax_percent(0.0f);
		taxconfigRepo.save(taxConfig);
		taxConfig = new TaxConfig();
		taxConfig.setName("C1").setDays_start(11).setDays_end(20).setValue_start(null).setValue_end(null)
				.setMultiply_by_day(false).setTax_value(0.0f).setTax_percent(8.0f);
		taxconfigRepo.save(taxConfig);
		taxConfig = new TaxConfig();
		taxConfig.setName("C2").setDays_start(21).setDays_end(30).setValue_start(null).setValue_end(null)
				.setMultiply_by_day(false).setTax_value(0.0f).setTax_percent(6.0f);
		taxconfigRepo.save(taxConfig);
		taxConfig = new TaxConfig();
		taxConfig.setName("C3").setDays_start(31).setDays_end(40).setValue_start(null).setValue_end(null)
				.setMultiply_by_day(false).setTax_value(0.0f).setTax_percent(4.0f);
		taxconfigRepo.save(taxConfig);
		taxConfig = new TaxConfig();
		taxConfig.setName("C4").setDays_start(41).setDays_end(null).setValue_start(100000.0f).setValue_end(null)
				.setMultiply_by_day(false).setTax_value(0.0f).setTax_percent(2.0f);
		taxconfigRepo.save(taxConfig);
		// ----------------------------------------------------------
		
	}

}
