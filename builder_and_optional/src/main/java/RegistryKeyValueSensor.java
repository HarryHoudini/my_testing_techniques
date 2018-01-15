import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistryKeyValueSensor {
    private String                             caption;
    private String                             key;
    private String                             valueName;
    private String                             valueContent;
    private RegistryKeyValueData.ValueType     exectedType;
    private RegistryKeyValueData.ConditionType condition;
    private String                             expectedValue;
}
