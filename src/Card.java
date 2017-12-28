
public class Card {
	private String name;
	private char strength;
	
	Card(String name, char strength){
		this.name = name;
		this.strength = strength;
	}
	
    public String getName(){
    		return this.name;
    }
    
    public char getStrength(){
    	return this.strength;
    }
    
    @Override
    public String toString(){
    		return name + "(" + strength + ")";
    }
	
}
