package org.twiliofaces.test.service;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.twiliofaces.cdi.doers.Caller;
import org.twiliofaces.inject.configuration.TwilioAccount;
import org.twiliofaces.inject.configuration.qualifier.TwilioAccountQualifier;

@Singleton
public class TwilioSingletonScheduleCallerWithPrimoAccountTest
{
   Logger logger = Logger.getLogger(getClass().getName());
   @Inject
   Instance<Caller> caller;

   @PostConstruct
   public void start()
   {
      logger.info(" start ");
   }

   @Schedule(hour = "*", minute = "*", second = "*/10", persistent = false, info =
            "alle 4 di notte di ogni primo del mese")
   public void test()
   {
      TwilioAccount twilioAccount = new TwilioAccountQualifier()
      {
         private static final long serialVersionUID = 1L;

         public String accountName()
         {
            return "primo";
         }
      };
      Caller injectedCaller = caller.select(twilioAccount).get();
      logger.info("accountSid: " + injectedCaller.getAccountSid());
      logger.info("authToken: " + injectedCaller.getAuthToken());
      logger.info("number: " + injectedCaller.getFrom());
   }
}
