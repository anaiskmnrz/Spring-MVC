/**
 * 
 */
package dev.hotel.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

/** Représentation 
 *
 * @author KOMINIARZ Anaïs
 *
 */
@RestController
@RequestMapping("clients")
public class ClientsController {
	

	private ClientRepository clientRepository;
	
	/** Constructeur
	 *
	 * @param clientRepository
	 */
	public ClientsController(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}
	
	
	@GetMapping
	public List<Client> listerClients(@RequestParam Integer start, @RequestParam Integer size) {
		return clientRepository.findAll(PageRequest.of(start, size)).toList();
	}
	
	@GetMapping("{uuid}")
	public ResponseEntity<?> trouverClient(@PathVariable UUID uuid) {
		
		Optional<Client> clientOpt = clientRepository.findById(uuid);
		
		if (clientOpt.isPresent()) {
			return ResponseEntity.ok(clientOpt.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("uuid non valide");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> ajouterClient(@RequestBody Client client) {
		
		if (client.getNom().length() < 2 || client.getPrenoms().length() < 2) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("resultat", "les noms et "
					+ "prénoms doivent contenir au moins 2 caractères.").build();
		} else {
			Client save = clientRepository.save(client);
			return ResponseEntity.status(HttpStatus.ACCEPTED).header("resultat", "Le client a été crée").body(save);
		}
		
	
	}
	
}
