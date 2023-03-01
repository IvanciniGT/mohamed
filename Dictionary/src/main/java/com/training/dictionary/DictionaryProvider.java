package com.training.dictionary;

import java.util.Optional;

public interface DictionaryProvider {

    boolean exists(String language);

    Optional<Dictionary> getDictionary(String language);

}
