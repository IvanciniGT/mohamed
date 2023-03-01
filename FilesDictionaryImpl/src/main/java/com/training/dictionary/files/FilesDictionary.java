package com.training.dictionary.files;

import com.training.dictionary.Dictionary;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilesDictionary implements Dictionary {

    private final Map<String,List<String>> words;

    public FilesDictionary(Map<String,List<String>> words){ // break  Break  BREAK
        this.words = words;
    }

    @Override
    public boolean exists(String word) {
        return words.containsKey( Utils.normalizeWord(word) );
    }

    @Override
    public Optional<List<String>> getMeanings(String word) {
        if (exists(word))
            return Optional.of(words.get( Utils.normalizeWord(word) ));
        else
            return Optional.empty();
    }

    @Override
    public List<String> getSuggestions(String suppliedWord) {
        String normalizedWord = Utils.normalizeWord(suppliedWord);
        return words.keySet()                           // just get the known words from my dictionary (file)
             .parallelStream()                          // allows me to use functional programming
                                                        // This is going to open MULTIPLE THREADs (up to the number of cores in my machine)
             .map(     word       -> new Suggestion( Utils.levenshteinDistance(word, normalizedWord) , word) ) // Creating a suggestion, including the levenst. Distance
             .filter(  suggestion -> suggestion.distance <= Utils.MAX_ALLOWED_DISTANCE                       ) // Remove suggestions with big distance values
             .sorted(  Comparator.comparing( suggestion -> suggestion.distance )                             ) // Sort suggestions by distance
             .map(     suggestion -> suggestion.word                                                         ) // Keep only the word. Forget about the distance
             .limit(   Utils.MAX_SUGGESTIONS                                                                 ) //Keep only a maximum number of suggestions
             .collect( Collectors.toList() );           // We return the words as a list. DONE !
    }
    // This is just a Data Structure to hold both the Original Word and the Distance
    // (how that original words differs from the supplied word)
    private static class Suggestion{
        public int distance;
        public String word;

        public Suggestion(int distance,String word) {
            this.distance = distance;
            this.word = word;
        }
    }
}
