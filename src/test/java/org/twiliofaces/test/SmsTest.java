package org.twiliofaces.test;

import org.twiliofaces.cdi.doers.simple.SimpleSender;

import com.twilio.sdk.TwilioRestException;

public class SmsTest
{
   public static void main(String[] args) throws TwilioRestException
   {
      SimpleSender sender = new SimpleSender("xxxx", "ACxxxxxx",
               "xxxxx");
      sender.setTo("xxxx").setBody("xxx").send();
   }
}
