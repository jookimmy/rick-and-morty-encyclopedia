package nojoo.com.rickandmortyencyclopedia;

import android.graphics.drawable.Drawable;

public class Item
{
    String title;
    String imgurl;
    String id;

    // Empty Constructor
    public Item()
    {

    }

    // Constructor
    public Item(String title, String imgurl, String id)
    {
        super();
        this.title = title;
        this.imgurl = imgurl;
        this.id = id;
    }

    // Getter and Setter Method
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getImage()
    {
        return imgurl;
    }

    public void setImage(String imgurl)
    {
        this.imgurl = imgurl;
    }
    public void setID(String id) {
        this.id = id;
    }
    public String getID() {
        return this.id;
    }


}
