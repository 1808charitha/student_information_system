package entity;

public class Teacher {
    private int teacherId;
    private String firstName;
    private String lastName;
    private String email;
    private String expertise;

    public Teacher(int teacherId, String firstName, String lastName, String email, String expertise) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.expertise = expertise;
    }

    public int getTeacherId() { return teacherId; }
    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getExpertise() { return expertise; }
    public void setExpertise(String expertise) { this.expertise = expertise; }

    @Override
    public String toString() {
        return "Teacher{" + "ID=" + teacherId + ", Name=" + firstName + " " + lastName +
                ", Email=" + email + ", Expertise=" + expertise + '}';
    }
}