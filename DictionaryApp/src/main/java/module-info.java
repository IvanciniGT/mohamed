module dictionary.app {
    requires dictionary;
    // This module uses/need a valid DictionaryProvider implementation.
    uses com.training.dictionary.DictionaryProvider;
}