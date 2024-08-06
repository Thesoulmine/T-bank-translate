package org.example.tbanktranslate.mapper;

import org.example.tbanktranslate.dto.TranslationRequestDTO;
import org.example.tbanktranslate.dto.TranslationResponseDTO;
import org.example.tbanktranslate.model.Translation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TranslationMapper {

    Translation toEntity(TranslationRequestDTO translationRequestDto);

    TranslationRequestDTO toRequestDTO(Translation translation);

    TranslationResponseDTO toResponseDTO(Translation translation);
}
