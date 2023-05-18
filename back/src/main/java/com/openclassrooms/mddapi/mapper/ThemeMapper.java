package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ThemeMapper implements EntityMapper<ThemeDto, Theme> {

        private final ModelMapper modelMapper;

        public ThemeMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }
        @Override
        public Theme toEntity(ThemeDto dto) {
            return modelMapper.map(dto, Theme.class);
        }

        @Override
        public ThemeDto toDto(Theme entity) {
            return modelMapper.map(entity, ThemeDto.class);
        }

        @Override
        public List<Theme> toEntity(List<ThemeDto> dtoList) {
            return dtoList.stream()
                    .map(dto -> modelMapper.map(dto, Theme.class))
                    .collect(Collectors.toList());
        }
        @Override
        public List<ThemeDto> toDto(List<Theme> entityList) {
            return entityList.stream()
                    .map(entity -> modelMapper.map(entity, ThemeDto.class))
                    .collect(Collectors.toList());
        }
}