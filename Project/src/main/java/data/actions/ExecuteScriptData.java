package main.java.data.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.utility.interfaces.MyLocatorInterface;
import main.java.utility.interfaces.MySelectOptionsInterface;
import main.java.utility.utils.OptionalUtils;
import org.openqa.selenium.By;

import java.util.Optional;

import static com.codeborne.selenide.Selectors.byValue;

@Getter
public final class ExecuteScriptData extends  ActionData {
    private final Optional<String>     caption;
    private final Optional<String>     script;
    private final Optional<ScriptType> scriptType;

    @AllArgsConstructor
    @Getter
    public enum ScriptType implements MyLocatorInterface, MySelectOptionsInterface {
        VBS ("string:vbs"),
        JS  ("string:js"),
        BAT ("string:bat"),
        CMD ("string:cmd"),
        PS  ("string:ps1");

        private final String value;

        public By getLocator(){
            return byValue(this.value);
        }
    }

    public ExecuteScriptData(Builder builder){
        this.caption    = builder.caption;
        this.script     = builder.script;
        this.scriptType = builder.scriptType;
    }

    public static class Builder{
        private Optional<String>     caption    = Optional.empty();
        private Optional<String>     script     = Optional.empty();
        private Optional<ScriptType> scriptType = Optional.empty();

        public static Builder of(){
            return new Builder();
        }

        public static Builder of(String caption){
            return Builder.of().setCaption(caption);
        }

        public Builder setCaption(String caption){
            this.caption = Optional.of(caption);
            return this;
        }

        public Builder setScript(String script){
            this.script = Optional.of(script);
            return this;
        }

        public Builder setScriptType(ScriptType scriptType){
            this.scriptType = Optional.of(scriptType);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExecuteScriptData expectedData = (ExecuteScriptData) o;

        return
            OptionalUtils.optionalEquals(this.caption,    expectedData.caption) &&
            OptionalUtils.optionalEquals(this.script,     expectedData.script)  &&
            OptionalUtils.optionalEquals(this.scriptType, expectedData.scriptType);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.script);
        result = 31 * result + OptionalUtils.optionalHashCode(this.scriptType);
        return result;
    }

    @Override
    public String toString() {
        return "ExecuteScriptData{" +
            "caption="      + OptionalUtils.optionalToString(this.caption)    +
            ", script="     + OptionalUtils.optionalToString(this.script)     +
            ", scriptType=" + OptionalUtils.optionalToString(this.scriptType) +
            '}';
    }
}
