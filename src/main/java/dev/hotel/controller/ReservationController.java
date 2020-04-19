/**
 * 
 */
package dev.hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Reservation;
import dev.hotel.exceptions.ChambreException;
import dev.hotel.exceptions.ClientException;
import dev.hotel.service.ReservationService;

/** 
 *
 * @author KOMINIARZ Anaïs
 *
 */

@RestController
@RequestMapping("reservations")
public class ReservationController {

	private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<?> creerReservation(@RequestBody CreerReservationDto creerReservationDto) {
        try {
        	Reservation saveReservation = reservationService.creerReservation(creerReservationDto.getDateDebut(), creerReservationDto.getDateFin(), 
        			creerReservationDto.getClientId(), creerReservationDto.getChambres());
        	return ResponseEntity.status(HttpStatus.ACCEPTED).header("resultat", "la réservation a été crée").body(saveReservation);
        } catch (ChambreException ex) {
        	return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header(ex.getMessage()).build();
        } catch (ClientException ex) {
        	return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header(ex.getMessage()).build();
        }
    }
}
