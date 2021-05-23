package ru.itis.flaremarket.convertor;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.flaremarket.models.enums.sellitem.Game;

@Component
public class StringToGameConverter implements Converter<String, Game> {
    @Override
    public Game convert(String source) {
        return Game.valueOf(source.toUpperCase());
    }
}