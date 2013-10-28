/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.test.service;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

//@Singleton
public class TwilioSingletonScheduleSmSDefaultAccountTest
{
   Logger logger = Logger.getLogger(getClass().getName());

   // @Inject
   // Sender sender;

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
