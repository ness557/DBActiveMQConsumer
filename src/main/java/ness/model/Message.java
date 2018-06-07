package ness.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mes_gen")
    @SequenceGenerator(name = "mes_gen", sequenceName = "mes_seq", initialValue = 1)
    private int id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "text")
    private String text;

    public Message() {
    }

    public Message(LocalDateTime dateTime, String text) {
        this.dateTime = dateTime;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", text='" + text + '\'' +
                '}';
    }
}
