import java.util.ArrayList;
import java.util.HashMap;


public class PhoneBook {

    ArrayList<Contact> contacts = new ArrayList<>();
    ArrayList<String> groups = new ArrayList<>();

    HashMap<String, ArrayList<Contact>> groupsAndContacts = new HashMap<>();


    public void run(){

        // Тестовое заполнение групп

        groupAdd("Друзья");
        groupAdd("Работа");
        groupAdd("Учеба");
        groupAdd("Избранные");
        groupAdd("Работа");

        groupRemove("Избранные");
        groupRemove("asdasd");


        // Тестовое заполнение контактов

        ArrayList<String> phones = new ArrayList<>();

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

        addContactToGroup(contacts.get(0), "Работа");
        addContactToGroup(contacts.get(1), "Друзья");
        addContactToGroup(contacts.get(0), "Друзья");
        addContactToGroup(contacts.get(2), "Работа");

        showGroups();
        System.out.println();

        findContactByPhoneNumber("+79996662288");
        findContactByPhoneNumber("+898854-855");
    }

/*    public void showPhoneBook(){
        for (Contact contact : contacts){
            System.out.println("    " + contact.lastName + " " + contact.firstName + " " + contact.patronymic);
            ArrayList<String> phones1 = contact.phoneNumbers;
            for (String phone : phones1){
                System.out.println("        " + phone);
            }
            System.out.println();
        }
    }*/

    public void groupAdd(String newGroup){
        if (!groups.contains(newGroup)) {
            this.groups.add(newGroup);
            this.groupsAndContacts.putIfAbsent(newGroup, new ArrayList<>());
            System.out.println("Создана группа : " + newGroup);
        } else {
            System.out.println("Группа не создана так как уже существует.");
        }
    }

    public void groupRemove(String groupToRemove) {
        if (groups.contains(groupToRemove)) {
            this.groups.remove(groupToRemove);
            this.groupsAndContacts.remove(groupToRemove);
            System.out.println("Удалена группа : " + groupToRemove);
        } else {
            System.out.println("Группа не удалена так как уже существует.");
        }
    }

    public void showGroups(){
        for (HashMap.Entry entry: groupsAndContacts.entrySet()) {
            System.out.println(entry.getKey());
            if (((ArrayList<Contact>) entry.getValue()).size() != 0) {
                for (Contact contact : (ArrayList<Contact>) entry.getValue()) {
                    contact.showContact();
                }
            } else {
                System.out.println("    Нет записей.");
            }
        }
    }

    public void addContact(String lastname, String firstname, String patronymic, ArrayList<String> phones){
        contacts.add(new Contact(lastname, firstname, patronymic, phones));
    }

    public void deleteContact(Contact contactToDelete){

        for (HashMap.Entry entry: groupsAndContacts.entrySet()) {
            removeContactFromGroup(contactToDelete, (String) entry.getKey());
        }

        if (contacts.contains(contactToDelete)) {
            contacts.remove(contactToDelete);
        } else {
            System.out.println("Невозможно удалить контакт т.к. такой контакт не существует");
        }
    }

    public void removeContactFromGroup(Contact contactToRemove, String group){
        if (groupsAndContacts.containsKey(group)) {
            if (this.groupsAndContacts.get(group).contains(contactToRemove)) {
                this.groupsAndContacts.get(group).remove(contactToRemove);
            } else {
                System.out.println(" Невозможно удалить контакт из группы. Контакт не состоит в данной группе");
            }
        } else {
            System.out.println(" Группа " + group + " не найдена. Операция невозможна.");
        }
    }

    public void addContactToGroup(Contact contactToAdd, String group){
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

    public void findContactByPhoneNumber(String phoneNumber){
        boolean isFinded = false;
        for (Contact contact : contacts){
            for (String phone : contact.phoneNumbers){
                if (phone.equals(phoneNumber)){
                    contact.showContact();
                    isFinded = true;
                }
            }
        }

        if (!isFinded){
            System.out.println("Контакт не найден.");
        };
    }
}
