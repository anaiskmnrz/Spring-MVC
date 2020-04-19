/**
 * 
 */
package dev.hotel.exceptions;

/** Exception si l'uuid ne correspond pas à un client 
 *
 * @author KOMINIARZ Anaïs
 *
 */
public class ClientException extends RuntimeException{

	public ClientException(String message) {
		super(message);
	}
}

