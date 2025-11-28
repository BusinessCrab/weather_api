package com.business_crab.weather_api.component;

import java.io.IOException;
import java.util.Objects;

import javax.sql.rowset.serial.SerialException;

import org.hibernate.type.SerializationException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.business_crab.weather_api.model.Weather;

import tools.jackson.databind.ObjectMapper;

public class WeatherSerializer implements RedisSerializer<Weather> {
    private final ObjectMapper objectMapper;
    public WeatherSerializer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public byte[] serialize(final Weather value) throws SerializationException {
        try {
            return objectMapper.writeValueAsBytes(value);
        } catch (JsonParseException exception) {
            throw new SerializationException("Serialization Weather to JSON error" , exception);
        }
    }

    @Override
    public Weather deserialize(final byte[] bytes) throws SerializationException {
        try {
            if (Objects.nonNull(bytes)) {
                return objectMapper.readValue(bytes, Weather.class);
            }
            return null;
        } catch (JsonParseException exception) {
            throw new SerializationException("Deserialization Weather to JSON error" , exception);
        }
    }
}