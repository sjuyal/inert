package Executor;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class InertDriver {

	static String iepath = null;
	static String chromepath = null;
	static Map<String, String> browserPath = new LinkedHashMap<String, String>();
	static Map<String, String> testBrowserName = new LinkedHashMap<String, String>();
	static int next = 1;
	static Iterator<Entry<String, String>> entries = null;

	public static WebDriver getInstance() {

		if (next == 1) {
			readInertConfigPath();
			readTestConfigPath();
			next++;
			entries = testBrowserName.entrySet().iterator();
		}
		if (entries == null)
			return null;
		else {
			if(entries.hasNext()){
				Entry<String, String> en = entries.next();
				
				while (en != null) {
					if (en.getKey().toString().equalsIgnoreCase("chrome")) {
						return getChrome();
					} else if (en.getKey().toString().equalsIgnoreCase("ie")) {
						return getIE();
					} else if (en.getKey().toString().equalsIgnoreCase("firefox"))
						return getFirefox();
				}
			}
		}
		return null;
	}

	private static void readTestConfigPath() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			String path = System.getProperty("user.dir");
			System.out.println(path);
			Document dom = db.parse(path + "//TestConfigs//TestConfig.xml");
			NodeList nl = dom.getElementsByTagName("browserName");

			if (nl != null && nl.getLength() > 0) {
				for (int i = 0; i < nl.getLength(); i++) {
					Element el = (Element) nl.item(i);
					String p = el.getAttribute("name").toString().toLowerCase();
					String chk = el.getTextContent().toString();
					if (chk.equalsIgnoreCase("true"))
						testBrowserName.put(p, chk);

				}
			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void readInertConfigPath() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			String path = System.getProperty("user.dir");
			System.out.println(path);
			Document dom = db.parse(path + "//Resources//InertConfig.xml");
			NodeList nl = dom.getElementsByTagName("browserName");

			if (nl != null && nl.getLength() > 0) {
				for (int i = 0; i < nl.getLength(); i++) {
					Element el = (Element) nl.item(i);
					String p = el.getAttribute("name").toString();
					browserPath.put(p.toLowerCase(), path + "\\"
							+ el.getTextContent().toString());

				}
			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static ChromeDriver getChrome() {
		ChromeDriver cDriver = null;
		System.setProperty("webdriver.chrome.driver", browserPath.get("chrome"));
		cDriver = new ChromeDriver();
		return cDriver;
	}

	static FirefoxDriver getFirefox() {
		return new FirefoxDriver();
	}

	static InternetExplorerDriver getIE() {
		InternetExplorerDriver cDriver = null;
		System.out.println(browserPath.get("ie"));
		System.setProperty("webdriver.ie.driver", browserPath.get("ie"));

		DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
		capab.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);

		cDriver = new InternetExplorerDriver();
		return cDriver;

	}

}
