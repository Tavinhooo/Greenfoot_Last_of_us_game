import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Score extends Actor
{
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    private int value;
    private int target;
    private String prefix;

    public Score()
    {
        this("");
    }

    /**
     * Create a new counter, initialized to 0 with a black background.
     */
    public Score(String prefix)
    {
        background = new GreenfootImage(50, 50);  // Creates a black square of fixed size
        background.setColor(Color.BLACK);
        background.fill();  // Fill the background with black
        value = 0;
        target = 0;
        this.prefix = prefix;
        updateImage();
    }
    
    public void act() 
    {
        if (value < target) {
            value++;
            updateImage();
        }
        else if (value > target) {
            value--;
            updateImage();
        }
    }

    public void add(int score)
    {
        target += score;
    }

    public int getValue()
    {
        return target;
    }

    public void setValue(int newValue)
    {
        target = newValue;
        value = newValue;
        updateImage();
    }
    
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
        updateImage();
    }

    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(background);
        GreenfootImage text = new GreenfootImage(prefix + value, 22, Color.WHITE, transparent);  // White text

        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }

        image.drawImage(text, (image.getWidth() - text.getWidth()) / 2, 
                        (image.getHeight() - text.getHeight()) / 2);
        setImage(image);
    }
}
