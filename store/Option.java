package store;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException; // reports an error reading from a file
public class Option implements Saveable{
    protected String name;
    protected long cost;
    public Option(String name,long cost){
        this.name=name;
        this.cost=cost;
        if(cost<0){
            throw new IllegalArgumentException("The  cost can never be negative");
        }
    }
    public Option (BufferedReader br) throws IOException{
        this.name=br.readLine();
        this.cost=Long.parseLong(br.readLine());
    }
    @Override
    public void save(BufferedWriter bw) throws IOException{
        bw.write("" + name + '\n');        
        bw.write("" + cost + '\n');
    }
    public long cost(){
        return cost;
    }

    @Override
    public String toString(){
        return String.format("%s ($%.2f)\n",name,cost/100.0);
    }
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;             // An object is equal to itself

        if(!(o instanceof Option)) return false;  // A different type is not equal

        Option f = (Option)o;
        return (name.equals(f.name)) && (cost == f.cost);       // Compare relevant fields

    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + Long.hashCode(cost);
        return result;
    }
}