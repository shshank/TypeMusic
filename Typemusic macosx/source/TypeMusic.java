import processing.core.*; 
import processing.data.*; 
import processing.opengl.*; 

import arb.soundcipher.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class TypeMusic extends PApplet {

/*TypeMusic is written by
* Shashank Shekhar (shashankjet@gmail.com)
* Aditya Kumar Nayak (adityanayak1991@gmail.com)

Feel free to modify or use the code for your purposes.

http:4errors.com/typemusic
*/



double inst;                //stores the frequency for different instruments
PImage keys, info, instt, beeth;      //stores the images
int vol=500, duration=2;          //volume and duration of the notes to be played
int i;
int first=0;

SoundCipher sc = new SoundCipher(this);

class keyy                  //Class to store values for the keys
{
  char value;
  boolean flag;                //used with setFlag and unsetFlag to prevent repitition during long press
  float keyMIDI;
  keyy(char me, float noteMIDI)        //constructor for data type keyy
  {
    value=me;
    flag=false;
    keyMIDI=noteMIDI;
  }

  public void unsetFlag()
  {  
    flag=false;
  }

  public void setFlag()
  {  
    flag=true;
  }

  public void playKey()
  {
    sc.playNote(keyMIDI, vol, duration);
  }
}

                      //declaring the required keys and
                      //defining notes for each key
keyy k7 = new keyy('7', 61.0f);
keyy k8 = new keyy('8', 63.0f);

keyy k0 = new keyy('0', 66.0f);
keyy s1 = new keyy('-', 68.0f);
keyy s2 = new keyy('=', 70.0f);

keyy kQ = new keyy('Q', 0.0f);
keyy kY = new keyy('Y', 60.0f);
keyy kU = new keyy('U', 62.0f);
keyy kI = new keyy('I', 64.0f);
keyy kO = new keyy('O', 65.0f);
keyy kP = new keyy('P', 64.0f); 
keyy s3 = new keyy('[', 67.0f);
keyy s4 = new keyy(']', 69.0f);

keyy kS = new keyy('S', 49.0f);
keyy kD = new keyy('D', 51.0f);

keyy kG = new keyy('G', 54.0f);
keyy kH = new keyy('H', 56.0f);
keyy kJ = new keyy('J', 58.0f);


keyy kZ = new keyy('Z', 48.0f);
keyy kX = new keyy('X', 50.0f);
keyy kC = new keyy('C', 52.0f);
keyy kV = new keyy('V', 53.0f);
keyy kB = new keyy('B', 55.0f);
keyy kN = new keyy('N', 57.0f);
keyy kM = new keyy('M', 59.0f);

                      //putting all the keyy objects in an array
keyy keyboard[]= {
  k7, k8, k0, s1, s2, 
  kY, kU, kI, kO, kP, s3, s4, 
  kS, kD, kG, kH, kJ, 
  kZ, kX, kC, kV, kB, kN, kM
};

public void setup()
{
  size(800, 550);
  background(0);
  imageMode(CENTER);
  
                        //loading the images and
                        //displaying typemusic.jpg initially
  info = loadImage("typemusic.jpg");
  image(info, width/2, height/2-25);
  keys = requestImage("bg.jpg"); 
  instt = requestImage("instt.jpg");
  beeth = requestImage("beetho.jpg");
 
                        //white text at the bottom
  fill(200);
  textFont(createFont("Arial", 12));
  textAlign(CENTER);
  text("Turn CAPSLOCK ON to Play", width/2, 520);
  text("Press TAB to change Instrument", width/2, 540);
  text("CTRL+A for ABOUT", width/2-300, 540);
  text("CTRL+B for Beethovan", width/2+300, 540);
}



public void draw()
{  
  
  sc.instrument(inst);            //changing the instrument
  
                        //showing the typemusic.jpg for 4sec
  if (first==0)                //when the application starts for the first time
  {
    delay(4000);
    first++;
  }
                      //showing the keys
  image( keys, width/2, height/2-25);


                      //waiting for key inputs
  if (keyPressed) 
  {
    i=0;                  //checking if the key pressed is in the array
    while (keyboard[i].value!=key && i<keyboard.length-1)
    {  
      i++;
    }

                      //if yes play the sound associated with it
    if (keyboard[i].value == key && keyboard[i].flag== false)
    { 
      keyboard[i].playKey(); 
      keyboard[i].setFlag();        //setting the flag so that it doesn't
    }                    //repeat the sound for long press
    
    
    if (key=='a')              //keys to change the instruments
    {
      inst=sc.ACCORDION;
    }

    if (key=='b')
    {
      inst=sc.BAGPIPES;
    }

    if (key=='n')
    {
      inst=sc.BOTTLE_BLOW;
    }

    if (key=='v')
    {
      inst=sc.VIOLIN;
    }

    if (key=='c')
    {
      inst=sc.CLARINET;
    }

    if (key=='g')
    {
      inst=sc.DISTORTED_GUITAR;
    }

    if (key=='d')
    {
      inst=sc.DRUM;
    }

    if (key=='f')
    {
      inst=sc.FLUTE;
    }

    if (key=='w')
    {
      inst=sc.WHISTLE;
    }

    if (key=='t')
    {
      inst=sc.TRUMPET;
    }

    if (key=='s')
    {
      inst=sc.SYNTH_DRUM;
    }
    if (key=='p')
    {
      inst=sc.PIANO;
    }
    
                        //keys to show the images
    if (key==0x09)
    {
      image(instt, width/2, height/2-25);
    }
    if (key==0x01)
    {
      image(info, width/2, height/2-25);
    }
    if (key==0x02)
    {
      image(beeth, width/2, height/2-25);
    }
  }
}
                      //unsetting the flag for the key that is released
public void keyReleased() 
{
  i=0;
  while (keyboard[i].value!=key && i<keyboard.length-1)
  {  
    i++;
  } 
  if (keyboard[i].value == key && keyboard[i].flag== true)
  { 
    keyboard[i].unsetFlag();
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "TypeMusic" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
