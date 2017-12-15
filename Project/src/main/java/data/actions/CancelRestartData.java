package main.java.data.actions;

import lombok.Getter;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public final class CancelRestartData extends ActionData {
    private final Optional<String> caption;

    public CancelRestartData(Builder builder){
        this.caption  = builder.caption;
    }

    public static class Builder{
        private Optional<String> caption = Optional.empty();

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

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CancelRestartData expectedData = (CancelRestartData) o;

        return OptionalUtils.optionalEquals(this.caption, expectedData.caption);
    }

    @Override
    public int hashCode() {
        return OptionalUtils.optionalHashCode(this.caption);
    }

    @Override
    public String toString() {
        return "CancelRestartData{" +
            "caption=" + OptionalUtils.optionalToString(this.caption) +
            '}';
    }
}
