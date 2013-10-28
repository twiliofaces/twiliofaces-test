/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.test.controller;

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
