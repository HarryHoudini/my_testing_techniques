package main.java.constants;

import com.google.common.io.Files;
import main.java.utility.utils.config.BrowserType;

import java.io.File;

public final class DependingConstants {

    private DependingConstants(){
        throw new UnsupportedOperationException("Illegal access to private constructor!");
    }

    public final static String OCONTROL_PATH = "C:\\Program Files (x86)\\Network Olympus\\Bin\\OControl.exe";

    // "http://localhost:3000", "https://10.0.0.239:3000"
    public final static String WEBSERVER_URL = "https://10.0.0.176:3000";

    public final static BrowserType BROWSER_TYPE = BrowserType.CHROME;

    // Name of the attribute of ITestContext that stores the value URL reload of page
    public final static String NAME_ATTR_URL = "baseURL";
    public final static String NAME_ATTR_WEBDRIVER = "webDriver";


    public final static String TEMP_DIR = Files.createTempDir().getAbsolutePath();
}
