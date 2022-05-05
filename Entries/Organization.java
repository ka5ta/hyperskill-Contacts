package contacts.Entries;

import contacts.Constants.Gender;

import java.time.temporal.ChronoUnit;

public class Organization extends PhoneBookEntry {

    private String address;

    public Organization(){}

    @Override
    public String getLongName() {
        return super.getName();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Organization name: ").append(super.getName()).append("\n")
                .append("Address: ").append(address).append("\n")
                .append("Number: ").append(super.getPhone()).append("\n")
                .append("Time created: ").append(super.getTimeCreated().truncatedTo(ChronoUnit.MINUTES)).append("\n")
                .append("Time last edit: ").append(super.getTimeUpdated().truncatedTo(ChronoUnit.MINUTES));
        return builder.toString();
    }

}
