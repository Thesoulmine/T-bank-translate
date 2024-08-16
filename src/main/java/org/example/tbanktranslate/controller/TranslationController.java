package org.example.tbanktranslate.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.tbanktranslate.dto.ExceptionResponseDTO;
import org.example.tbanktranslate.dto.TranslationRequestDTO;
import org.example.tbanktranslate.dto.TranslationResponseDTO;
import org.example.tbanktranslate.exception.TranslateClientException;
import org.example.tbanktranslate.mapper.TranslationMapper;
import org.example.tbanktranslate.model.Translation;
import org.example.tbanktranslate.service.TranslationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class TranslationController {

    private final TranslationService translationService;
    private final TranslationMapper translationMapper;

    public TranslationController(TranslationService translationService,
                                 TranslationMapper translationMapper) {
        this.translationService = translationService;
        this.translationMapper = translationMapper;
    }

    @PostMapping("/api/translate")
    public ResponseEntity<TranslationResponseDTO> translate(@RequestBody TranslationRequestDTO translationRequestDTO,
                                                            HttpServletRequest request) throws TranslateClientException {
        Translation translation = translationMapper.toEntity(translationRequestDTO);
        translation.setIpAddress(request.getRemoteAddr());

        return new ResponseEntity<>(
                translationMapper.toResponseDTO(translationService.translateAndSave(translation)),
                HttpStatus.OK);
    }

    @ExceptionHandler(TranslateClientException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCustomException(TranslateClientException exception) {
        return new ResponseEntity<>(
                new ExceptionResponseDTO(exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
