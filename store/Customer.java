package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Customer implements Comparable<Customer>, Saveable {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        if (!(email.matches("(?i)^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"))) {
            throw new IllegalArgumentException("The email provided is incorrect");
        }
    }

    public Customer(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.email = br.readLine();
    }

    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(email + '\n');
    }

    @Override
    public String toString() {
        return name + " (" + email + ")\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true; // An object is equal to itself

        if (!(o instanceof Customer)) return false; // A different type is not equal

        Customer f = (Customer) o;
        return (name.equals(f.name)) && (email.equals(f.email)); // Compare relevant fields

    }

    @Override
    public int compareTo(Customer other) {
        int nameComparison = name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        } else {
            return email.compareTo(other.email);
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
