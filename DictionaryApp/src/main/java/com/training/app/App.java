package com.training.app;

import com.training.dictionary.Dictionary;
import com.training.dictionary.DictionaryProvider;

import java.util.ServiceLoader;

public class App {

    public static void main(String[] args){
        System.out.println("Dictionary app!");
        if (args.length != 2){
            System.err.println("You have to supply a language and a word.");
            System.err.println("For example: 'ES break'");
            System.exit(1);
        }

        String language = args[0];
        String word     = args[1];

        System.out.println("Language: " + language);
        System.out.println("Word:     " + word);

        // Dependency Injection: My class is NOT GOING TO CREATE instance of objects
        //                       My class is going to ASK FOR instances of objects
        Iterable<DictionaryProvider> providers = ServiceLoader.load(DictionaryProvider.class);
        // The point here is:
        // I will have several dictionaries implementations.
        // I have an application
        // Those are different things (projects)... And I don't want to have a string coupling between them
        // I want a loose coupling between those projects.

        // Look for a valid dictionary of the supplied language.
        Dictionary myDictionary = null;
        // We are going to ask every single dictionary provider to check whether it has a dictionary for
        // the supplied language or not
        for(DictionaryProvider provider : providers){
            if(provider.exists(language)){
                System.out.println("Loading the dictionary for: " + language);
                myDictionary = provider.getDictionary(language).orElseThrow();
                                            // ^ get the dictionary.
                                            // orElseThrow: In case there is no dictionary throw a NoSuchElementException
                                            //               The dict implementation has a bug !
                break; // Stop looking for more providers
            }
        }
        if(myDictionary == null){
            System.err.println("There is no dictionary for: " + language);
            System.err.println("Sorry");
            System.exit(2);
        }
        // In case we have a valid Dictionary
        if(myDictionary.exists(word)){
            System.out.println("The word " + word + " exists in " + language);
            System.out.println("Meanings: ");
            myDictionary.getMeanings(word).orElseThrow().stream().forEach(System.out::println);
                                                        // This stream(). is optional in this case.
                                                        // Java will add this for us
            // For each meaning, supply that meaning to the System.out::println function
            // So it gets printed.
        }else{
            System.out.println("The word " + word + " does not exists in " + language);
            System.out.println("Maybe you wanted to say: ");
            myDictionary.getSuggestions(word).forEach(System.out::println);
        }
    }

}
