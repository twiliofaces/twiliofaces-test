package org.twiliofaces.test.twilioscope;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.inject.context.TwilioScope;
import org.twiliofaces.inject.notification.CallSid;

@TwilioScope
@Named
public class TwilioScopeController implements Serializable
{

   private static final long serialVersionUID = 1L;

   @Inject
   @CallSid
   String callSid;

   int count = 0;

   public TwilioScopeController()
   {
      System.out.println("TwilioScopeController: " + new Date() + " - "
               + getClass());
   }

   public String getCallSid()
   {
      return callSid;
   }

   public void log()
   {
      count++;
      System.out.println("CALL SID: " + getCallSid() + " count: " + count);
   }

}
