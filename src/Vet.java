import java.util.ArrayList;
import java.util.Scanner;

public class Vet {
    static Scanner keyboardInput = new Scanner(System.in);
    static ArrayList<Animal> animals = new ArrayList<>();
    static ArrayList<Appointment> scheduledAppointments = new ArrayList<>();
    static ArrayList<String> times = new ArrayList<>();

    public static void main(String[] args) {
        // Add specific times to the times arraylist
        times.add("11:00");
        times.add("12:00");
        times.add("1:00");
        times.add("2:00");
        times.add("3:00");
        times.add("4:00");
        while (true) {
            System.out.println("Would you like to add an appointment or continue? Add an appointment [app], " +
                    "continue [c]");
            System.out.print("> ");
            String choice = keyboardInput.nextLine();
            if (choice.equalsIgnoreCase("app")) {
                addAppointment();
            } else if (choice.equals("c") && scheduledAppointments.size() == 0) {
                System.out.println("No Appointments were provided.");
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
                    [5] Check in
                    [6] Search an appointment
                    [7] Quit""");
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
                checkIn();
            } else if (choice.equals("6")) {
                searchAppointment();
            } else if (choice.equals("7")) {
                System.out.println("Bye! Have a great day!");
                break;
            } else {
                System.out.println("That is not a valid choice.");
            }
        }
    }

    // Adds an appointment
    public static void addAppointment() {
        System.out.print("Name: ");
        String nameInput = keyboardInput.nextLine();
        System.out.print("Animal Illness: ");
        String illnessInput = keyboardInput.nextLine();
        String timeChoice;
        while (true) {
            System.out.println("Please choose an available time. Available times: [11:00], [12:00], [1:00], " +
                    "[2:00], [3:00], [4:00]");
            System.out.print("> ");
            timeChoice = keyboardInput.nextLine();
            if (timesAvailable(timeChoice) > 1) {
                System.out.println("That time slot is full please select another time slot.");
            } else if (timeChoice.equalsIgnoreCase("11:00") || (timeChoice.equalsIgnoreCase("12:00") ||
                    timeChoice.equalsIgnoreCase("1:00") || timeChoice.equalsIgnoreCase("2:00") ||
                    timeChoice.equalsIgnoreCase("3:00") || timeChoice.equalsIgnoreCase("4:00"))) {
                break;
            } else {
                System.out.println("Invalid time!");
            }
        }
        Appointment appointment = new Appointment(nameInput, null, illnessInput, timeChoice, addAnimal());
        scheduledAppointments.add(appointment);
    }

    // Adds an animal
    public static Animal addAnimal() {
        System.out.println("---------------");
        System.out.println("Animal Details");
        System.out.println("---------------");
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
            if (typeOfAnimalInput.equalsIgnoreCase("cat") ||
                    (typeOfAnimalInput.equalsIgnoreCase("dog"))) {
                break;
            } else {
                System.out.println("Please choose a valid animal.");
            }
        }
        String genderInput;
        while (true) {
            System.out.print("Gender: ");
            genderInput = keyboardInput.nextLine();
            if (genderInput.equalsIgnoreCase("M") ||
                    genderInput.equalsIgnoreCase("F")) {
                break;
            } else {
                System.out.println("Please choose a valid gender.");
            }
        }
        System.out.print("(Please put only numbers in this field) Weight: ");
        double weightInput = Double.parseDouble(keyboardInput.nextLine());
        Animal pet = new Animal(ownerNameInput, animalNameInput, breedInput,
                typeOfAnimalInput, genderInput, weightInput);
        animals.add(pet);
        return pet;
    }

    // Checks for available times
    public static int timesAvailable(String newTime) {
        int counter = 0;
        for (Appointment a : scheduledAppointments) {
            if (a.time.equals(newTime)) {
                counter++;
            }
        }
        return counter;
    }

    // Lists all animals in the system
    public static void listAllAnimals() {
        for (Animal a : animals) {
            System.out.println("Owners Name: " + a.ownerName + " | Pet Name: " + a.petName + " | Gender: "
                    + a.gender + " | Breed: " + a.breed + " | Animal: " + a.typeOfAnimal + " | Weight: " + a.weight);
        }
    }

    // Lists all the appointments in the system
    public static void listAllAppointments() {
        for (Appointment app : scheduledAppointments) {
            System.out.println("Name: " + app.name + " | Status: " + app.status + " | Animal Illness: " +
                    app.illness + " | Time: " + app.time + " | Animal: " + app.animal.petName);
        }
    }

    // Deletes an appointment from the system
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
        scheduledAppointments.remove(removeAppointment);
        if (!found) {
            System.out.println("Appointment not found!");
        }
    }

    // Updates an appointment in the system
    public static void updateAppointments() {
        boolean found = false;
        System.out.print("Name: ");
        String nameInput = keyboardInput.nextLine();
        for (Appointment app : scheduledAppointments) {
            if (nameInput.equalsIgnoreCase(app.name)) {
                System.out.print("Animal Illness: ");
                String illnessInput = keyboardInput.nextLine();
                System.out.print("Time: ");
                String timeInput = keyboardInput.nextLine();
                app.update(illnessInput, timeInput);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Person not found!");
        }
    }

    // Changes the status of an appointment within the system
    public static void checkIn() {
        boolean found = false;
        System.out.print("Name: ");
        String nameInput = keyboardInput.nextLine();
        for (Appointment app : scheduledAppointments) {
            if (nameInput.equalsIgnoreCase(app.name)) {
                System.out.print("Status: ");
                String statusInput = keyboardInput.nextLine();
                app.updateStatus(statusInput);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Person not found!");
        }
    }

    // Searches for a specific appointment(s) with times
    public static void searchAppointment() {
        System.out.print("Appointment times: ");
        String nameInput = keyboardInput.nextLine();
        boolean found = false;
        for (Appointment app : scheduledAppointments) {
            if (nameInput.equalsIgnoreCase(app.time)) {
                System.out.println("Name: " + app.name + " | Status: " + app.status + " | Animal Illness: " +
                        app.illness + " | Time: " + app.time + " | Animal: " + app.animal.petName);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Appointments found with that time.");
        }
    }
}