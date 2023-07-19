package fr.flowsqy.shopchestlanggenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class LangWriter {

    private final KeyFilter keyFilter;

    public LangWriter(@NotNull KeyFilter keyFilter) {
        this.keyFilter = keyFilter;
    }

    public void write(@NotNull JsonObject object, @NotNull File outputFile) {
        final BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            if (!entry.getValue().isJsonPrimitive()) {
                continue;
            }
            if (!keyFilter.isAllowed(entry.getKey())) {
                continue;
            }
            final JsonPrimitive value = entry.getValue().getAsJsonPrimitive();
            if (!value.isString()) {
                continue;
            }
            try {
                writer.write(entry.getKey());
                writer.write("=");
                writer.write(value.getAsString());
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
