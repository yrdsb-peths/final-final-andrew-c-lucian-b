import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world.
 * 
 * @Lucian and Andrew
 * @May 20, 2026
 */
public class MyWorld extends World
{
    private boolean gameOver = false;

    private int collected = 0;
    private int level = 1;

    private static int highScore = 0;

    private Grandma grandma;

    public MyWorld()
    {
        super(900, 600, 1, false);
        setBackground("background.png");

        grandma = new Grandma();
        addObject(grandma, 200, 200);

        Fly fly = new Fly();
        addObject(fly, 400, 300);

        spawnItem();
        updateHUD();
    }

    public void act()
    {
        if (!gameOver)
        {
            checkGameOver();
        }
    }

    public void itemCollected()
    {
        collected++;

        if (collected > highScore)
        {
            highScore = collected;
        }

        int newLevel = (collected / 5) + 1;

        if (newLevel > level)
        {
            level = newLevel;
            grandma.setLevel(level);
        }

        spawnItem();
        updateHUD();
    }

    private void spawnItem()
    {
        int x = Greenfoot.getRandomNumber(getWidth() - 100) + 50;
        int y = Greenfoot.getRandomNumber(getHeight() - 100) + 50;

        int chance = Greenfoot.getRandomNumber(100);

        if (chance < 60)
        {
            addObject(new Cake(), x, y);
        }
        else if (chance < 80)
        {
            addObject(new Heart(), x, y);
        }
        else
        {
            addObject(new Frozen(), x, y);
        }
    }

    private void updateHUD()
    {
        showText("Collected: " + collected, 98, 30);
        showText("Level: " + level, 80, 60);
        showText("High Score: " + highScore, 105, 90);
    }

    private void checkGameOver()
    {
        if (getObjects(Fly.class).isEmpty())
        {
            gameOver = true;
            Greenfoot.setWorld(new DeathScreen());
        }
    }
}