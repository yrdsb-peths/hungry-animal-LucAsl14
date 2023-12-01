import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spit extends SmoothMover
{
    int vel, dir;
    boolean isNew = true;
    public Spit(int velocity, int direction){
        vel = velocity;
        dir = direction;
        setRotation(dir);
    }
    /**
     * Act - do whatever the Spit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MyWorld world = (MyWorld)getWorld();
        if(isTouching(Enemy.class)
         ||isTouching(Enemy2.class)){
           removeTouching(Enemy.class);
           removeTouching(Enemy2.class);
           world.counter.add(1);
           getWorld().removeObject(this);
           return;
        }
        if(Greenfoot.isKeyDown("SPACE")&&isNew){
            return;
        }
        homeToDirection(world, world.elephant);
        isNew = false;
        move(vel);
        if(isAtEdge()){
             getWorld().removeObject(this);
        }
    }
    
    private void homeToDirection(MyWorld world){
        if(getRotation()!=world.elephant.getRotation()){
            int turnSpeed;
            int rotationDiff = getRotation()-world.elephant.getRotation();
            if(rotationDiff>180
             ||(rotationDiff<0 && rotationDiff>-180)){
                 turnSpeed = 2;
             } else {
                 turnSpeed = -2;
             }
              
            turn(turnSpeed);
        }        
    }
}
