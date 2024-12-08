package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment extends BaseEntity{

	@Column(name = "payment_method")
	private String paymentMethod;
	
	@Column(name = "transaction_id",length = 20)
	@Length(max = 20)
	private String transactionId;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@OneToOne
	@JoinColumn(name = "booking_id",nullable = false)
	private Booking booking;
}
