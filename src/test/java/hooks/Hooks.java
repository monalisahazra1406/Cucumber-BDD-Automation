package hooks;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utils.ScreenshotUtils;

public class Hooks {

    @Before
    public void setUp() {


        DriverFactory.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {

        byte[] screenshot = ScreenshotUtils.captureScreenshot(DriverFactory.getDriver());
        if(scenario.isFailed()){

            scenario.attach(screenshot,"image/png","Failure screenshot");
        }
        scenario.attach(screenshot, "image/png", "Passing screenshot");
        DriverFactory.quitDriver();
    }
}
