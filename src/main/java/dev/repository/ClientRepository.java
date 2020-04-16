/**
 * 
 */
package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Client;

/** Représentation 
 *
 * @author KOMINIARZ Anaïs
 *
 */
public interface ClientRepository extends JpaRepository<Client, Integer>{
	
	

}
