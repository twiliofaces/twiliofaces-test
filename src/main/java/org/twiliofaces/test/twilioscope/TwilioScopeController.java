package org.twiliofaces.test.twilioscope;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.annotations.TwilioScope;
import org.twiliofaces.annotations.notification.CallSid;
import org.twiliofaces.extension.TwilioScoped;

@TwilioScope
@Named
public class TwilioScopeController implements TwilioScoped, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@CallSid
	Instance<String> callSid;

	int count = 0;

	public TwilioScopeController() {
		System.out.println("TwilioScopeController: " + new Date() + " - "
				+ getClass());
	}

	@Override
	public String getCallSid() {
		// TODO Auto-generated method stub
		return callSid.get();
	}

	public void log() {
		count++;
		System.out.println("CALL SID: " + getCallSid() + " count" + count);
	}

}
