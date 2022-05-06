package contacts.Entries;

import contacts.Constants.Gender;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Organization extends PhoneBookEntry {

    private final java.util.UUID UUID;
    private String address;

    public Organization(){
        UUID = randomUUID();
    }

    @Override
    public String getLongName() {
        return super.getName();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.util.UUID getUUID() {
        return UUID;
    }

    @Override
    public String toString() {

        StringBuilder builder;
        builder = new StringBuilder().append("Organization name: ").append(super.getName()).append("\n")
                .append("Address: ").append(address).append("\n")
                .append("Number: ").append(super.getPhone()).append("\n")
                .append("Time created: ").append(super.getTimeCreated().truncatedTo(ChronoUnit.MINUTES)).append("\n")
                .append("Time last edit: ").append(super.getTimeUpdated().truncatedTo(ChronoUnit.MINUTES));
        return builder.toString();
    }
}
