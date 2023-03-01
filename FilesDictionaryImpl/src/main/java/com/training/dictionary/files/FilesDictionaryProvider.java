package com.training.dictionary.files;

import com.training.dictionary.Dictionary;
import com.training.dictionary.DictionaryProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FilesDictionaryProvider implements DictionaryProvider {

    private final Map<String, Dictionary> dictionariesCache = new HashMap<>();

    @Override
    public boolean exists(String language) {
        if ( dictionariesCache.containsKey(language) )
            return true;
        if ( Utils.existsDictionaryFile(language) )
            return true;
        else
            return false;
    }

    @Override
    public Optional<Dictionary> getDictionary(String language) {
        if ( ! dictionariesCache.containsKey(language) ){
            Optional<Dictionary> justLoaded = Utils.readDictionaryFile(language);
            if (justLoaded.isPresent())
                dictionariesCache.put(language, justLoaded.orElseThrow());
        }
        return Optional.ofNullable(dictionariesCache.get(language));
                        // If get returns null, an empty Optional will be returned
                        // Otherwise an Optional containing the dictionary will be returned
    }
}
