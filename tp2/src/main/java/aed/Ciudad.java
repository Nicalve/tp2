package aed;

public class Ciudad {
    private final int id;
    private int earnings;
    private int losses;
    private int superavit;

    public Ciudad(int id){
        this.id = id;
        this.earnings = 0;
        this.losses = 0;
        this.superavit = 0;
    } //? Son asignaciones, por lo que la complejidad es O(1);

    public void addEarnings(int e){
        this.earnings = this.earnings + e; //? O(1);
    }

    public void addLosses(int l){
        this.losses = this.losses + l; //? O(1);
    }

    public int getId(){
        return this.id; //? O(1);
    }

    public int getEarnings(){
        return this.earnings; //? O(1);
    }

    public int getLosses(){
        return this.losses; //? O(1);
    }

    public int getSuperavit(){
        this.superavit = this.earnings - this.losses; //? O(1);
        return this.superavit; //? O(1);
    }
}