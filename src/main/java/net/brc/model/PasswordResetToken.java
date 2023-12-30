package net.brc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "PasswordResetToken")
@Setter
@Getter
@AllArgsConstructor
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 40;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    private Date expiryDate;
    @OneToOne(targetEntity = Reader.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "reader_id")
    private Reader reader;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, Reader reader) {
        super();
        this.token = token;
        this.reader = reader;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes){
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token){
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public static int getExpiration(){
        return EXPIRATION;
    }

    @Override
    public String toString(){
        return "PasswordResetToken [id=" + id + ", token=" + token + ", User=" + reader + ", expiryDate=" + expiryDate + "]";
    }
}
