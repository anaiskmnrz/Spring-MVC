/**
 * 
 */
package dev.hotel.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

/** Représentation 
 *
 * @author KOMINIARZ Anaïs
 *
 */
@WebMvcTest(ClientsController.class)
public class ClientsControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ClientRepository repository;
	
	@Test
	void testListerClients() throws Exception {
		List<Client> listeClients = new ArrayList<>();
		listeClients.add(new Client("Kominiarz", "Anaïs"));
		
		when(repository.findAll(PageRequest.of(0,2))).thenReturn(new PageImpl<Client>(listeClients));
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/clients?start=0&size=2"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Kominiarz"));
	}
	
	@Test
	void testTrouverClient() throws Exception {
		Client client = new Client("Kominiarz","Anaïs");
		
		when(repository.findById(client.getUuid())).thenReturn(Optional.of(client));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/clients/"+client.getUuid()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test 
	void testTrouverClientMauvaisUuid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/clients/f9a18170-9605-4fe6-83c8-d03a53e08bf5"))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
