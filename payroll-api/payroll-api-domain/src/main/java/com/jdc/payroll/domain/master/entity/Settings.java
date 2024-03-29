package com.jdc.payroll.domain.master.entity;

import java.time.LocalDate;

import com.jdc.payroll.domain.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Settings extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate effectAt;
	private int cutOffDay;
	private int payDay;
}
