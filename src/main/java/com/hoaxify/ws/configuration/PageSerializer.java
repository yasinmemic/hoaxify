package com.hoaxify.ws.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * Created By Yasin Memic on Apr, 2020
 */
@JsonComponent
public class PageSerializer extends JsonSerializer<Page<?>> {

    //@JsonView annotation, doesn't write a value without list or single value. It just writes List and single value!
    //This method solves that problem.
    @Override
    public void serialize(Page<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("content");
        serializers.defaultSerializeValue(value.getContent(), gen);
        gen.writeObjectField("pageable", value.getPageable());
        gen.writeBooleanField("last", value.isLast());
        gen.writeNumberField("totalPages", value.getTotalPages());
        gen.writeNumberField("totalElements", value.getTotalElements());
        gen.writeNumberField("size", value.getSize());
        gen.writeNumberField("number", value.getNumber());
        gen.writeObjectField("sort", value.getSort());
        gen.writeNumberField("numberOfElements", value.getNumberOfElements());
        gen.writeBooleanField("first", value.isFirst());
        gen.writeBooleanField("empty", value.isEmpty());
        gen.writeEndObject();
    }
}
