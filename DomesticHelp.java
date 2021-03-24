package com.cg.aps.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author Harsh
 * domestic help entity class, helpId is auto generated
 *
 */
@Entity
@Table(name = "domestic_help_aps")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DomesticHelp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer helpId;
	private String personName;
	private String helpType;
	private String arrivalTime;
	private String departureTime;
	private LocalDate helpDate;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "guardId"),name = "guardId")
	@JsonIgnore
	private Guard guard;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "ownerId"),name = "ownerId")
	@JsonIgnore
	private Owner owner;
}
