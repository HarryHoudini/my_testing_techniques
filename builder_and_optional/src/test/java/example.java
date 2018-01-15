public class example {
    public static void main(String[] args) {
        final String                         CAPTION    = "Caption";
        final String                         KEY        = "Key";
        final String                         VALUE_NAME = "Value Name";
        final RegistryKeyValueData.ValueType TYPE       = RegistryKeyValueData.ValueType.DWORD;

        RegistryKeyValueData data = new RegistryKeyValueData(
            RegistryKeyValueData.Builder.of()
                .setCaption(CAPTION)
                .setKey(KEY)
                .setValueName(VALUE_NAME)
                .setExpextedType(TYPE)
        );

        RegistryKeyValueSensor sensor = createRegistryKeyValueSensor(data);
    }

    public static RegistryKeyValueSensor createRegistryKeyValueSensor(RegistryKeyValueData data){
        RegistryKeyValueSensor sensor = new RegistryKeyValueSensor();
        data.getCaption()      .ifPresent(sensor::setCaption);
        data.getKey()          .ifPresent(sensor::setKey);
        data.getValueName()    .ifPresent(sensor::setValueName);
        data.getValueContent() .ifPresent(sensor::setValueName);
        data.getExectedType()  .ifPresent(sensor::setExectedType);
        data.getCondition()    .ifPresent(sensor::setCondition);
        data.getExpectedValue().ifPresent(sensor::setExpectedValue);
        return sensor;
    }
}
