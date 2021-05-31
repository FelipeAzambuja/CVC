package br.com.newbgp.cvc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Setter
@Getter
@Entity
@Accessors(chain = true)
public class User implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
	Integer privilege;
    String name,password,email;
    Boolean active;
    String account;
}
