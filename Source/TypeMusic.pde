/*TypeMusic was by
* Shashank Shekhar (shashankjet@gmail.com)
* Aditya Kumar Nayak (adityanayak1991@gmail.com)

Feel free to modify or use the code for your purposes preferably with the credits intact.

We'd appreciate if you stopped by at www.4errors.com/typemusic and left a comment
*/

import arb.soundcipher.*;

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

  void unsetFlag()
  {  
    flag=false;
  }

  void setFlag()
  {  
    flag=true;
  }

  void playKey()
  {
    sc.playNote(keyMIDI, vol, duration);
  }
}

                      //declaring the required keys and
                      //defining notes for each key
keyy k7 = new keyy('7', 61.0);
keyy k8 = new keyy('8', 63.0);

keyy k0 = new keyy('0', 66.0);
keyy s1 = new keyy('-', 68.0);
keyy s2 = new keyy('=', 70.0);

keyy kQ = new keyy('Q', 0.0);
keyy kY = new keyy('Y', 60.0);
keyy kU = new keyy('U', 62.0);
keyy kI = new keyy('I', 64.0);
keyy kO = new keyy('O', 65.0);
keyy kP = new keyy('P', 64.0); 
keyy s3 = new keyy('[', 67.0);
keyy s4 = new keyy(']', 69.0);

keyy kS = new keyy('S', 49.0);
keyy kD = new keyy('D', 51.0);

keyy kG = new keyy('G', 54.0);
keyy kH = new keyy('H', 56.0);
keyy kJ = new keyy('J', 58.0);


keyy kZ = new keyy('Z', 48.0);
keyy kX = new keyy('X', 50.0);
keyy kC = new keyy('C', 52.0);
keyy kV = new keyy('V', 53.0);
keyy kB = new keyy('B', 55.0);
keyy kN = new keyy('N', 57.0);
keyy kM = new keyy('M', 59.0);

                      //putting all the keyy objects in an array
keyy keyboard[]= {
  k7, k8, k0, s1, s2, 
  kY, kU, kI, kO, kP, s3, s4, 
  kS, kD, kG, kH, kJ, 
  kZ, kX, kC, kV, kB, kN, kM
};

void setup()
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
  textFont(createFont("Arial", 18));
  text("Press TAB to change Instrument", 30, 540);
  text("CTRL+A for ABOUT", 350, 540);
  text("CTRL+B for Beethovan", 580, 540);
}



void draw()
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
void keyReleased() 
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

