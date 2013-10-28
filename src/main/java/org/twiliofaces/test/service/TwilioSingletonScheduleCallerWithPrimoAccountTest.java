/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.test.service;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.twiliofaces.cdi.doers.Caller;
import org.twiliofaces.cdi.extension.TwilioManager;
import org.twiliofaces.cdi.extension.util.Account;

@Singleton
public class TwilioSingletonScheduleCallerWithPrimoAccountTest
{
   Logger logger = Logger.getLogger(getClass().getName());
   @Inject
   TwilioManager twilioManager;

   @PostConstruct
   public void start()
   {
      logger.info(" start ");
   }

   @Schedule(hour = "*", minute = "*", second = "*/10", persistent = false, info =
            "alle 4 di notte di ogni primo del mese")
   public void test()
   {
      Account account = twilioManager.getAccount("primo");
      Caller caller = new Caller(account.getTwilioNumber(), account.getTwilioSid(), account.getTwilioToken());
      logger.info("accountSid: " + caller.getAccountSid());
      logger.info("authToken: " + caller.getAuthToken());
      logger.info("number: " + caller.getFrom());
   }
}
