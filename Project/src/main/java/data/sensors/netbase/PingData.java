package main.java.data.sensors.netbase;

import lombok.Getter;
import main.java.data.sensors.SensorData;
import main.java.utility.utils.OptionalUtils;

import java.util.Optional;

@Getter
public class PingData extends SensorData {
    private final Optional<String> caption;
    private final Optional<String> packetCount;
    private final Optional<String> packetSize;
    private final Optional<String> timeout;
    private final Optional<String> ttl;

    public PingData(Builder builder){
        this.caption     = builder.caption;
        this.packetCount = builder.packetCount;
        this.packetSize  = builder.packetSize;
        this.timeout     = builder.timeout;
        this.ttl         = builder.ttl;
    }

    public static class Builder{
        private Optional<String> caption     = Optional.empty();
        private Optional<String> packetCount = Optional.empty();
        private Optional<String> packetSize  = Optional.empty();
        private Optional<String> timeout     = Optional.empty();
        private Optional<String> ttl         = Optional.empty();

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

        public Builder setPacketCount(Long packetCount){
            this.packetCount = Optional.of(packetCount.toString());
            return this;
        }

        public Builder setPacketCountString(String packetCount){
            this.packetCount = Optional.of(packetCount);
            return this;
        }


        public Builder setPacketSize(Long packetSize){
             this.packetSize = Optional.of(packetSize.toString());
             return this;
        }

        public Builder setPacketSizeString(String packetSize){
             this.packetSize = Optional.of(packetSize);
             return this;
        }

        public Builder setTimeout(Long timeout){
            this.timeout = Optional.of(timeout.toString());
            return this;
        }

        public Builder setTimeoutString(String timeout){
            this.timeout = Optional.of(timeout);
            return this;
        }

        public Builder setTtl(Long ttl){
            this.ttl = Optional.of(ttl.toString());
            return this;
        }

        public Builder setTtlString(String ttl){
            this.ttl = Optional.of(ttl);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PingData expectedData = (PingData) o;

        return
            OptionalUtils.optionalEquals(this.caption,         expectedData.caption)     &&
                OptionalUtils.optionalEquals(this.packetCount, expectedData.packetCount) &&
                OptionalUtils.optionalEquals(this.packetSize,  expectedData.packetSize)  &&
                OptionalUtils.optionalEquals(this.timeout,     expectedData.timeout)     &&
                OptionalUtils.optionalEquals(this.ttl,         expectedData.ttl);
    }

    @Override
    public int hashCode() {
        int result = OptionalUtils.optionalHashCode(this.caption);
        result = 31 * result + OptionalUtils.optionalHashCode(this.packetCount);
        result = 31 * result + OptionalUtils.optionalHashCode(this.packetSize);
        result = 31 * result + OptionalUtils.optionalHashCode(this.timeout);
        result = 31 * result + OptionalUtils.optionalHashCode(this.ttl);
        return result;
    }

    @Override
    public String toString() {
        return "PingData{" +
            "caption="       + OptionalUtils.optionalToString(this.caption)     +
            ", packetCount=" + OptionalUtils.optionalToString(this.packetCount) +
            ", packetSize="  + OptionalUtils.optionalToString(this.packetSize)  +
            ", timeout="     + OptionalUtils.optionalToString(this.timeout)     +
            ", ttl="         + OptionalUtils.optionalToString(this.ttl)         +
            '}';
    }
}
