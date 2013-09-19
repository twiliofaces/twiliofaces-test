package org.twiliofaces.test.twilioscope;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.inject.configuration.TwilioCapabilityToken;


@RequestScoped
@Named
public class TwilioJsClient
{

   @Inject
   @TwilioCapabilityToken(client = "#{loginController.name}")
   String flowerToken;

   private String name = "twiliofaces";

   public TwilioJsClient()
   {
   }

   public void log()
   {
      System.out.println("TwilioJsClient: " + getClass());
   }

   public String getFlowerToken()
   {
      return flowerToken;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }
}
