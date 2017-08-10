//
//  @ Project : ICT373 Assignment 1
//  @ File Name : Range.java
//  @ Details : Range class to handle generic range types. has functions to compare range values
//  @ Assumptions : Can be used in a genric way
//                  Ranges need to be of comparable type
//  @ Date : 9/04/2017
//  @ Author : Taaqif
//
package ict373assignment1;

/**
 * Generic Range class to allow for the creation of a Range object which uses a min and max. 
 * Useful for ranges of values. Has functions to check if a value is within the range and output the min and max 
 * Extends comparable to only work with data types that can be compared against each other
 * @author Taaqif
 * @param <T> the generic type needed to construct the object
 */
public class Range<T extends Comparable> {
    private T min;
    private T max;
    
    /**
     * Constructor to create a new range object with a minimum and a maximum
     * @param min the min value
     * @param max the max value, checks if max is larger than min
     */
    public Range (T min, T max){
        if(min.compareTo(max) > 0){
            throw new IllegalArgumentException("ERROR: Min range greater than max range");
        }else{
            this.max = max;
            this.min = min; 
        }
    }

    /**
     * Copy constructor 
     * @param newrange a copy constructor for quickly setting the range
     */
    public Range (Range<T> newrange){
        this.max = newrange.Getmax();
        this.min = newrange.Getmin(); 
        
    }

    /**
     * Checks if a nominated value is within the range of min and max
     * @param elem the value to check 
     * @return true if value is within range
     */
    public boolean contains (T elem){
        return (this.min.compareTo(elem) <= 0) && (this.max.compareTo(elem) >= 0);
    }

    /**
     * Gets the min value
     * @return the min value
     */
    public T Getmin(){
        return this.min;
    }

    /**
     * Gets the max value
     * @return the max value
     */
    public T Getmax(){
        return this.max;
    }

    /**
     * Converts the range to a string
     * @return the range as a string [min-max]
     */
    public String getRangeString(){
        
        return this.Getmin() + "-" + this.Getmax();
    }
}
