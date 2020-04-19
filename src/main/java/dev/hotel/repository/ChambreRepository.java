/**
 * 
 */
package dev.hotel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Chambre;

/**
 *
 * @author KOMINIARZ Anaïs
 *
 */
public interface ChambreRepository extends JpaRepository<Chambre, UUID>{

}
