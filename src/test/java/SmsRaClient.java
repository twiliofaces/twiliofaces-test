import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

public class SmsRaClient
{

   private static PostMethod generatePostMethod(String url, int i)
   {
      PostMethod postMethod = new PostMethod(url);
      String smsSid = "smsSid_" + i;
      postMethod.addParameter("SmsSid", smsSid);
      String accountSid = " accountSid_" + i;
      postMethod.addParameter("AccountSid", accountSid);
      String from = "from_" + i;
      postMethod.addParameter("From", from);
      String to = "to_" + i;
      postMethod.addParameter("To", to);
      String body = "body_" + i;
      postMethod.addParameter("Body", body);
      String fromCity = "fromCity_" + i;
      postMethod.addParameter("FromCity", fromCity);
      String fromState = "fromState_" + i;
      postMethod.addParameter("FromState", fromState);
      String fromZip = "fromZip_" + i;
      postMethod.addParameter("FromZip", fromZip);
      String fromCountry = "fromCountry_" + i;
      postMethod.addParameter("FromCountry", fromCountry);
      String toCity = "toCity_" + i;
      postMethod.addParameter("ToCity", toCity);
      String toState = "toState_" + i;
      postMethod.addParameter("ToState", toState);
      String toZip = "toZip_" + i;
      postMethod.addParameter("ToZip", toZip);
      String toCountry = "toCountry_" + i;
      postMethod.addParameter("ToCountry", toCountry);
      return postMethod;
   }

   public static void main(String[] args)
   {
      HttpClient httpClient = new HttpClient();
      String url = "http://jms-twfaces.rhcloud.com/tunnel";
      PostMethod postMethod = null;
      for (int i = 0; i < 10000; i++)
      {
         try
         {
            postMethod = generatePostMethod(url, i);
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

         if (postMethod.getStatusCode() == HttpStatus.SC_OK)
         {
            try
            {
               String resp = postMethod.getResponseBodyAsString();
               System.out.println(resp);
            }
            catch (IOException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
         else
         {
            System.out.println(postMethod.getStatusLine());
         }
      }
   }

}
