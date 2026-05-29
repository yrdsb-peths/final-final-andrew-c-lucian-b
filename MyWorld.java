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

    private int cakesEaten = 0;
    private int level = 1;

    private Grandma grandma;

    public MyWorld()
    {
        super(900, 600, 1, false);

        grandma = new Grandma();
        addObject(grandma, 200, 200);

        Fly fly = new Fly();
        addObject(fly, 400, 300);

        spawnCake();
        updateHUD();
    }

    public void act()
    {
        if (gameOver == false)
        {
            checkGameOver();
        }
    }

    public void cakeCollected()
    {
        cakesEaten++;

        if (cakesEaten >= 5 && level == 1)
        {
            level = 2;
            grandma.setLevel(2);
        }

        if (cakesEaten >= 10 && level == 2)
        {
            level = 3;
            grandma.setLevel(3);
        }

        spawnCake();
        updateHUD();
    }

    private void spawnCake()
    {
        int x = Greenfoot.getRandomNumber(getWidth() - 100) + 50;
        int y = Greenfoot.getRandomNumber(getHeight() - 100) + 50;

        Cake cake = new Cake();
        addObject(cake, x, y);
    }

    private void updateHUD()
    {
        showText("Cakes: " + cakesEaten, 80, 30);
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