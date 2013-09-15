package org.twiliofaces.test.twilioscope;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class LoginController
{

   private String name = "twiliofaces";

   public LoginController()
   {
   }

   public void log()
   {
      System.out.println("TwilioJsClient: " + getClass());
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      System.out.println("new name:" + name);
      this.name = name;
   }
}
