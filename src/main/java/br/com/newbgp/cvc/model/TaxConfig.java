package br.com.newbgp.cvc.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@ToString
@Getter
@Setter
@Accessors(chain = true)
public class TaxConfig implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String name;
	Integer days_start,days_end;
	Float value_start,value_end,tax_value,tax_percent;
	Boolean multiply_by_day;	
}
