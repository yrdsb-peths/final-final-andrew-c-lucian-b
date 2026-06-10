import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Grandma here.
 * 
 * @Lucian and Andrew
 * @May 20, 2026
 */
public class Grandma extends Actor
{
    private GreenfootImage[] grandmaRight = new GreenfootImage[4];
    private GreenfootImage[] grandmaLeft = new GreenfootImage[4];

    private String facing = "right";

    private SimpleTimer animationTimer = new SimpleTimer();
    private int imageIndex = 0;

    private int speed = 1;
    private int throwCooldown = 0;
    private int throwDelay = 120;

    private int level = 1;

    private boolean frozen = false;
    private int freezeTimer = 0;

    public Grandma()
    {
        for (int i = 0; i < grandmaRight.length; i++)
        {
            grandmaRight[i] = new GreenfootImage("grandma_walk/grandma_walk" + (i + 1) + ".png");
            grandmaRight[i].scale(125, 125);

            grandmaLeft[i] = new GreenfootImage("grandma_walk/grandma_walk" + (i + 1) + ".png");
            grandmaLeft[i].mirrorHorizontally();
            grandmaLeft[i].scale(125, 125);
        }

        setImage(grandmaRight[0]);
        animationTimer.mark();
    }

    public void act()
    {
        if (frozen)
        {
            freezeTimer--;

            if (freezeTimer <= 0)
            {
                frozen = false;
            }

            return;
        }

        chaseFly();
        throwProjectile();
        animateGrandma();
        stayInBounds();
    }

    public void setLevel(int level)
    {
        this.level = level;

        speed = Math.min(3, 1 + (level - 1) / 3);

        throwDelay = Math.max(40, 120 - (level - 1) * 15);
    }

    public void freeze(int seconds)
    {
        frozen = true;
        freezeTimer = seconds * 60;
    }

    private void chaseFly()
    {
        if (getWorld().getObjects(Fly.class).isEmpty())
        {
            return;
        }

        Fly fly = (Fly)getWorld().getObjects(Fly.class).get(0);

        int dx = fly.getX() - getX();
        int dy = fly.getY() - getY();

        if (Math.abs(dx) > Math.abs(dy))
        {
            if (dx > 0)
            {
                setLocation(getX() + speed, getY());
                facing = "right";
            }
            else
            {
                setLocation(getX() - speed, getY());
                facing = "left";
            }
        }
        else
        {
            if (dy > 0)
            {
                setLocation(getX(), getY() + speed);
            }
            else
            {
                setLocation(getX(), getY() - speed);
            }
        }
    }

    private void throwProjectile()
    {
        if (throwCooldown > 0)
        {
            throwCooldown--;
            return;
        }

        if (getWorld().getObjects(Fly.class).isEmpty())
        {
            return;
        }

        Fly fly = (Fly)getWorld().getObjects(Fly.class).get(0);

        double dx = fly.getX() - getX();
        double dy = fly.getY() - getY();

        double length = Math.sqrt(dx * dx + dy * dy);

        if (length == 0)
        {
            return;
        }

        double dirX = dx / length;
        double dirY = dy / length;

        Projectile proj;

        if (level == 1)
        {
            proj = new Projectile(dirX, dirY, "shoe", 20, 4);
            proj.setScale(40);
        }
        else if (level == 2)
        {
            proj = new Projectile(dirX, dirY, "yarn", 35, 4);
            proj.setScale(55);
        }
        else if (level == 3)
        {
            proj = new Projectile(dirX, dirY, "comb", 50, 5);
            proj.setScale(65);
        }
        else if (level == 4)
        {
            proj = new Projectile(dirX, dirY, "drugs", 65, 5);
            proj.setScale(80);
        }
        else
        {
            proj = new Projectile(dirX, dirY, "purse", 80, 6);
            proj.setScale(95);
        }

        getWorld().addObject(proj, getX(), getY());

        throwCooldown = throwDelay;
    }

    private void animateGrandma()
    {
        if (animationTimer.millisElapsed() < 120)
        {
            return;
        }

        animationTimer.mark();

        if (facing.equals("right"))
        {
            setImage(grandmaRight[imageIndex]);
        }
        else
        {
            setImage(grandmaLeft[imageIndex]);
        }

        imageIndex = (imageIndex + 1) % grandmaRight.length;
    }

    private void stayInBounds()
    {
        if (getX() < 0)
        {
            setLocation(0, getY());
        }

        if (getX() > getWorld().getWidth() - 1)
        {
            setLocation(getWorld().getWidth() - 1, getY());
        }

        if (getY() < 0)
        {
            setLocation(getX(), 0);
        }

        if (getY() > getWorld().getHeight() - 1)
        {
            setLocation(getX(), getWorld().getHeight() - 1);
        }
    }
}