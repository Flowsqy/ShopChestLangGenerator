package fr.flowsqy.shopchestlanggenerator;

import com.google.gson.JsonElement;
import org.jetbrains.annotations.Nullable;

public class JsonValidator {

    public final boolean validate(@Nullable JsonElement element) {
        if (element == null) {
            System.err.println("Couldn't load the json file");
            return false;
        }
        if (!element.isJsonObject()) {
            System.err.println("Invalid json file");
            return false;
        }
        return true;
    }

}
