import greenfoot.*;

public class Fly extends Actor
{
    
    private int speed = 4;

    // Animation arrays
    private GreenfootImage[] flyRight;
    private GreenfootImage[] flyVertical;

    // Animation variables
    private int frame = 0;
    private int animationCounter = 0;

    // Direction
    private boolean facingRight = true;
    private boolean facingDown = true;

    public Fly()
    {
        loadAnimations();
    }

    public void act()
    {
        moveFly();
    }

    public void loadAnimations()
    {
        // Horizontal flying animation
        flyRight = new GreenfootImage[4];
        flyRight[0] = new GreenfootImage("fly_horizontal_1.png");
        flyRight[1] = new GreenfootImage("fly_horizontal_2.png");
        flyRight[2] = new GreenfootImage("fly_horizontal_3.png");
        flyRight[3] = new GreenfootImage("fly_horizontal_4.png");
    
        // Vertical flying animation
        flyVertical = new GreenfootImage[4];
        flyVertical[0] = new GreenfootImage("fly_vertical_1.png");
        flyVertical[1] = new GreenfootImage("fly_vertical_2.png");
        flyVertical[2] = new GreenfootImage("fly_vertical_3.png");
        flyVertical[3] = new GreenfootImage("fly_vertical_4.png");
    
        // ✅ SCALE ALL IMAGES TO 1/3 SIZE
        for (int i = 0; i < flyRight.length; i++)
        {
            flyRight[i].scale(flyRight[i].getWidth()/3, flyRight[i].getHeight()/3);
        }
    
        for (int i = 0; i < flyVertical.length; i++)
        {
            flyVertical[i].scale(flyVertical[i].getWidth()/3, flyVertical[i].getHeight()/3);
        }
    }

    public void moveFly()
    {
        boolean moving = false;
    
        boolean up = Greenfoot.isKeyDown("w");
        boolean down = Greenfoot.isKeyDown("s");
        boolean left = Greenfoot.isKeyDown("a");
        boolean right = Greenfoot.isKeyDown("d");
    
        int dx = 0;
        int dy = 0;
    
        if (up) 
        {
            dy -= speed;
            facingDown = false;
        }
        if (down) 
        {
            dy += speed;
            facingDown = true;
        }
        if (left)
        {
            dx -= speed;
            facingRight = false;
        }
        if (right)
        {
            dx += speed;
            facingRight = true;
        }
    
        // Move once
        if (dx != 0 || dy != 0)
        {
            setLocation(getX() + dx, getY() + dy);
            moving = true;
    
            // ✅ PRIORITY: horizontal animation if moving sideways
            if (dx != 0)
            {
                animateHorizontal();
            }
            else
            {
                animateVertical();
            }
        }
    
        // Idle
        if (!moving)
        {
            setImage(flyRight[0]);
        }
    }
    public void animateVertical()
    {
        animationCounter++;
    
        if (animationCounter % 5 == 0)
        {
            frame++;
            if (frame >= flyVertical.length)
            {
                frame = 0;
            }
    
            GreenfootImage image = new GreenfootImage(flyVertical[frame]);
    
            // ✅ Rotate instead of mirror
            if (facingDown)
            {
                image.rotate(180);
            }
    
            setImage(image);
        }
    }

    public void animateHorizontal()
    {
        animationCounter++;

        // Controls animation speed
        if (animationCounter % 5 == 0)
        {
            frame++;

            if (frame >= flyRight.length)
            {
                frame = 0;
            }

            GreenfootImage image = new GreenfootImage(flyRight[frame]);

            // Flip image if moving left
            if (!facingRight)
            {
                image.mirrorHorizontally();
            }

            setImage(image);
        }
    }
}