package org.twiliofaces.test.service;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.twiliofaces.cdi.doers.Sender;

//@Singleton
public class TwilioSingletonScheduleSmSDefaultAccountTest
{
   Logger logger = Logger.getLogger(getClass().getName());
//   @Inject
//   Sender sender;

   @PostConstruct
   public void start()
   {
      logger.info(" start ");
   }

   // @Schedule(hour = "*", minute = "*", second = "*/20", persistent = false, info =
   // "alle 4 di notte di ogni primo del mese")
   public void test()
   {
      // logger.info("accountSid: " + sender.getAccountSid());
      // logger.info("authToken: " + sender.getAuthToken());
      // logger.info("number: " + sender.getFrom());
   }
}
