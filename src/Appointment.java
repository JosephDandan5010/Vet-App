public class Appointment {
    String name;
    String status;
    String illness;
    String time;
    Animal animal;

    public Appointment(String name, String status, String illness, String time, Animal animal) {
        this.name = name;
        this.status = status;
        this.illness = illness;
        this.time = time;
        this.animal = animal;
    }

    public void update(String status, String illness, String time) {
        this.status = status;
        this.illness = illness;
        this.time = time;
    }
}