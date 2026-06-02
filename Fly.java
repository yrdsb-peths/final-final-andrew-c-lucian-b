import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fly here.
 * 
 * @Lucian and Andrew
 * @May 20, 2026
 */
public class Fly extends Actor
{
    private int speed = 4;

    private int health = 100;
    private int maxHealth = 100;
    private int invulnerabilityTimer = 0;

    private GreenfootImage[] flyRight;
    private GreenfootImage[] flyVertical;

    private int imageIndex = 0;
    private int animationCounter = 0;

    private boolean facingRight = true;
    private boolean facingDown = true;

    private GreenfootImage currentFrame;

    public Fly()
    {
        loadAnimations();
        currentFrame = flyRight[0];
        setImage(currentFrame);
    }

    public void act()
    {
        if (invulnerabilityTimer > 0)
        {
            invulnerabilityTimer--;
        }

        moveFly();

        if (getWorld() == null)
        {
            return;
        }

        checkCatch();

        if (getWorld() == null)
        {
            return;
        }

        collectCake();

        if (getWorld() == null)
        {
            return;
        }

        stayInBounds();

        drawHealthBar();
    }

    public void takeDamage(int damage)
    {
        if (invulnerabilityTimer > 0)
        {
            return;
        }

        health = health - damage;

        if (health < 0)
        {
            health = 0;
        }

        invulnerabilityTimer = 120;

        if (health <= 0)
        {
            if (getWorld() != null)
            {
                getWorld().removeObject(this);
            }
        }
    }

    private void drawHealthBar()
    {
        GreenfootImage img = new GreenfootImage(currentFrame);

        int barWidth = 40;
        int barHeight = 6;

        int healthWidth = (health * barWidth) / maxHealth;

        img.setColor(Color.RED);
        img.fillRect(
            (img.getWidth() - barWidth) / 2,
            0,
            barWidth,
            barHeight
        );

        img.setColor(Color.GREEN);
        img.fillRect(
            (img.getWidth() - barWidth) / 2,
            0,
            healthWidth,
            barHeight
        );

        setImage(img);
    }

    private void checkCatch()
    {
        if (isTouching(Grandma.class))
        {
            takeDamage(50);
        }
    }

    private void collectCake()
    {
        if (isTouching(Cake.class))
        {
            World world = getWorld();

            if (world == null)
            {
                return;
            }

            Actor cake = getOneIntersectingObject(Cake.class);

            if (cake != null)
            {
                world.removeObject(cake);

                MyWorld myWorld = (MyWorld) world;
                myWorld.cakeCollected();
            }
        }
    }

    private void moveFly()
    {
        double dx = 0;
        double dy = 0;

        if (Greenfoot.isKeyDown("w"))
        {
            dy = dy - 1;
            facingDown = false;
        }

        if (Greenfoot.isKeyDown("s"))
        {
            dy = dy + 1;
            facingDown = true;
        }

        if (Greenfoot.isKeyDown("a"))
        {
            dx = dx - 1;
            facingRight = false;
        }

        if (Greenfoot.isKeyDown("d"))
        {
            dx = dx + 1;
            facingRight = true;
        }

        if (dx != 0 || dy != 0)
        {
            double length = Math.sqrt(dx * dx + dy * dy);

            dx = (dx / length) * speed;
            dy = (dy / length) * speed;

            setLocation(
                (int)(getX() + dx),
                (int)(getY() + dy)
            );

            if (Math.abs(dx) > Math.abs(dy))
            {
                animateHorizontal();
            }
            else
            {
                animateVertical();
            }
        }
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

    private void loadAnimations()
    {
        flyRight = new GreenfootImage[4];
        flyVertical = new GreenfootImage[4];

        for (int i = 0; i < 4; i++)
        {
            flyRight[i] = new GreenfootImage("fly_move/fly_horizontal" + (i + 1) + ".png");
            flyVertical[i] = new GreenfootImage("fly_move/fly_vertical" + (i + 1) + ".png");

            flyRight[i].scale(flyRight[i].getWidth() / 5, flyRight[i].getHeight() / 5);
            flyVertical[i].scale(flyVertical[i].getWidth() / 5, flyVertical[i].getHeight() / 5);
        }
    }

    private void animateVertical()
    {
        animationCounter++;

        if (animationCounter % 5 == 0)
        {
            imageIndex = (imageIndex + 1) % flyVertical.length;

            GreenfootImage img = new GreenfootImage(flyVertical[imageIndex]);

            if (facingDown == true)
            {
                img.rotate(180);
            }

            currentFrame = img;
        }
    }

    private void animateHorizontal()
    {
        animationCounter++;

        if (animationCounter % 5 == 0)
        {
            imageIndex = (imageIndex + 1) % flyRight.length;

            GreenfootImage img = new GreenfootImage(flyRight[imageIndex]);

            if (facingRight == false)
            {
                img.mirrorHorizontally();
            }

            currentFrame = img;
        }
    }
}