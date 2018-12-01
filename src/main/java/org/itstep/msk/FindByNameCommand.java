package org.itstep.msk;

import java.io.PrintWriter;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FindByNameCommand implements Command {
    private final FindByNameSpecificationFactory specFactory;
    private final SpecificationContactBook contactBook;
    private final Supplier<String> dataSupplier;
    private final PrintWriter printer;

    public FindByNameCommand(FindByNameSpecificationFactory specFactory, SpecificationContactBook contactBook, Supplier<String> dataSupplier, PrintWriter printer) {
        this.specFactory = specFactory;
        this.contactBook = contactBook;
        this.dataSupplier = dataSupplier;
        this.printer = printer;
    }

    /**
     * Builds a ContactSpecification and executes one with SpecificationRepository given
     * Then prints contacts to the given PrintWriter using StringContactFormatter
     * */
    @Override
    public void exec() throws Exception {
        printer.print("Введите имя для поиска: "); printer.flush();
        String name = dataSupplier.get();
        Iterable<Contact> contacts = contactBook.read(specFactory.create(name));

        Iterable<ContactFormatter> formatters = StreamSupport.stream(contacts.spliterator(),true)
                .map(x->new StringContactFormatter(x,18,20))
                .collect(Collectors.toList());

        for (ContactFormatter cf : formatters) {
            cf.print(printer);
            printer.println();
        }
        printer.flush();
    }
}
