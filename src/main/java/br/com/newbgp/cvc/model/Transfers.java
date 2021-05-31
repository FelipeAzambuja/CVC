package br.com.newbgp.cvc.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@ToString
@Getter
@Setter
@Accessors(chain = true)
public class Transfers implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	@ManyToOne
	User origin,destiny;
	
	String type;
	
	Float value,tax;
	
	Integer days;
	@DateTimeFormat(iso = ISO.DATE)
	LocalDate scheduling,date;
}
