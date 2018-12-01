package org.itstep.msk;

import java.io.IOException;
import java.io.Writer;

/**
 * Intended to be executed in case of command not found in a map in a CommandApplication
 *
 * @author Марк Михайлович
 * @version 1.0
 * */
public class OnWrongCommand implements Command {
    private final String text;
    private final Writer writeTo;

    public OnWrongCommand(String text, Writer writeTo) {
        this.text = text;
        this.writeTo = writeTo;
    }

    /**
     * Writes the String text supplied with constructor to the Writer writeTo supplied with constructor
     *
     * @throws IOException if something gone wrong inside the Writer
     * */
    @Override
    public void exec() throws IOException {
        writeTo.write(text);
        writeTo.flush();
    }
}
