module files.dictionary.implementation {
    requires dictionary;
    provides com.training.dictionary.DictionaryProvider
            with com.training.dictionary.files.FilesDictionaryProvider;
    // We offer an implementation of that Interface
}

