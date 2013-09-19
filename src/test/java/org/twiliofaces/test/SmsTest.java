package org.twiliofaces.test;

import org.twiliofaces.cdi.doers.simple.SimpleSender;

import com.twilio.sdk.TwilioRestException;

public class SmsTest
{
   public static void main(String[] args) throws TwilioRestException
   {
      // AC2999a90e54ebe0bdab34fab15f241040</param-value>
      // </context-param>
      //
      //
      // <context-param>
      // <param-name>TWILIO_TOKEN</param-name>
      // <param-value>e64ab5aad9596459bed149a29fcb22fa</param-value>
      // </context-param>
      //
      // <context-param>
      // <param-name>TWILIO_NUMBER</param-name>
      // <param-value>390909100010
      SimpleSender sender = new SimpleSender("390909100010", "AC2999a90e54ebe0bdab34fab15f241040",
               "e64ab5aad9596459bed149a29fcb22fa");
      sender.setTo("393922274929").setBody("ciao").send();
   }
}
