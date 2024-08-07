package org.example.tbanktranslate.service;

import com.google.common.util.concurrent.RateLimiter;
import org.example.tbanktranslate.client.TranslateClient;
import org.example.tbanktranslate.dao.TranslationDAO;
import org.example.tbanktranslate.exception.YandexClientException;
import org.example.tbanktranslate.model.Translation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class TranslationServiceImpl implements TranslationService {

    private final TranslationDAO translationDAO;
    private final TranslateClient translateClient;
    private final ExecutorService executorService;
    private final RateLimiter rateLimiter;

    public TranslationServiceImpl(TranslationDAO translationDAO,
                                  TranslateClient translateClient) {
        this.translationDAO = translationDAO;
        this.translateClient = translateClient;
        executorService = Executors.newFixedThreadPool(10);
        rateLimiter = RateLimiter.create(10);
    }

    @Override
    public Translation translateAndSave(Translation translation) throws YandexClientException {
        List<Future<String>> futures = submitTranslateTasks(translation);
        StringBuilder translatedText = new StringBuilder();

        for (Future<String> future : futures) {
            try {
                translatedText.append(future.get()).append(" ");
            } catch (InterruptedException ignored) {

            } catch (ExecutionException exception) {
                throw new YandexClientException(exception.getCause().getMessage());
            }
        }

        translation.setTargetText(translatedText.toString().stripTrailing());
        translationDAO.save(translation);

        return translation;
    }

    private List<Future<String>> submitTranslateTasks(Translation translation) {
        List<Future<String>> futures = new ArrayList<>();

        for (String word : translation.getSourceText().split(" ")) {
            rateLimiter.acquire(1);
            Future<String> future = executorService.submit(() ->
                    translateClient.translate(
                            word,
                            translation.getSourceLanguageCode(),
                            translation.getTargetLanguageCode()));
            futures.add(future);
        }

        return futures;
    }
}
