package ru.itis.flaremarket.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.flaremarket.models.enums.sellitem.SellType;

@Component
public class StringToSellTypeConverter implements Converter<String, SellType> {
    @Override
    public SellType convert(String source) {
        return SellType.valueOf(source.toUpperCase());
    }
}
