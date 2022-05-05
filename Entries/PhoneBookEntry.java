package contacts.Entries;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PhoneBookEntry {
    private String name;
    private String phone = "[no number]";

    private final LocalDateTime timeCreated;

    private LocalDateTime timeUpdated;

    public PhoneBookEntry() {
        this.timeCreated = LocalDateTime.now();
    }

    public PhoneBookEntry(String name, String phone, LocalDateTime timeCreated) {
        this.name = name;
        this.timeCreated = timeCreated;
        if (Objects.nonNull(phone) && !phone.isBlank()) {
            verifyPhone(phone);
        }
    }

    public abstract String getLongName();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhone(String phone) {
        if (Objects.nonNull(phone) && !phone.isBlank()) {
            this.phone = verifyPhone(phone);
        }
    }

    public String getPhone() {
        return phone;
    }

    public String verifyPhone(String phoneNumber) {
        if (isValidNumber(phoneNumber)) {
            return phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
    }

    private boolean isValidNumber(String number) {
/*        Pattern pattern = Pattern.compile("[+]?\\w?[\\s-]?(\\([\\w]{2,}\\)|" +
                "[\\w]{2,}[\\s-]\\([\\w]{2,}\\)|" +
                "[\\w]{2,})([\\s-][\\w]{2,})*");*/
        Pattern pattern = Pattern.compile("(^(?<countryCode>\\+\\d+\\s+)?(?<areaCode>\\(?\\w{2,3}\\)?)(?<prefix>[ -]?\\(?\\w{2,3}\\)?)?(?<line>[ -]?\\w{2,4})?(?<direct>[ -]?\\w{2,5})?|(?<stupid>\\+\\(\\w+\\))|[0-9])$", Pattern.CASE_INSENSITIVE);
        //Pattern pattern = Pattern.compile("^\\+?([0-9]{1,3}\\s)?(\\(?[0-9a-z]+\\)?)?([\\s-]?[0-9a-z]{1,4})*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(LocalDateTime timeUpdated) {
        this.timeUpdated = timeUpdated;
    }
}
