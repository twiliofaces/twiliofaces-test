package org.twiliofaces.test.service;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.twiliofaces.request.TwilioSmsSender;

@Singleton
public class TwilioStartupTest
{
   Logger logger = Logger.getLogger(getClass().getName());
   @Inject
   TwilioSmsSender twilioSmsSender;

   @PostConstruct
   public void start()
   {
      logger.info(" start ");
   }

   @Schedule(hour = "*", minute = "*", second = "*/20", persistent = false, info =
            "alle 4 di notte di ogni primo del mese")
   public void test()
   {
      logger.info("accountSid: " + twilioSmsSender.getAccountSid());
   }
}
