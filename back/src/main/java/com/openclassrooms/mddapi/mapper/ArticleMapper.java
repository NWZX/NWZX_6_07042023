package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.Article;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper implements EntityMapper<ArticleDto, Article> {

    private final ModelMapper modelMapper;

    public ArticleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public Article toEntity(ArticleDto dto) {
        return modelMapper.map(dto, Article.class);
    }

    @Override
    public ArticleDto toDto(Article entity) {
        return modelMapper.map(entity, ArticleDto.class);
    }

    @Override
    public List<Article> toEntity(List<ArticleDto> dtoList) {
        return dtoList.stream()
                .map(dto -> modelMapper.map(dto, Article.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<ArticleDto> toDto(List<Article> entityList) {
        return entityList.stream()
                .map(entity -> modelMapper.map(entity, ArticleDto.class))
                .collect(Collectors.toList());
    }
}
