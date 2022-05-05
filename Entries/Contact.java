package contacts.Entries;

import contacts.Constants.Gender;

import java.awt.geom.Area;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Scanner;

public class Contact extends PhoneBookEntry {

    private String surname;
    private Gender gender;
    private LocalDate birthday;

    public Contact() {
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getLongName() {
        return super.getName() + " " + this.surname;
    }

    @Override
    public String toString() {
        String genderString;
        if(gender == Gender.UNDEFINED){
            genderString = "[no data]";
        } else {
            genderString = gender.toString();
        }

        String birth;
        if(birthday == null){
            birth = "[no data]";
        } else {
            birth = birthday.toString();
        }



        StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(super.getName()).append("\n")
                .append("Surname: ").append(surname).append("\n")
                .append("Birth date: ").append(birth).append("\n")
                .append("Gender: ").append(genderString).append("\n")
                .append("Number: ").append(super.getPhone()).append("\n")
                .append("Time created: ").append(super.getTimeCreated().truncatedTo(ChronoUnit.MINUTES)).append("\n")
                .append("Time last edit: ").append(super.getTimeUpdated().truncatedTo(ChronoUnit.MINUTES));
        return builder.toString();
    }
}


