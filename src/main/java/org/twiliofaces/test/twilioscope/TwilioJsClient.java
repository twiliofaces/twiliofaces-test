package org.twiliofaces.test.twilioscope;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.twiliofaces.annotations.configuration.TwilioClientToken;

@RequestScoped
@Named
public class TwilioJsClient {

	@TwilioClientToken(client = "client")
	String flowerToken;

	public TwilioJsClient() {
	}

	public void log() {
		System.out.println("TwilioJsClient: " + getClass());
	}

	public String getFlowerToken() {
		return flowerToken;
	}

	public void setFlowerToken(String flowerToken) {
		this.flowerToken = flowerToken;
	}
}
