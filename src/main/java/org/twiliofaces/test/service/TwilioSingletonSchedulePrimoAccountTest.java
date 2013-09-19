package org.twiliofaces.test.service;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.twiliofaces.cdi.doers.Caller;
import org.twiliofaces.inject.configuration.TwilioAccount;

@Singleton
public class TwilioSingletonSchedulePrimoAccountTest
{
   Logger logger = Logger.getLogger(getClass().getName());

   @Inject
   @TwilioAccount(accountName = "primo")
   Caller caller;

   @PostConstruct
   public void start()
   {
      logger.info(" start ");
   }

   @Schedule(hour = "*", minute = "*", second = "*/30", persistent = false, info =
            "alle 4 di notte di ogni primo del mese")
   public void test()
   {
      logger.info("accountSid: " + caller.getAccountSid());
      logger.info("authToken: " + caller.getAuthToken());
      logger.info("number: " + caller.getFrom());
   }
}
