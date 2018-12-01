package org.itstep.msk;

import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Implementation of the interface Command to print out Contacts
 * Uses Repository to get contacts
 *
 * @author Марк Михайлович
 * @version 1.0
 * */
public class PrintContactsCommand implements Command {
    private final PrintWriter printer;
    private final SimpleContactBook book;

    public PrintContactsCommand(PrintWriter printer, SimpleContactBook book) {
        this.printer = printer;
        this.book = book;
    }

    /**
     * Reads data from repository SimpleContactBook book and wraps contacts into StringContactFormatter
     * */
    public void exec() {
        Iterable<ContactFormatter> formatters = StreamSupport.stream(book.read().spliterator(),true)
                .map(x->new StringContactFormatter(x,18,20))
                .collect(Collectors.toList());

        for (ContactFormatter cf : formatters) {
            cf.print(printer);
            printer.println();
        }
        printer.flush();
    }
}
