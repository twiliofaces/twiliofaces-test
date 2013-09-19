package org.twiliofaces.test.common;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class TwilioSmsEmulator
{

   private PostMethod postMethod;
   private String url;
   private HttpClient httpClient;

   public TwilioSmsEmulator()
   {
   }

   public TwilioSmsEmulator(String url)
   {
      this.url = url;
      httpClient = new HttpClient();
      postMethod = new PostMethod(url);
   }

   public int getStatusCode()
   {
      return postMethod.getStatusCode();
   }

   public String getEntity()
   {
      try
      {
         return postMethod.getResponseBodyAsString();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      return "";
   }

   public TwilioSmsEmulator post()
   {
      try
      {
         httpClient.executeMethod(postMethod);
      }
      catch (HttpException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      return this;
   }

   public TwilioSmsEmulator emulateSms(String msg)
   {
      generatePostMethod(getUrl(), ((Double) Math.random()).intValue(), msg);
      return post();
   }

   public void addParameter(String key, String value)
   {
      postMethod.addParameter(key, value);
   }

   public TwilioSmsEmulator parameter(String key, String value)
   {
      addParameter(key, value);
      return this;
   }

   private void generatePostMethod(String url, int i, String msg)
   {
      parameter("SmsSid", "smsSid_" + i);
      parameter("AccountSid", " accountSid_" + i);
      parameter("From", "from_" + i);
      parameter("To", "to_" + i);
      parameter("Body", msg);
      parameter("FromCity", "fromCity_" + i);
      parameter("FromState", "fromState_" + i);
      parameter("FromZip", "fromZip_" + i);
      parameter("FromCountry", "fromCountry_" + i);
      parameter("ToCity", "toCity_" + i);
      parameter("ToState", "toState_" + i);
      parameter("ToZip", "toZip_" + i);
      parameter("ToCountry", "toCountry_" + i);
   }

   public PostMethod getResponse()
   {
      return postMethod;
   }

   public String getUrl()
   {
      return url;
   }

   public TwilioSmsEmulator setUrl(String url)
   {
      this.url = url;
      httpClient = new HttpClient();
      postMethod = new PostMethod(url);
      return this;
   }

}
