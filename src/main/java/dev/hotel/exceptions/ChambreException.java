/**
 * 
 */
package dev.hotel.exceptions;

/** Exception si l'uuid ne correspond pas à une chambre
 *
 * @author KOMINIARZ Anaïs
 *
 */
public class ChambreException extends RuntimeException{
	
	public ChambreException(String message) {
		super(message);
	}

}
