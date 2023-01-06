# Drawing Board for Arduino Uno MAX7219 module

## Description

Allows you to draw on a 8x8 panel and converts the drawing into Arduino code that can be executed on a MAX7219 module.

![MAX7219](https://user-images.githubusercontent.com/29484054/211084238-c3c761f1-7940-4c80-9b22-5dafd722930e.gif)

## Getting Started

### Dependencies

#### For running the program:
* Java 8
* JUnit

#### For executing it on a MAX7219 module:
* [Arduino Uno Software](https://www.arduino.cc/en/software)
* LedControl library [from Elegoo](https://www.elegoo.com/pages/arduino-kits-support-files)
* (1) x Arduino Uno R3 (or equivalent)
* (1) x Max7219 module
* (5) x F-M wires (Female to Male DuPont wires)

### Executing program

1. Run '/src/ButtonMatrix.java'
2. Draw by clicking on the squares
3. Click 'Save'
4. Run '/result/result.ino' (requires Arduino software)
5. If the MAX7219 module is connected via Arduino, it will show the drawing

### Changing the data
* Change the intensity of the LED in 'MAX7219.ino':
```
/* Set the brightness to a medium values */
lc.setIntensity(0,8);
```

### Help
* For help on the basics of how to run Arduino programs and connect it to a MAX7219 module, see Lesson 15 of [The Most Complete Starter Kit for Elegoo Uno v1.0](https://www.elegoo.com/pages/arduino-kits-support-files)

## Version History
* 0.1
    * Initial Release
