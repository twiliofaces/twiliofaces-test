package org.twiliofaces.test.common;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.twiliofaces.test.request.TwilioEvaluator;

public abstract class AbstractTwimlClientTest {

	@ArquillianResource
	URL deploymentUrl;

	static String TWIML_FOLDER = "src/test/resources/twiml/";

	private String jsfPage;
	private String twimlFile;
	private Map<String, String> parameters;

	@Deployment
	public static Archive<?> createDeployment() {

		MavenDependencyResolver resolver = DependencyResolvers.use(
				MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
		WebArchive jar = ShrinkWrap
				.create(WebArchive.class, "twilio.war")
				.addClass(AbstractTwimlClientTest.class)
				.addPackage(TwilioEvaluator.class.getPackage().getName())
				.addAsLibraries(
						resolver.artifact("org.twiliofaces:twiliofaces")
								.resolveAsFiles())
				.addAsLibraries(
						resolver.artifact("org.jboss.resteasy:resteasy-jaxrs")
								.resolveAsFiles())
				.addAsLibraries(
						resolver.artifact("com.twilio.sdk:twilio-java-sdk")
								.resolveAsFiles())
				.addAsWebResource("pages/client.xhtml")
				.addAsWebResource("pages/client.xhtml")
				.addAsWebResource("pages/conference.xhtml")
				.addAsWebResource("pages/dial.xhtml")
				.addAsWebResource("pages/enqueue.xhtml")
				.addAsWebResource("pages/gather.xhtml")
				.addAsWebResource("pages/hangup.xhtml")
				.addAsWebResource("pages/leave.xhtml")
				.addAsWebResource("pages/number.xhtml")
				.addAsWebResource("pages/pause.xhtml")
				.addAsWebResource("pages/play.xhtml")
				.addAsWebResource("pages/queue.xhtml")
				.addAsWebResource("pages/record.xhtml")
				.addAsWebResource("pages/redirect.xhtml")
				.addAsWebResource("pages/reject.xhtml")
				.addAsWebResource("pages/say.xhtml")
				.addAsWebResource("pages/sip.xhtml")
				.addAsWebResource("pages/sms.xhtml")
				.addAsWebInfResource("common/faces-config.xml",
						"faces-config.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		return jar;
	}

	protected String getTwiml() throws Exception {
		String content = null;
		File file = new File(TWIML_FOLDER + getTwimlFile());
		try {
			FileReader reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content.trim();
	}

	protected ClientResponse<String> execute() throws Exception {
		ClientRequest clientRequest = new ClientRequest(
				deploymentUrl.toString() + getJsfPage());
		if (getParameters().size() > 0) {
			for (String key : getParameters().keySet()) {
				String value = getParameters().get(key);
				clientRequest.formParameter(key, value);
			}
		}
		return clientRequest.post(String.class);
	}

	public String getJsfPage() {
		return jsfPage;
	}

	public void setJsfPage(String jsfPage) {
		this.jsfPage = jsfPage;
	}

	public String getTwimlFile() {
		return twimlFile;
	}

	public void setTwimlFile(String twimlFile) {
		this.twimlFile = twimlFile;
	}

	public Map<String, String> getParameters() {
		if (parameters == null)
			this.parameters = new HashMap<String, String>();
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

}
