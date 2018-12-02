package org.itstep.msk;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Application main class
 *
 * @author Марк Михайлович
 * @version 1.0
 */
public final class App {
    public static void main( String[] args ) {
//================================== Testing data ===================================
        ArrayList<Contact> contacts = new ArrayList<>(16);
        contacts.add(new Contact("Павел Иванович Чичиков","+13(900)999-99-99"));
        contacts.add(new Contact("Настасья Петровна Коробочка","+17(900) 999 99 99"));
        contacts.add(new Contact("Михаил Семёнович Сабакевич","8 900 999-99-99"));
        contacts.add(new Contact("Плюшкин","999-99-99"));
        contacts.add(new Contact("Манилов","999 99 99"));
        contacts.add(new Contact("Ноздрёв","8 (900) 999-99-99"));
        contacts.add(new Contact("Хлестаков Иван","+7(900)9999999"));
        contacts.add(new Contact("Земляника Артемий","+4 900 99999999"));
        contacts.add(new Contact("Шлёпкин Иван","8 800 8889999"));
        contacts.add(new Contact("Hercule Poirot","8 800 900 99 99"));
        contacts.add(new Contact("Mary Debenham","+19 900 999 99 99"));
        contacts.add(new Contact("Mrs. Hubbard","+23 900 999-99-99"));

//============================ Application Bootstrap ================================
        SpecificationContactBook contactBook = new ArraySpecificationContactBook(new ArrayContactBook(contacts)); //data source
        PrintWriter printer = new PrintWriter(System.out); //output stream
        Scanner sc = new Scanner(System.in); //user input supplier

        TreeMap<String, NameComparableContact> mapContacts = new TreeMap<>();
        mapContacts.putAll(contacts.stream().collect(Collectors.toMap(Contact::getName,contact ->new NameComparableContact(contact))));
        SpecificationContactBook contactBook1 = new TreeMapContactBook(mapContacts);

        Map<String,Command> commands = new HashMap<>();
        Command onFailure = new OnWrongCommand("Неизвестная команда, повторите ввод"+System.lineSeparator(),printer);
        CommandApplication mainApp = new CommandApplication(commands,onFailure,()->sc.next());

        //populate commands
        commands.put("exit",new ExitCommand(mainApp));
        commands.put("list",new PrintContactsCommand(printer,contactBook));
//        commands.put("find",new FindByNameCommand(new ArrayBackedFindByNameFactory(),contactBook,()->sc.next(),printer));
        commands.put("find",new FindByNameCommand(new TreeMapBackedFindByNameFactory(), contactBook1, () -> sc.next(), printer));
//        commands.put("find",new FindByNameCommand(new TreeMapBackedStartsWithFactory(), contactBook1, () -> sc.next(), printer));

//====================================================================================
        try {
            mainApp.start();
        } catch (Throwable t) {
            System.out.println("Что-то пошло не так...");
            System.out.println(t.getMessage());
        }
    }
}
