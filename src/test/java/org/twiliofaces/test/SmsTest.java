package org.twiliofaces.test;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.twiliofaces.cdi.doers.Caller;
import org.twiliofaces.cdi.doers.Sender;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

public class SmsTest
{

   @Inject
   Caller caller;

   @Inject
   Sender sender;

   public void test() throws TwilioRestException
   {
      // params.add(new BasicNameValuePair("Url", "http://demo.twilio.com/docs/voice.xml"));
      // params.add(new BasicNameValuePair("To", "+14155551212"));
      // params.add(new BasicNameValuePair("From", "+14158675309"));
      String sid = caller.endpoint("http://demo.twilio.com/docs/voice.xml").from("+14158675309").to("+14155551212")
               .call();

      // List<NameValuePair> params = new ArrayList<NameValuePair>();
      // params.add(new BasicNameValuePair("Body", "Jenny please?! I love you <3"));
      // params.add(new BasicNameValuePair("To", "+15558675309"));
      // params.add(new BasicNameValuePair("From", "+14158141829"));
      // params.add(new BasicNameValuePair("MediaUrl", "http://www.example.com/hearts.png"));

      String sid2 = sender.from("+14158141829").to("+15558675309").body("Jenny please?! I love you <3")
               .mediaUrl("http://www.example.com/hearts.png").simpleSend();

      // <Response>
      // <Message>Hello, Mobile Monkey</Message>
      // </Response>
   }
}
