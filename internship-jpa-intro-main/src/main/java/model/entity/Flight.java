package model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name =  "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "origin", nullable = false)
	private String origin;

	@Column(name = "destination", nullable = false)
	private String destination;

	@Column(name = "ariline", nullable = false)
	private String airline;

	@Column(name = "flight_number", nullable = false, unique = false)
	private Integer flight_number;

	@Column(name = "departure_date", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date departure_date;

	@Column(name = "arrival_date", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date arrival_date;

	@Column(name = "status", nullable = false)
	private String status;

	@OneToOne(mappedBy = "flight", cascade = CascadeType.ALL)
	private Booking booking;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public Integer getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(Integer flight_number) {
		this.flight_number = flight_number;
	}

	public Date getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}

	public Date getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
