package Android.tests.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class LaunchingAndroidDevice {
	private AndroidDriver driver;
	
	public AndroidDriver configureAndroidPhone() throws MalformedURLException, FileNotFoundException {
	    // Read data from JSON file
	    File file = new File("src/test/java/config/androidPhone.json");
	    InputStream is = new FileInputStream(file);
	    JsonReader reader = Json.createReader(is);
	    JsonObject jsonObject = reader.readObject();
	    reader.close();

	    File fil = new File(jsonObject.getString("appLink"));
	    File fs = new File(fil, jsonObject.getString("apk"));
	    String URLs = jsonObject.getString("url");

	    // Set desired capabilities
	    DesiredCapabilities cap = new DesiredCapabilities();
	    cap.setCapability(MobileCapabilityType.DEVICE_NAME, jsonObject.getString("deviceName"));
	    cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
	    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, jsonObject.getString("platformVersion"));
	    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, jsonObject.getString("automationName"));
	    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, jsonObject.getString("platformName"));

	    try {
	        driver = new AndroidDriver(new URL(URLs), cap);
	        System.out.println(URLs);
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return driver;
	    
	    
	}
	public AndroidDriver configureAndroidTablet() throws MalformedURLException, FileNotFoundException {
	    // Read data from JSON file
	    File file = new File("src/test/java/config/androidTablet.json");
	    InputStream is = new FileInputStream(file);
	    JsonReader reader = Json.createReader(is);
	    JsonObject jsonObject = reader.readObject();
	    reader.close();

	    File fil = new File(jsonObject.getString("appLink"));
	    File fs = new File(fil, jsonObject.getString("apk"));
	    String URLs = jsonObject.getString("url");

	    // Set desired capabilities
	    DesiredCapabilities cap = new DesiredCapabilities();
	    cap.setCapability(MobileCapabilityType.DEVICE_NAME, jsonObject.getString("deviceName"));
	    cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
	    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, jsonObject.getString("platformVersion"));
	    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, jsonObject.getString("automationName"));
	    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, jsonObject.getString("platformName"));

	    try {
	        driver = new AndroidDriver(new URL(URLs), cap);
	        System.out.println(URLs);
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return driver;
	    
	    
	}

}
