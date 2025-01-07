package exercise;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checkstringprivate {
    public static final String setCccd = null;
    private String cccd;
    private String mk;
    private String email;

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String nhapCccd(String Cccd) {
        Pattern nhap = Pattern.compile("[0-9]{12}");
        Matcher kt = nhap.matcher(Cccd);
        if (kt.matches()) {
            return Cccd;
        } else {
            return "ERROR";
        }
    }

    public String nhapMk(String mk) {
        Pattern nhap = Pattern.compile("[0-9a-zA-Z]{6,}");
        Matcher kt = nhap.matcher(mk);
        if (kt.matches()) {
            return mk;
        } else {
            return "ERROR";
        }
    }

    public String nhapEmail(String email) {
        Pattern nhap = Pattern.compile("^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]{2,}$");
        Matcher kt = nhap.matcher(email);
        if (kt.matches()) {
            return email;
        } else {
            return "ERROR";
        }
    }
}
