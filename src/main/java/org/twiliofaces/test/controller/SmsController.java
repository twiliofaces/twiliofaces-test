/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.test.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.inject.notification.AccountSid;
import org.twiliofaces.inject.notification.Body;
import org.twiliofaces.inject.notification.From;
import org.twiliofaces.inject.notification.MediaContentTypes;
import org.twiliofaces.inject.notification.MediaUrls;
import org.twiliofaces.inject.notification.MessageSid;
import org.twiliofaces.inject.notification.NumMedia;
import org.twiliofaces.inject.notification.SmsSid;
import org.twiliofaces.inject.notification.To;

@RequestScoped
@Named
public class SmsController
{
   @Inject
   @Body
   String body;

   @Inject
   @MediaContentTypes
   List<String> mediaContentTypes;

   @Inject
   @MediaUrls
   List<String> mediaUrls;

   @Inject
   @MessageSid
   String messageSid;

   @Inject
   @NumMedia
   Integer numMedia;

   @Inject
   @SmsSid
   String smsSid;

   @Inject
   @AccountSid
   String accountSid;

   @Inject
   @From
   String from;

   @Inject
   @To
   String to;

   public SmsController()
   {
   }

   public void evaluate()
   {
      System.out.println("accountSid: " + accountSid);
      System.out.println("messageSid: " + messageSid);
      System.out.println("smsSid: " + smsSid);
      System.out.println("from: " + from);
      System.out.println("to: " + to);
      System.out.println("body: " + body);
      System.out.println("numMedia: " + numMedia);
      if (numMedia > 0)
      {
         for (int i = 0; i < numMedia; i++)
         {
            System.out.println(i + "-  mediaContentType: " + mediaContentTypes.get(i) + " - mediaUrl: "
                     + mediaUrls.get(i));
         }
      }

   }

}
