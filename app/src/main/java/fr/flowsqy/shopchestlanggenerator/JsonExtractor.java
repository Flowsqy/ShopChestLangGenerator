package fr.flowsqy.shopchestlanggenerator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonExtractor {

    private final LangReader langReader;

    public JsonExtractor(@NotNull LangReader langReader) {
        this.langReader = langReader;
    }

    @Nullable
    public JsonElement extract() {
        try {
            return JsonParser.parseReader(langReader.getReader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
