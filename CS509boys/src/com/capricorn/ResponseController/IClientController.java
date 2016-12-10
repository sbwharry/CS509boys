package com.capricorn.ResponseController;

import xml.Message;
/** Form an IClientController interface 
 * @author Chen Li, Tianyu Wu, Bowen Sun
 */
public interface IClientController {
	
	/**
	 * Return TRUE if accept the response; false otherwise.
	 * 
	 * If unable to process a valid response, then must thrown a RuntimeException 
	 */
	public boolean process(Message response);
}
