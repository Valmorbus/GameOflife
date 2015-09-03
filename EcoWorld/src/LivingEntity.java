import java.awt.Point;
import java.util.List;

import javax.swing.ImageIcon;


public abstract class LivingEntity implements Entity {
	
	private static final ImageIcon image = new ImageIcon("src\\img\\unknown.gif");
	protected Pasture pasture;
	protected int spawn;
	protected int resetSpawn;
	protected boolean moving; 
	protected boolean notDead;
	
	public abstract Entity createNew(); //implementeras f�r breed av klasser som �rver
	
	public boolean canEat(Entity otherEntity)
	{
	return false;	
	}

	public void breed()
	{
		spawn--; //minskar 1 f�r varje tick
		if(spawn == 0) 
        	{
            Point neighbour =(Point)getRandomMember(pasture.getFreeNeighbours(this));
            	if(neighbour != null)
            	{
            	pasture.addEntity(createNew(), neighbour);	//skapar ny vid angr�nsande position            	
            	}
            	this.spawn = this.resetSpawn;
        	}
   	}
	public void tick() 
    {    
			if(this.notDead)
				breed();      
			else	
				pasture.removeEntity(this);	//om upp�ten
					
	}
	
	public ImageIcon getImage() 
    {
        return image;
    }
	
	public abstract boolean isCompatible(Entity otherEntity);
	
	/**
     * A general method for grabbing a random element from a
     * list. Does it belong in this class?
     */
    protected static <X> X getRandomMember(List<X> c) {
        if (c.size() == 0)
            return null;
        
        int n = (int)(Math.random() * c.size());
        
        return c.get(n);
    }	
	
}
