import java.util.ArrayList;
import java.util.Scanner;

public class Vet {
    static Scanner keyboardInput = new Scanner(System.in);
    static ArrayList<Animal> animals = new ArrayList<>();
    static ArrayList<Appointment> scheduledAppointments = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Would you like to add an appointment or quit? Add an appointment [app], continue [c]");
            System.out.print("> ");
            String choice = keyboardInput.nextLine();
            if (choice.equalsIgnoreCase("app")) {
                addAppointment();
            } else if (!choice.equalsIgnoreCase("app") && !choice.equalsIgnoreCase("c")) {
                System.out.println("That choice is not valid");
            } else if (choice.equalsIgnoreCase("c")) {
                break;
            }
        }
        while (true) {
            System.out.println("""
                    What would you like to do?
                    [1] View all animals
                    [2] View all appointments
                    [3] Delete an appointment
                    [4] Update an appointment
                    [5] Search an appointment
                    [6] Quit""");
            System.out.print("> ");
            String choice = keyboardInput.nextLine();
            if (choice.equals("1")) {
                listAllAnimals();
            } else if (choice.equals("2")) {
                listAllAppointments();
            } else if (choice.equals("3")) {
                deleteAppointments();
            } else if (choice.equals("4")) {
                updateAppointments();
            } else if (choice.equals("5")) {
                searchAppointment();
            } else if (choice.equals("6")) {
                System.out.println("Bye");
                break;
            } else {
                System.out.println("That is not valid");
            }
        }
    }

    public static Animal addAnimal() {
        System.out.print("Owners name: ");
        String ownerNameInput = keyboardInput.nextLine();
        System.out.print("Animals name: ");
        String animalNameInput = keyboardInput.nextLine();
        System.out.print("Breed: ");
        String breedInput = keyboardInput.nextLine();
        String typeOfAnimalInput;
        while (true) {
            System.out.print("Cat or Dog? ");
            typeOfAnimalInput = keyboardInput.nextLine();
            if (typeOfAnimalInput.equalsIgnoreCase("cat") || (typeOfAnimalInput.equalsIgnoreCase("dog"))) {
                break;
            } else {
                System.out.println("Please choose a valid animal");
            }
        }
        System.out.print("Gender: ");
        String genderInput = keyboardInput.nextLine();
        System.out.print("(Please put only numbers in this field) Weight: ");
        double weightInput = Double.parseDouble(keyboardInput.nextLine());

        Animal pet = new Animal(ownerNameInput, animalNameInput, breedInput, typeOfAnimalInput, genderInput, weightInput);
        animals.add(pet);
        return pet;
    }

    public static void addAppointment() {
        System.out.print("Name: ");
        String nameInput = keyboardInput.nextLine();
        System.out.print("Status: ");
        String statusInput = keyboardInput.nextLine();
        System.out.print("Illness: ");
        String illnessInput = keyboardInput.nextLine();
        System.out.println("Please choose an available time. Available times: [11:00], [1:00], [2:00], [3:00]");
        System.out.print("> ");
        String timeChoice = keyboardInput.nextLine();
        while (true) {
            if (timeChoice.equalsIgnoreCase("11:00") || (timeChoice.equalsIgnoreCase("1:00") ||
                    timeChoice.equalsIgnoreCase("2:00") || timeChoice.equalsIgnoreCase("3:00"))) {
                break;
            } else {
                System.out.println("Invalid time");
            }
        }
        Appointment appointment = new Appointment(nameInput, statusInput, illnessInput, timeChoice, addAnimal());
        scheduledAppointments.add(appointment);
    }

    public static void listAllAnimals() {
        for (Animal a : animals) {
            System.out.println(a.ownerName + a.petName + a.gender + a.breed + a.typeOfAnimal + a.weight);
        }
    }

    public static void listAllAppointments() {
        for (Appointment app : scheduledAppointments) {
            System.out.println(app.name + app.status + app.illness + app.time + app.animal);
        }
    }

    public static void deleteAppointments() {
        Appointment removeAppointment = null;
        boolean found = false;
        System.out.print("Name: ");
        String nameInput = keyboardInput.nextLine();
        for (Appointment app : scheduledAppointments) {
            if (nameInput.equalsIgnoreCase(app.name)) {
                found = true;
                removeAppointment = app;
            }
        }
        if (found) {
            for (Animal a : animals) {
                if (a == removeAppointment.animal) {
                    animals.remove(a);
                    break;
                }
            }
            scheduledAppointments.remove(removeAppointment);

        }
        if (!found) {
            System.out.println("Appointment not found");
        }
    }

    public static void updateAppointments() {
        boolean found = false;
        System.out.print("Name: ");
        String nameInput = keyboardInput.nextLine();
        for (Appointment app : scheduledAppointments) {
            if (nameInput.equalsIgnoreCase(app.name)) {
                System.out.print("Status: ");
                String statusInput = keyboardInput.nextLine();
                System.out.print("Illness: ");
                String illnessInput = keyboardInput.nextLine();
                System.out.print("Time: ");
                String timeInput = keyboardInput.nextLine();
                app.update(statusInput, illnessInput, timeInput);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Contact not found");
        }
    }

    public static void searchAppointment() {
        System.out.print("Name: ");
        String nameInput = keyboardInput.nextLine();
        boolean found = false;
        for (Appointment app : scheduledAppointments) {
            if (nameInput.equalsIgnoreCase(app.name)) {
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Person not found");
        }
    }
}