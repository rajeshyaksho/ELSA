package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Computer implements Saveable {
    private String name;
    private String model;
    private ArrayList<Option> options;

    public Computer(String name, String model) {
        this.name = name;
        this.model = model;
        this.options = new ArrayList<>();
    }

    public Computer(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.model = br.readLine();
        int size = Integer.parseInt(br.readLine());
        this.options = new ArrayList<>();
        while (size-- > 0) {
            this.options.add(new Option(br));
        }
    }

    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(model + '\n');
        bw.write(options.size() + '\n');
        for (Option option : options) {
            option.save(bw);
        }
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public long cost() {
        long sum = 0;
        for (Option option : options) {
            sum += option.cost;
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(name + " (" + model + ")\n");
        for (Option option : options) {
            result.append("\t").append(option.toString());
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Computer)) return false;
        Computer c = (Computer) o;
        return this.toString().equals(c.toString());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + ((options == null) ? 0 : options.hashCode());
        return result;
    }
}
