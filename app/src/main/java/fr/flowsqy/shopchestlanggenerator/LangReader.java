package fr.flowsqy.shopchestlanggenerator;

import java.io.Reader;

import org.jetbrains.annotations.NotNull;

public interface LangReader {

    @NotNull
    Reader getReader();

    void close();

}
