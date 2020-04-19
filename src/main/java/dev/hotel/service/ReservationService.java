/**
 * 
 */
package dev.hotel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Reservation;
import dev.hotel.exceptions.ChambreException;
import dev.hotel.exceptions.ClientException;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

/** 
 *
 * @author KOMINIARZ Anaïs
 *
 */
@Service
public class ReservationService {

	private ReservationRepository reservationRepository;
    private ClientRepository clientRepository;
    private ChambreRepository chambreRepository;

    public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository, ChambreRepository chambreRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.chambreRepository = chambreRepository;
    }

    @Transactional
    public Reservation creerReservation(LocalDate dateDebut, LocalDate dateFin, UUID clientId, List<UUID> chambreUuids) {

        // TODO Valider l'existence du client
        boolean isClientExist = clientRepository.existsById(clientId);
        if (isClientExist == false) {
        	throw new ClientException("uuid client non trouvé");
        }


        Reservation reservation = new Reservation();

        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);
        
        
        reservation.setUuid(clientId);
        
        List<Chambre> listeChambres = new ArrayList<>();   
        for (UUID uuid : chambreUuids) {
        	Optional<Chambre> chambreFindById = chambreRepository.findById(uuid);
        	Chambre chambreRecup = chambreFindById.orElseThrow(() -> new ChambreException("La chambre " + uuid + " n'existe pas"));
        	
        	listeChambres.add(chambreRecup);
        }
        reservation.setChambres(listeChambres);
        
        
        return reservationRepository.save(reservation);

    }
    
   /* @ExceptionHandler(ClientNotExistException.class)
 	public ResponseEntity<String> onUuidNotFound(ClientNotExistException ex) {
 		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UUID non valide");
 	}*/
}
