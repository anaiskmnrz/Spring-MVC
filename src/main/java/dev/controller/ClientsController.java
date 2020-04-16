/**
 * 
 */
package dev.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.repository.ClientRepository;

/** Représentation 
 *
 * @author KOMINIARZ Anaïs
 *
 */
@RestController
@RequestMapping("clients")
public class ClientsController {
	
	private ClientRepository clientRepository;
	
	public ClientsController(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@GetMapping
	public List<Client> listerClients(@RequestParam Integer start, @RequestParam Integer size) {
		return clientRepository.findAll(PageRequest.of(start, size)).toList();
	}

	
}
