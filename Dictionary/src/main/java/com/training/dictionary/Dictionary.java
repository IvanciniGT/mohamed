package com.training.dictionary;

import java.util.List;
import java.util.Optional;

public interface Dictionary {

    boolean exists(String word);

    Optional<List<String>> getMeanings(String word);

    List<String> getSuggestions(String word);

}
