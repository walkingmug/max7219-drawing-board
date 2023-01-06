//Adapted from elegoo.com

//We always have to include the library
#include "LedControl.h"

/*
 Now we need a LedControl to work with.
 ***** These pin numbers will probably not work with your hardware *****
 pin 12 is connected to the DataIn 
 pin 11 is connected to LOAD(CS)
 pin 10 is connected to the CLK 
 We have only a single MAX72XX.
 */
LedControl lc=LedControl(12,10,11,1);

/* we always wait a bit between updates of the display */
unsigned long delaytime1=500;
unsigned long delaytime2=50;
void setup() {
  /*
   The MAX72XX is in power-saving mode on startup,
   we have to do a wakeup call
   */
  lc.shutdown(0,false);
  /* Set the brightness to a medium values */
  lc.setIntensity(0,8);
  /* and clear the display */
  lc.clearDisplay(0);
}

/*
 This method will display the LEDs.
 */
void writeArduinoOnMatrix() {
  /* here is the data for the characters */
  byte led_display[8]={//input};
  /* now display them one by one with a small delay */
  for (int i=0; i<8; i++){
      lc.setRow(0,i,led_display[i]);
  }
}

void loop() { 
  writeArduinoOnMatrix();
}