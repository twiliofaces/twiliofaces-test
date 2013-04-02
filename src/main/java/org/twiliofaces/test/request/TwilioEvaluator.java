package org.twiliofaces.test.request;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.twiliofaces.annotations.From;
import org.twiliofaces.annotations.RecordingUrl;
import org.twiliofaces.annotations.TwilioRequestParams;
import org.twiliofaces.request.TwilioRequestParamsMap;

@Named
@RequestScoped
public class TwilioEvaluator implements Serializable {

	private static final long serialVersionUID = 1L;

	@From
	@Inject
	private String from;

	@Inject
	@RecordingUrl
	private String recordingUrl;

	@Inject
	@TwilioRequestParams
	private TwilioRequestParamsMap twilioRequestParamsMap;

	public TwilioEvaluator() {
	}

	public void init(ComponentSystemEvent event) {
		System.out.println("twilioRequestParamsMap:" + twilioRequestParamsMap);
	}

	public String getFrom() {
		return from;
	}

	public String getRecordingUrl() {
		return recordingUrl;
	}

}