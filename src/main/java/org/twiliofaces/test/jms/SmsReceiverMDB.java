/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.test.jms;

import java.util.Enumeration;
import java.util.logging.Logger;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.ejb3.annotation.ResourceAdapter;

@ResourceAdapter("twiliofaces-sms-ra-0.0.1-SNAPSHOT.rar")
@MessageDriven
public class SmsReceiverMDB implements MessageListener
{
   Logger logger = Logger.getLogger(getClass().getName());

   @Override
   public void onMessage(Message message)
   {
      logger.info("we received a new twilio sms message!");

      if (message instanceof MapMessage)
      {
         MapMessage mess = (MapMessage) message;
         try
         {
            Enumeration<?> e = mess.getMapNames();
            while (e.hasMoreElements())
            {
               String key = (String) e.nextElement();
               String value = mess.getString(key);
               logger.info(key + ": " + value);
            }
         }
         catch (JMSException e1)
         {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
      }
   }
}
