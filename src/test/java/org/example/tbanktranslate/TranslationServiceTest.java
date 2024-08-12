package org.example.tbanktranslate;

import org.example.tbanktranslate.client.TranslateClient;
import org.example.tbanktranslate.dao.TranslationDAO;
import org.example.tbanktranslate.model.Translation;
import org.example.tbanktranslate.service.TranslationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TranslationServiceTest {

    @Mock
    private TranslationDAO translationDAO;

    @Mock
    private TranslateClient translateClient;

    @InjectMocks
    private TranslationServiceImpl translationService;

    private Translation translation;

    @BeforeEach
    public void setup() {
        translation = new Translation();
        translation.setSourceText("hello world");
        translation.setSourceLanguageCode("en");
        translation.setTargetLanguageCode("ru");
    }

    @Test
    public void testTranslateAndSave() throws Exception {
        List<String> words = Arrays.asList("привет", "мир");

        when(translateClient.translate(anyString(), anyString(), anyString()))
                .thenReturn(words.get(0))
                .thenReturn(words.get(1));

        Translation result = translationService.translateAndSave(translation);

        assertNotNull(result);
        assertEquals("привет мир", result.getTargetText());
        verify(translationDAO, times(1)).save(result);
    }
}
