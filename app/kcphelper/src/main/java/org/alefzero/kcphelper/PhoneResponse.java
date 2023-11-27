package org.alefzero.kcphelper;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PhoneResponse {

    @XmlElement
    private String username;
    @XmlElement
    private String phone;

    public PhoneResponse(String username, String phone) {
        setUsername(username);
        setPhone(phone);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PhoneResponse [username=" + username + ", phone=" + phone + "]";
    }

}
