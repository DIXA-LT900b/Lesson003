import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class PhoneBook {

    ArrayList<Contact> contacts = new ArrayList<>();
    ArrayList<String> groups = new ArrayList<>();
    HashMap<String, ArrayList<Contact>> groupsAndContacts = new HashMap<>();
    Scanner userInput = new Scanner(System.in);

    boolean isRun;
    byte choose;

    public void init(){

        // Тестовое создание групп

        groupAdd("Друзья");
        groupAdd("Работа");
        groupAdd("Учеба");
        groupAdd("Избранные");


        // Тестовое создание контактов

        ArrayList<String> phones = new ArrayList<>(); // Вспомогательный массив для номеров одного контакта

        phones.add("+7-918-106-63-06");
        phones.add("+7 918 542 99 65");
        addContact("Иванов","Иван", "Иванович", phones);
        phones.clear();

        phones.add("+79996662288");
        addContact("Coitan", "Gheorghe", "Ivanovich", phones);
        phones.clear();

        phones.add("+7 988 885 85 85");
        phones.add("8-989-951-4775");
        addContact("Сидоров", "Сидор", null, phones);
        phones.clear();

        phones.add("+79289289288");
        addContact(null, "Лидия", null, phones);
        phones.clear();


        // Тестовое добавление некоторых контактов в группы

        addContactToGroup(contacts.get(0), "Работа");
        addContactToGroup(contacts.get(1), "Друзья");
        addContactToGroup(contacts.get(0), "Друзья");
        addContactToGroup(contacts.get(2), "Работа");

    }
    public void run(){
        this.isRun = true;

        while (isRun) {
            choose = showUserMenu();
            userInput.reset();

            switch (choose) {
                case 1: {
                    String firstname, lastname, patronymic, phone;
                    ArrayList<String> phones = new ArrayList<>();
                    char addAnotherPhone = 'y';

                    System.out.println("Создать новый контакт.");
                    System.out.println("Введите имя : ");
                    firstname = userInput.nextLine();
                    System.out.println("Введите фамилию : ");
                    lastname = userInput.nextLine();
                    System.out.println("Введите отчество : ");
                    patronymic = userInput.nextLine();


                    while (addAnotherPhone == 'Y' || addAnotherPhone == 'y'){
                        System.out.println("Введите номер телефона : ");
                        phone = userInput.nextLine();

                        System.out.println(" Хотите добавить еще один номер? (Y/N) : ");
                        addAnotherPhone = userInput.nextLine().charAt(0);
                        System.out.println(addAnotherPhone);
                        phones.add(phone);
                    }
                    addContact(lastname, firstname,patronymic,phones);
                    break;
                }
                case 2: {
                    String phoneNumber, group;
                    Contact contact;

                    System.out.println("Добавить контакт в группу.");
                    System.out.println("Введите номер телефона контакта, который хотите добавить в группу : ");
                    phoneNumber = userInput.nextLine().trim();
                    System.out.println("Введите название группы, в которую хотите добавить контакт : ");
                    group = userInput.nextLine().trim();

                    contact = findContactByPhoneNumber(phoneNumber);
                    addContactToGroup(contact, group);

                    break;
                }
                case 3: {
                    System.out.println("Удалить контакт из группы.");

                    System.out.print("Введите номер телефона контакта, который хотите удалить : ");
                    String phoneNumber = userInput.nextLine().trim();

                    System.out.print("Введите название группы, из которой хотите удалить контакт : ");
                    String group = userInput.nextLine().trim();

                    Contact foundedContact = findContactByPhoneNumber(phoneNumber);
                    if (foundedContact != null){
                        removeContactFromGroup(foundedContact, group);
                    } else {
                        System.out.println("Операция невозможна. Контакт не найден.");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Стереть контакт.");

                    System.out.print("Введите номер телефона контакта, который хотите стереть : ");
                    String phoneNumber = userInput.nextLine();
                    phoneNumber = phoneNumber.trim();

                    Contact foundedContact = findContactByPhoneNumber(phoneNumber);
                    deleteContact(foundedContact);

                    break;
                }
                case 5: {

                    break;
                }
                case 6: {
                    System.out.println("Создать новую группу.");
                    System.out.print("Введите название для новой группы : ");

                    String newGroup = userInput.nextLine();
                    newGroup = newGroup.trim();

                    groupAdd(newGroup);
                    break;
                }
                case 7: {
                    System.out.println("Удаление группы.");
                    System.out.println("Введите название группы, которую хотите удалить : ");

                    String groupToRemove = userInput.nextLine();
                    groupToRemove = groupToRemove.trim();

                    groupRemove(groupToRemove);
                    break;
                }
                case 8: {
                    System.out.println("Просмотр всех групп.");
                    showGroups();
                    break;
                }
                case 9: {
                    String phoneNumber;
                    Contact contact;

                    System.out.println("Поиск контакта в книге по номеру телефона.");

                    System.out.println("Введите номер контакта для поиска : ");
                    phoneNumber = userInput.nextLine().trim();

                    contact = findContactByPhoneNumber(phoneNumber);
                    if (contact != null){
                        System.out.println("Контакт найден : ");
                        contact.showContact();
                    }

                    break;
                }
                case 10: {
                    String phone, group;
                    Contact contact;

                    System.out.println("Поиск контакта в группе.");
                    System.out.println("Введите номер контакта для поиска : ");
                    phone = userInput.nextLine().trim();
                    System.out.println("Введите название группы, в которой нужно искать : ");
                    group = userInput.nextLine().trim();

                    findContactInGroup(phone, group);

                    break;
                }
                case 11: {
                    System.out.println("Просмотр всех контактов.");
                    showAllContacts();

                    break;
                }
                case 0: {
                    isRun = false;
                    break;
                }
            }
        }
    }



    public byte showUserMenu(){
        System.out.println();
        System.out.println("        Контакты                                Группы                              Поиск");
        System.out.println("1. Создать контакт.                     6. Создать новую группу.            9. Поиск контакта по номеру.");
        System.out.println("2. Добавить контакт в группу.           7. Удалить группу.                 10. Поиск контакта в группе.");
        System.out.println("3. Удалить контакт из группы.           8. Просмотр всех групп.            11. Просмотр всех контактов. ");
        System.out.println("4. Стереть контакт.");
        System.out.println();
        System.out.print("Выберите пункт меню (Введите 0 для выхода): ");
        byte choose = userInput.nextByte();
        userInput.nextLine();
        System.out.println();
        return choose;

    }






    public void addContact(String lastname, String firstname,                   // 1. Создать новый контакт
                           String patronymic, ArrayList<String> phones){
        contacts.add(new Contact(lastname, firstname, patronymic, phones));
        System.out.println("Новый контакт добавлен.");
    }

    public void addContactToGroup(Contact contactToAdd, String group){          //2. Добавить контакт в группу
        if (groupsAndContacts.containsKey(group)) {
            if (!groupsAndContacts.get(group).contains(contactToAdd)) {
                groupsAndContacts.get(group).add(contactToAdd);
            } else {
                System.out.println("Контакт уже состоит в группе.");
            }
        } else {
            System.out.println("Невозможно добавить. Такой группы не существует.");
        }
    }



    public void removeContactFromGroup(Contact contactToRemove, String group){              // 3. Удалить контакт из группы
        if (contactToRemove != null) {
            if (groupsAndContacts.containsKey(group)) {
                if (this.groupsAndContacts.get(group).contains(contactToRemove)) {
                    this.groupsAndContacts.get(group).remove(contactToRemove);
                    System.out.println("Контакт найден и удален из группы " + group + ".");
                } else {
                    System.out.println(" Невозможно удалить контакт из группы. Контакт не состоит в данной группе");
                }
            } else {
                System.out.println(" Группа " + group + " не найдена. Операция невозможна.");
            }
        }
    }

    public void deleteContact(Contact contactToDelete){                 // 4. Стереть контакт

        for (HashMap.Entry entry: groupsAndContacts.entrySet()) {
            removeContactFromGroup(contactToDelete, (String) entry.getKey());
        }

        if (contacts.contains(contactToDelete)) {
            contacts.remove(contactToDelete);
            System.out.println("Контакт найден и удален.");
        } else {
            System.out.println("Невозможно удалить контакт. Контакт не существует.");
        }
    }

    public void groupAdd(String newGroup){                  // 6. Создать новую группу
        if (!groups.contains(newGroup)) {
            if (newGroup != "") {
                this.groups.add(newGroup);
                this.groupsAndContacts.putIfAbsent(newGroup, new ArrayList<>());
                System.out.println("Создана группа : " + newGroup);
            } else {
                System.out.println("Группа не создана. Название группы не может быть пустым.");
            }
        } else {
            System.out.println("Группа не создана так как уже существует.");
        }
    }

    public void groupRemove(String groupToRemove) {                 // 7. Удалить группу
        if (groups.contains(groupToRemove)) {
            this.groups.remove(groupToRemove);
            this.groupsAndContacts.remove(groupToRemove);
            System.out.println("Удалена группа : " + groupToRemove);
        } else {
            System.out.println("Группа не удалена так как такой группы не существует.");
        }
    }

    public void showGroups(){                   // 8. Просмотр всех групп
        for (HashMap.Entry entry: groupsAndContacts.entrySet()) {
            System.out.println(entry.getKey());
            if (((ArrayList<Contact>) entry.getValue()).size() != 0) {
                for (Contact contact : (ArrayList<Contact>) entry.getValue()) {
                    contact.showContact();
                }
            } else {
                System.out.println("    Нет записей.");
                System.out.println();
            }
        }
    }

    public Contact findContactByPhoneNumber(String phoneNumber){            // 9. Поиск контакта по номеру.
        boolean isFound = false;
        for (Contact contact : contacts){
            for (String phone : contact.phoneNumbers){
                if (phone.equals(phoneNumber)){
                    contact.showContact();
                    isFound = true;
                    return contact;
                }
            }
        }

        if (!isFound){
            System.out.println("Контакт не найден.");
        }
        return null;
    }

    public void findContactInGroup(String phoneNumber, String group){               // 10. Поиск контакта в группе
        Contact contact;
        contact = findContactByPhoneNumber(phoneNumber);

        if (groupsAndContacts.containsKey(group)){
            if (groupsAndContacts.get(group).contains(contact)){
                System.out.println("Контакт с номером " + phoneNumber + " состоит в группе " + group + ".");
            } else {
                System.out.println("Контакт с номером " + phoneNumber + " не состоит в группе " + group + ".");
            }
        } else {
            System.out.println("Группа " + group + " не найдена.");
        }
    }

    public void showAllContacts(){                  // 11. Просмотр всех контактов
        for (Contact contact : contacts){
            System.out.println("    " + contact.makeSolidName());
            ArrayList<String> phones1 = contact.phoneNumbers;
            for (String phone : phones1){
                System.out.println("        " + phone);
            }
            System.out.println();
        }
    }
}
