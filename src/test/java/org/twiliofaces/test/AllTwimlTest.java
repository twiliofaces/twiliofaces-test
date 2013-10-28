package org.twiliofaces.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.twiliofaces.test.common.AbstractTwimlClientTest;
import org.twiliofaces.test.common.TwilioSmsEmulator;

@RunWith(Arquillian.class)
@RunAsClient
public class AllTwimlTest extends AbstractTwimlClientTest
{

   @Before
   public void init()
   {
      setXsdFile("twiml.xsd");

   }

   @Test
   public void testSendSmsToMdb() throws Exception
   {
      TwilioSmsEmulator twilioSmsEmulator = new TwilioSmsEmulator();
      twilioSmsEmulator.setUrl(deploymentUrl + "tunnel").emulateSms("prova");
      Assert.assertEquals(200, twilioSmsEmulator.getStatusCode());
      Assert.assertEquals(smsTwimlResponse, twilioSmsEmulator
               .getEntity().trim());
   }

   @Test
   public void testJsClient() throws Exception
   {
      Map<String, String> callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "1");
      callSidParams.put("name", "fiorenzo");
      ClientResponse<String> response = execute("jsClient.jsf", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // System.out.println(response.getEntity().trim());
   }

   @Test
   public void testSmsPageReceiver() throws Exception
   {
      setTwimlFile("sms-reply.twiml");
      Map<String, String> callSidParams = new HashMap<String, String>();
      callSidParams.put("Body", "ciao come va?");
      callSidParams.put("MessageSid", "123");
      callSidParams.put("NumMedia", "1");
      callSidParams.put("SmsSid", "123");
      callSidParams.put("From", "+392227000");
      callSidParams.put("To", "+3922274111");
      callSidParams.put("AccountSid", "AS1234567890");
      callSidParams.put("AccountSid", "AS1234567890");
      callSidParams.put("MediaContentType0", "image/png");
      callSidParams.put("MediaUrl0", "http://twilio.com/static/image.png");
      ClientResponse<String> response = execute("sms-reply.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      Assert.assertEquals(getTwiml(), response.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
      // System.out.println(response.getEntity().trim());
   }

   @Test
   public void testExtension() throws Exception
   {

      Map<String, String> callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "1");
      ClientResponse<String> response = execute("leaveWithExtension.twiml",
               callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "2");
      response = execute("leaveWithExtension.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "3");
      response = execute("leaveWithExtension.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "4");
      response = execute("leaveWithExtension.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "5");
      response = execute("leaveWithExtension.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "1");
      response = execute("clientWithExtension.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "2");
      response = execute("clientWithExtension.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "3");
      response = execute("clientWithExtension.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "4");
      response = execute("clientWithExtension.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "5");
      response = execute("clientWithExtension.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "1");
      response = execute("statusCallback.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "2");
      response = execute("statusCallback.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "3");
      response = execute("statusCallback.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "4");
      response = execute("statusCallback.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
      // **************************
      callSidParams = new HashMap<String, String>();
      callSidParams.put("CallSid", "5");
      response = execute("statusCallback.twiml", callSidParams);
      Assert.assertEquals(200, response.getStatus());
   }

   @Test
   public void testClient() throws Exception
   {
      setJsfPage("client.twiml");
      setTwimlFile("client.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testConference() throws Exception
   {
      setJsfPage("conference.twiml");
      setTwimlFile("conference.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testDial() throws Exception
   {
      setJsfPage("dial.twiml");
      setTwimlFile("dial.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testEnqueue() throws Exception
   {
      setJsfPage("enqueue.twiml");
      setTwimlFile("enqueue.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testGather() throws Exception
   {
      setJsfPage("gather.twiml");
      setTwimlFile("gather.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testHangup() throws Exception
   {
      setJsfPage("hangup.twiml");
      setTwimlFile("hangup.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testLeave() throws Exception
   {
      setJsfPage("leave.twiml");
      setTwimlFile("leave.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testNumber() throws Exception
   {
      setJsfPage("number.twiml");
      setTwimlFile("number.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testPause() throws Exception
   {
      setJsfPage("pause.twiml");
      setTwimlFile("pause.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testPlay() throws Exception
   {
      setJsfPage("play.twiml");
      setTwimlFile("play.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testQueue() throws Exception
   {
      setJsfPage("queue.twiml");
      setTwimlFile("queue.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testRecord() throws Exception
   {
      setJsfPage("record.twiml");
      setTwimlFile("record.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testRedirect() throws Exception
   {
      setJsfPage("redirect.twiml");
      setTwimlFile("redirect.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testReject() throws Exception
   {
      setJsfPage("reject.twiml");
      setTwimlFile("reject.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testSay() throws Exception
   {
      setJsfPage("say.twiml");
      setTwimlFile("say.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testSip() throws Exception
   {
      setJsfPage("sip.twiml");
      setTwimlFile("sip.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

   @Test
   public void testSms() throws Exception
   {
      setJsfPage("sms.twiml");
      setTwimlFile("sms.twiml");
      ClientResponse<String> result = execute();
      Assert.assertEquals(200, result.getStatus());
      Assert.assertEquals(getTwiml(), result.getEntity().trim());
      Assert.assertTrue(validateAgainstXSD());
   }

}
