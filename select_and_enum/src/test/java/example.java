import org.junit.Test;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertTrue;

public class example {
    @Test
    public void setGigabytes(){
        Select<UnitSize> measureSelect = new Select<>($(byAttribute("data-control-name","ratio")), UnitSize.class);

        measureSelect.selectItem(UnitSize.GIGABYTES);

        assertTrue(measureSelect.getSelectedOption() == UnitSize.GIGABYTES);
    }
}
