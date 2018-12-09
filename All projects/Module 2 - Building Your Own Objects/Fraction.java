// The Fraction class is an object that holds information about a fraction (numerator and denominator).
// It will have several constructors and both private and public methods implementing the behavior of a fraction.

public class Fraction {

    private int numerator, denominator;

    public Fraction(int numerator, int denominator){
        if (denominator == 0){
            throw new IllegalArgumentException();
        }
        else if (numerator < 0 && denominator < 0){
            numerator = -numerator;
            denominator = -denominator;
        }
        else if (numerator > 0 && denominator < 0){
            numerator = -numerator;
            denominator = -denominator;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int numerator){
        this.numerator = numerator;
        this.denominator = 1;
    }

    public Fraction(){
        this.numerator = 0;
        this.denominator = 1;
    }

    // exposes the value of the numerator field to the user
    public int getNumerator(){
        return numerator;
    }

    // exposes the value of the denominator field to the user
    public int getDenominator(){
        return denominator;
    }

    // "numerator/denominator", a String representation of the Fraction
    public String toString(){
        if (denominator == 1) {
            return numerator + "";
        }
        else{
            return numerator + "/" + denominator;
        }
    }

    // the result of numerator / denominator
    public double toDouble(){
        return (double) numerator / denominator;
    }

    // returns a new Fraction that is the sum of other and this fractions
    public Fraction add(Fraction other){
        int num;
        int den;

        num = this.denominator * other.numerator + other.denominator * this.numerator;
        den = this.denominator * other.denominator;

        this.numerator = num;
        this.denominator = den;

        this.toLowestTerms();

        return this;
    }

    // returns a new Fraction that is the difference between the other and this fraction
    public Fraction subtract(Fraction other){
        int num;
        int den;

        num = other.denominator * this.numerator - this.denominator * other.numerator;
        den = this.denominator * other.denominator;

        this.numerator = num;
        this.denominator = den;

        this.toLowestTerms();

        return this;
    }

    // returns a new Fraction that is the product of the other and this fraction
    public Fraction multiply(Fraction other){
        int num;
        int den;

        num = this.numerator * other.numerator;
        den = this.denominator * other.denominator;

        this.numerator = num;
        this.denominator = den;

        this.toLowestTerms();

        return this;
    }

    // returns a new Fraction that is the division of the other and this fraction, throw an IllegalArgumentException() if the user asks you to divide by 0
    public Fraction divide(Fraction other){
        int num;
        int den;

        num = other.denominator * this.numerator;
        den = this.denominator * other.numerator;

        this.numerator = num;
        this.denominator = den;

        this.toLowestTerms();

        return this;
    }

    // must take in an "Object" to properly override the Object class's equals method, but should ultimately check if two fractions are equal
    public boolean equals(Fraction other){
        other.toLowestTerms();
        this.toLowestTerms();
        if (other.numerator == this.numerator && other.denominator == this.denominator){
            return true;
        }
        else{
            return false;
        }
    }

    // converts the current fraction to the lowest terms
    public void toLowestTerms(){
        int num;
        int den;

        num = this.numerator / gcd(this.numerator, this.denominator);
        den = this.denominator / gcd(this.numerator, this.denominator);

        this.numerator = num;
        this.denominator = den;
    }

    // takes in two ints and determines the greatest common divisors of the two ints, should be a static method
    public static int gcd(int num, int den){
        while (num != 0 && den != 0){
            int remainder = num % den;
            num = den;
            den = remainder;
        }
        return num;
    }
}
