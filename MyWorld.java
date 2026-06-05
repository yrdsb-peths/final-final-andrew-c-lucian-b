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
        if (gameOver == false)
        {
            checkGameOver();
        }
    }

    public void itemCollected()
    {
        collected++;

        if (collected >= 5 && level == 1)
        {
            level = 2;
            grandma.setLevel(2);
        }

        if (collected >= 10 && level == 2)
        {
            level = 3;
            grandma.setLevel(3);
        }

        spawnItem();
        updateHUD();
    }

    private void spawnItem()
    {
        int x = Greenfoot.getRandomNumber(getWidth() - 100) + 50;
        int y = Greenfoot.getRandomNumber(getHeight() - 100) + 50;

        int chance = Greenfoot.getRandomNumber(100);

        if (chance < 20)
        {
            addObject(new Heart(), x, y);
        }
        else
        {
            addObject(new Cake(), x, y);
        }
    }

    private void updateHUD()
    {
        showText("Collected: " + collected, 100, 30);
        showText("Level: " + level, 80, 60);
    }

    private void checkGameOver()
    {
        if (getObjects(Fly.class).isEmpty())
        {
            gameOver = true;
            showText("GAME OVER", getWidth() / 2, getHeight() / 2);
        }
    }
}