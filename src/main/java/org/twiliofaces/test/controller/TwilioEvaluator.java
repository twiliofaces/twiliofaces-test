/*
 * Copyright 2013 twiliofaces.org.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.twiliofaces.test.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.cdi.producer.util.TwilioRequestMap;
import org.twiliofaces.inject.notification.From;
import org.twiliofaces.inject.notification.RecordingUrl;
import org.twiliofaces.inject.notification.TwilioRequestParams;

@Named
@RequestScoped
public class TwilioEvaluator implements Serializable
{

   private static final long serialVersionUID = 1L;

   @From
   @Inject
   private String from;

   @Inject
   @RecordingUrl
   private String recordingUrl;

   @Inject
   @TwilioRequestParams
   private TwilioRequestMap twilioRequestParamsMap;

   public TwilioEvaluator()
   {
   }

   public void init(ComponentSystemEvent event)
   {
      System.out.println("twilioRequestParamsMap:" + twilioRequestParamsMap);
   }

   public String getFrom()
   {
      return from;
   }

   public String getRecordingUrl()
   {
      return recordingUrl;
   }

}