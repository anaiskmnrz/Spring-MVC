/**
 * 
 */
package dev.hotel.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/** 
 *
 * @author KOMINIARZ Anaïs
 *
 */
public class CreerReservationDto {
	
	private LocalDate dateDebut;
    private LocalDate dateFin;
    private List<UUID> chambres;
    private UUID clientId;

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<UUID> getChambres() {
        return chambres;
    }

    public void setChambres(List<UUID> chambres) {
        this.chambres = chambres;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}
