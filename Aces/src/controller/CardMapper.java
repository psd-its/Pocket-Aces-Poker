/**
 * Controller class to handle the mapping of user's hand to images of their
 * cards, to be displayed.
 * 
 * @author Mathew Harrington
 */

package controller;

import java.io.File;

public class CardMapper
{
    private String assetPath = "src/assets/";
    private String defaultImgPath = assetPath + "CARDBACK.png";
    
    public CardMapper()
    {  
    }
    
    /**
     * Maps the user's hand string into a filepath (string) pointing to the 
     * corresponding image. 
     * 
     * @param s The user's hand, player.getHand.ToString()
     * @return String Path to the image or path to default image if something
     * went wrong.
     */
    public String map(String s)
    {
        File f;
        
        // first split string
        String[] values = splitString(s);
        
        // lookup in assets the card required
        String assetName = buldPath(values[0], values[2]);
        if(assetName != null)
        {
            // might need to check here incase card wasn't found?
            String path = this.assetPath + assetName;
            
            f = new File(path);
            
            if(f.exists() && !f.isDirectory())
            {
                return path;
            }
        }
        return defaultImgPath;
    }
    
    /**
     * Overloaded map method, this takes an additional parameter 'size'. A way
     * to obtain a different size image, e.g *_2X.png
     * 
     * @param s The user's hand, player.getHand.ToString()
     * @param size The size requested e.g 2X 
     * @return String Path to the image or path to default image if something
     * went wrong.
     */
    public String map(String s, String size)
    {
        String basePath = map(s);
        
        // quick check to make sure we don't add to an image path that isn't valid
        if(!basePath.equals(defaultImgPath))
        {
            String fullPath = new StringBuffer(basePath).insert(basePath.length()-4, size).toString();
            return fullPath;
        }
        return basePath;
    }
    
    /**
     * Breaks string into an array on the space character.
     * 
     * @param s The string to split.
     * @return String array containing the tokens.
     */
    private String[] splitString(String s)
    {
        // ssplit on space
        String[] split = s.split(" ");
        return split;
    }
    
    /**
     * Turns the string representing face and suit into a useable file path and
     * returns it.
     * 
     * @param face String representing the face of the card.
     * @param suit String representing the suit of the card.
     * @return The complete filename of the card requested, null if it cannot be found.
     */
    private String buldPath(String face, String suit)
    {
        String sFace;
        
        switch(face)
        {
            case "TWO" :
                sFace = "2";
                break;
            case "THREE" :
                sFace = "3";
                break;
            case "FOUR" :
                sFace = "4";
                break;
            case "FIVE" :
                sFace = "5";
                break;
            case "SIX" :
                sFace = "6";
                break;
            case "SEVEN" :
                sFace = "7";
                break;
            case "EIGHT" :
                sFace = "8";
                break;
            case "NINE" :
                sFace = "9";
                break;
            case "TEN" :
                sFace = "10";
                break;
            case "JACK" :
                sFace = "J";
                break;
            case "QUEEN" :
                sFace = "Q";
                break;
            case "KING" :
                sFace = "K";
                break;
            case "ACE" :
                sFace = "A";
                break;
            default :
                sFace = null;
        }
        
        if(sFace != null)
        {
            return sFace + "_" + suit + ".png";
        }
        
        return null;
    }
}
