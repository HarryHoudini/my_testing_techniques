package main.java.data.actions;

import lombok.Getter;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public final class DelayData extends ActionData {
    private final Optional<String> caption;
    private final Optional<String> time;

    public DelayData(Builder builder){
        this.caption  = builder.caption;
        this.time     = builder.time;
    }

    public static class Builder{
        private Optional<String> caption = Optional.empty();
        private Optional<String> time    = Optional.empty();

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

        public Builder setTime(Long time){
            this.time = Optional.of(time.toString());
            return this;
        }

        public Builder setTimeString(String time){
            this.time = Optional.of(time);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DelayData expectedData = (DelayData) o;

        return
            OptionalUtils.optionalEquals(this.caption, expectedData.caption) &&
            OptionalUtils.optionalEquals(this.time,    expectedData.time);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.time);
        return result;
    }

    @Override
    public String toString() {
        return "DelayData{" +
            "caption=" + OptionalUtils.optionalToString(this.caption) +
            ", time="  + OptionalUtils.optionalToString(this.time)    +
            '}';
    }
}
