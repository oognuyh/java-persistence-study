package com.oognuyh.item19.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BestSellingConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute ? "Yes" : "No";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Yes".equals(dbData);
    }    
}
