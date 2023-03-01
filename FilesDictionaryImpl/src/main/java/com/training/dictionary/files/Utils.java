package com.training.dictionary.files;

import com.training.dictionary.Dictionary;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Utils {

    static final int MAX_ALLOWED_DISTANCE = 2;
    static final int MAX_SUGGESTIONS = 10;

    static String normalizeWord(String word){
        return word.toLowerCase();
    }


    static int levenshteinDistance(String str1, String str2) {
        return computeLevenshteinDistance(str1.toCharArray(),
                str2.toCharArray());
    }

    private static int minimum(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private static int computeLevenshteinDistance(char [] str1, char [] str2) {
        int [][]distance = new int[str1.length+1][str2.length+1];

        for(int i=0;i<=str1.length;i++){
            distance[i][0]=i;
        }
        for(int j=0;j<=str2.length;j++){
            distance[0][j]=j;
        }
        for(int i=1;i<=str1.length;i++){
            for(int j=1;j<=str2.length;j++){
                distance[i][j]= minimum(distance[i-1][j]+1,
                        distance[i][j-1]+1,
                        distance[i-1][j-1]+
                                ((str1[i-1]==str2[j-1])?0:1));
            }
        }
        return distance[str1.length][str2.length];

    }

    private static Optional<Path> getDictionaryFilePath(String language){
        URL dictionaryFileURL = Utils.class.getClassLoader() // This program loads the code
                   .getResource("dictionary."+language+".txt") ;
        if(dictionaryFileURL != null)
            try {
                return Optional.of(Path.of(dictionaryFileURL.toURI()));
            }catch(Exception e){
                System.err.println("Looks like There is a bug in the JAVA API");
            }
        return Optional.empty();
    }

    static boolean existsDictionaryFile(String language) {
        return getDictionaryFilePath(language).isPresent();
    }

    static Optional<Dictionary> readDictionaryFile(String language) {
        Optional<Path> dictionaryFilePath = getDictionaryFilePath(language);
        if (dictionaryFilePath.isPresent()){
            // Read the file and return a new Dictionary
            // In java 11, they added a new Function to read files.
            try {
                String contents = Files.readString(dictionaryFilePath.orElseThrow());
                // In Java 11 they created a new function inside String: lines()
                // That function return an STREAM!!!!
                Map<String, List<String>> words = contents.lines()
                                                          .filter( line -> ! line.isBlank() ) //Only keep NON blank lines
                                                          .map(  line -> line.split("=") )
                                                          .collect( Collectors.toMap(
                                                                                        array -> normalizeWord(array[0]), //The key of the map: WORD
                                                                                        array -> Arrays.asList(array[1].split("\\|")) // The value of the map: Meanings (List<String>)
                                                                                    ));
                return Optional.of(new FilesDictionary(words));
            }catch(Exception e){
                System.err.println("Problems while reading the file... sorry!");
            }
        }
        return Optional.empty();

    }
}
