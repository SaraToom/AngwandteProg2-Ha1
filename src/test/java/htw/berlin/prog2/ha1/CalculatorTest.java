package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    //Teilaufgabe 1: Schreiben Sie einen neuen zusätzlichen Test, der eine bisher nicht getestete Funktionalität abdeckt, die bereits funktioniert und der daher direkt grün wird.
    @Test
    @DisplayName("should set screen to 0 ")
    void testClearKey() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(3);

        calc.pressClearKey();
        String expected = "0";

        String actual = calc.readScreen();
        assertEquals(expected, actual);
    }

//Teilaufgabe 2: Schreiben Sie zwei weitere zusätzliche Tests, die zwei unterschiedliche Fehlerkategorien aufdecken (d.h. deren Fehlerursachen in unterschiedlichen Methoden liegen) und somit fehlschlagen.
    @Test
    @DisplayName("should set screen to 0 ")
    void testClearKeyOnlyScreen(){
        Calculator calc = new Calculator();
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
// Drücken der Clear-Taste sollte nur den Bildschirm löschen, aber latestValue behalten
        calc.pressClearKey();

        calc.pressDigitKey(2);
        calc.pressEqualsKey();

    
        String expected = "7";
        String actual = calc.readScreen();

        System.out.println("Expected: " + expected + " | Actual: " + actual);

        
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("sollte den negativen zweiten Operanden korrekt verarbeiten")
    void testNegative(){
        Calculator calc = new Calculator();
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressNegativeKey(); //maccht aus 3 -3
        calc.pressEqualsKey();

        String expected = "2"; // 5 + (-3) =2
        String actual = calc.readScreen();

        assertEquals (expected, actual);

    }
}

