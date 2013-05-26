package org.twiliofaces.test.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

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
import org.twiliofaces.test.twilioscope.TwilioScopeController;

public abstract class AbstractTwimlClientTest {

	@ArquillianResource
	URL deploymentUrl;

	static String TWIML_FOLDER = "src/test/resources/twiml/";
	static String XSD_FOLDER = "src/test/resources/xsd/";

	private String jsfPage;
	private String twimlFile;
	private String xsdFile;
	private Map<String, String> parameters;

	@Deployment
	public static Archive<?> createDeployment() {

		MavenDependencyResolver resolver = DependencyResolvers.use(
				MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
		WebArchive war = ShrinkWrap
				.create(WebArchive.class, "twilio.war")
				.addClass(AbstractTwimlClientTest.class)
				.addPackage(TwilioEvaluator.class.getPackage().getName())
				.addPackage(TwilioScopeController.class.getPackage().getName())
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
				.addAsWebResource("pages/clientWithExtension.xhtml")
				.addAsWebResource("pages/client.xhtml")
				.addAsWebResource("pages/conference.xhtml")
				.addAsWebResource("pages/dial.xhtml")
				.addAsWebResource("pages/enqueue.xhtml")
				.addAsWebResource("pages/gather.xhtml")
				.addAsWebResource("pages/hangup.xhtml")
				.addAsWebResource("pages/leave.xhtml")
				.addAsWebResource("pages/leaveWithExtension.xhtml")
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
				.addAsWebResource("pages/jsClient.xhtml")
				.addAsWebInfResource("common/faces-config.xml",
						"faces-config.xml")
				.addAsWebInfResource("common/web.xml", "web.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(war.toString(true));
		return war;
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

	protected String getXsd() throws Exception {
		String content = null;
		File file = new File(XSD_FOLDER + getXsdFile());
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

	public ClientResponse<String> execute(String jsfPage,
			Map<String, String> parameters) throws Exception {
		ClientRequest clientRequest = new ClientRequest(
				deploymentUrl.toString() + jsfPage);
		if (parameters.size() > 0) {
			for (String key : parameters.keySet()) {
				String value = parameters.get(key);
				clientRequest.formParameter(key, value);
			}
		}
		return clientRequest.post(String.class);
	}

	public boolean validateAgainstXSD() throws Exception {
		InputStream xmlIs = new ByteArrayInputStream(getTwiml().getBytes());
		InputStream xsdIs = new ByteArrayInputStream(getXsd().getBytes());
		return validateAgainstXSD(xmlIs, xsdIs);
	}

	public static boolean validateAgainstXSD(String xml, String xsd) {
		InputStream xmlIs = new ByteArrayInputStream(xml.getBytes());
		InputStream xsdIs = new ByteArrayInputStream(xsd.getBytes());
		return validateAgainstXSD(xmlIs, xsdIs);
	}

	public static boolean validateAgainstXSD(InputStream xml, InputStream xsd) {
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(xsd));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
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

	public void addParameter(String key, String value) {
		getParameters().put(key, value);
	}

	public void clearParameters() {
		this.parameters = null;
	}

	public String getXsdFile() {
		return xsdFile;
	}

	public void setXsdFile(String xsdFile) {
		this.xsdFile = xsdFile;
	}

}
