package test.e2e.widgets.nettree;

import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.utils.MimeTypeDetector;

import java.io.File;
import java.io.IOException;

/**
 * Created by Saveg on 10/9/2017.
 */



public class test {

    public static void main(String[] args) throws IOException {
        final String detect = MimeTypeDetector.detect(new File("C:\\TEMP\\1.png"));
//        ReportPortal.emitLog("asda", "WARN", Calendar.getInstance().getTime(), file);
        try {
            final ReportPortalMessage dsd = new ReportPortalMessage(new File("C:\\TEMP\\1.png"), "dsd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


