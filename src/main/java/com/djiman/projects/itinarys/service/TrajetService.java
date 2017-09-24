/**
 * 
 */
package com.djiman.projects.itinarys.service;

/**
 * @author gorguindong 
 * Intial version 24/09/2017
 */
public interface TrajetService {
	
	void addTrajet(String begin, String end);

	void editTrajet(String begin, String end);

	void deletTrajet(String begin, String end);
	
}
