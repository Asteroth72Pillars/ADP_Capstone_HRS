package za.ac.cput.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDateTime inDate;
    private LocalDateTime outDate;

    public Rental() {
    }

    public Rental(int id, User user, Room room, LocalDateTime inDate, LocalDateTime outDate) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.inDate = inDate;
        this.outDate = outDate;
    }

    public Rental(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.room = builder.room;
        this.inDate = builder.inDate;
        this.outDate = builder.outDate;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getInDate() {
        return inDate;
    }

    public void setInDate(LocalDateTime inDate) {
        this.inDate = inDate;
    }

    public LocalDateTime getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDateTime outDate) {
        this.outDate = outDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return getId() == rental.getId() &&
                Objects.equals(getUser(), rental.getUser()) &&
                Objects.equals(getRoom(), rental.getRoom()) &&
                Objects.equals(getInDate(), rental.getInDate()) &&
                Objects.equals(getOutDate(), rental.getOutDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getRoom(), getInDate(), getOutDate());
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", user=" + user +
                ", room=" + room +
                ", inDate=" + inDate +
                ", outDate=" + outDate +
                '}';
    }

    public static class Builder {
        private int id;
        private User user;
        private Room room;
        private LocalDateTime inDate;
        private LocalDateTime outDate;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setRoom(Room room) {
            this.room = room;
            return this;
        }

        public Builder setInDate(LocalDateTime inDate) {
            this.inDate = inDate;
            return this;
        }

        public Builder setOutDate(LocalDateTime outDate) {
            this.outDate = outDate;
            return this;
        }

        public Rental build() {
            return new Rental(this);
        }

        public Builder copy(Rental rental) {
            this.id = rental.id;
            this.user = rental.user;
            this.room = rental.room;
            this.inDate = rental.inDate;
            this.outDate = rental.outDate;
            return this;
        }
    }
}
