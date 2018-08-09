package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class is used for parsing text
 *
 * @author Diz
 */
public class Parser {

    /**
     * Takes a string to look in and a starting position and looks for the next string, lookFor,
     * that occurs after the given start position.
     * @param lookIn String to look in
     * @param start Starting position to look
     * @param lookFor String to look for
     * @return 
     */
    public static int getPosOfNext(String lookIn, int start, String lookFor) {
        for (int i = start; i < lookIn.length(); i++) {
            if (String.valueOf(lookIn.charAt(i)).equals(lookFor)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * This method will take a string as a parameter and will divide this string
     * into an array of substrings. The length of this division is specified as
     * a parameter.
     *
     * @param in The String to be divided into an array of substrings
     * @param step The length of each division.
     * @return array of strings.
     */
    public static String[] getAtInterval(String in, int step) {
        int remIn = in.length() % step, divIn = in.length() / step;
        String[] separated;
        int ixSep = -1;
        String s = "";
        if (remIn != 0) {
            separated = new String[divIn + 1];
        }
        else {
            separated = new String[divIn];
        }

        // initialize separated[]
        for (int i = 0; i < separated.length; i++) {
            separated[i] = "";
        }

        for (int start = 0; start < (divIn * step); start += step) {
            ixSep++;
            for (int j = start; j < (start + step); j++) {
                s += String.valueOf(in.charAt(j));
            }
            separated[ixSep] = s;
            s = "";
        }
        
        if (remIn != 0) {
            for (int i = (divIn * step); i < in.length(); i++) {
                s += String.valueOf(in.charAt(i));
            }
            separated[ixSep + 1] = s;
        }
        
       // for (String t : separated) {
        //    System.out.println(t + " " + t.length());
        //}
        return separated;
    }
    
    /**
     * This method takes a string, in, and an array of strings, subs, and counts each 
     * occurrence of the strings in subs that appear in the string in. Returns an array
     * of integers that is the count of each subs element that occurs in string in.
     * 
     * @param in the string to go through 
     * @param subs the strings that are looked for and counted in string in
     * @return an int array of the counts of each string in string array subs
     */
    public static int[] countEach(String in, String[] subs) {
        int[] count = new int[subs.length];
        int ixSubsChar = 0;
        for (int ixIn = 0; ixIn < in.length(); ixIn++) {
           
            for (int ixSubs = 0; ixSubs < subs.length; ixSubs++) {
                // match first letter of subs
                if ((String.valueOf(in.charAt(ixIn)).equals(String.valueOf(subs[ixSubs].charAt(0)))) &&
                        (subs[ixSubs].length() <= (in.length() - ixIn))) {
                    ixSubsChar = 0;
                    
                    if (subs[ixSubs].length() == 1) {
                        count[ixSubs] += 1;
                    }
                    else {
                        for (int ixInMore = (ixIn + 1); ixInMore < (ixIn + subs[ixSubs].length()); ixInMore++) {
                            ixSubsChar++;
                            if (!(String.valueOf(in.charAt(ixInMore)).equals(String.valueOf(subs[ixSubs].charAt(ixSubsChar))))) {
                                break;
                            }
                            if (ixSubsChar == (subs[ixSubs].length() - 1)) {
                                count[ixSubs] += 1;
                            }
                        } // end for ixInMore
                    } // end else
                } // end if
            } // end for ix Subs
        } // end for ixIn
        
        return count;
    } // end countEach()
    
    /**
     * Specific to genetics. This method takes a nucleotide sequence (AGTC) and converts to 
     * a purine, pyrimidine (RY) sequence.
     * @param in genetic sequence to be converted to YR sequence
     * @return YR sequence
     */
    public static String seqToRY(String in) {
        String out = "";
        for (int i = 0; i < in.length(); i++) {
            if (String.valueOf(in.charAt(i)).equals("G") || String.valueOf(in.charAt(i)).equals("A")) {
                out += "R";
            }
            if (String.valueOf(in.charAt(i)).equals("T") || String.valueOf(in.charAt(i)).equals("C")) {
                out += "Y";
            }
        }
        return out;
    }
    
    /**
     * Finds the starting positions of a specified string, lookFor, inside another string, lookIn.
     * The first character if lookIn is indexed at 0.
     * @param lookIn The string that we are looking in.
     * @param lookFor The string that we are looking for.
     * @return array of starting positions of the string we are looking for.
     */
    public static int[] findString(String lookIn, String lookFor) {
        int[] points;
        ArrayList<Integer> pointsAl = new ArrayList<Integer>();
        int ixLookFor = 0;
        for (int i = 0; i <= (lookIn.length() - lookFor.length()); i++) {
            if (String.valueOf(lookIn.charAt(i)).equals(String.valueOf(lookFor.charAt(0)))) {
                if (lookFor.length() == 1) {
                    pointsAl.add(i + 1);
                }
                else {
                    for (int j = (i + 1); j < (i + lookFor.length()); j++) {
                        ixLookFor++;
                        if (!(String.valueOf(lookIn.charAt(j)).equals(String.valueOf(lookFor.charAt(ixLookFor))))) {
                            break;
                        }
                        if ((j - i) == (lookFor.length() - 1)) {
                            pointsAl.add(i + 1);
                        }
                    }
                }
            }
            ixLookFor = 0;
        }
        points = new int[pointsAl.size()];
        for (int i = 0; i < pointsAl.size(); i++) {
            points[i] = pointsAl.get(i);
        }
        return points;
    }
    
    /**
     * Takes a string and extracts a substring from it with the given start and end positions specified.
     * @param in the main string
     * @param start the position in the main string to begin building the substring
     * @param end the position in the main string to end the substring
     * @return substring desired , empty string if index error occurs
     */
    public static String getSubString(String in, int start, int end) {
        String s = "";
        try {
            for (int i = start; i < end; i++) {
                s += String.valueOf(in.charAt(i));
            }
        } catch (IndexOutOfBoundsException ioobe) {
            return s;
        }
        return s;
    }
    
    /**
     * Takes a string and returns the next amount of characters of that string beginning at a specified location.
     * @param in The string to be read from.
     * @param start The starting position of the string to return. Inclusive for starting position. 
     * @param len The length of the string to be returned.
     * @return A substring of the given string specified by starting position and number of characters.
     */
    public static String getNextString(String in, int start, int len) {
        String ret = "";
        
        if (((start + len) <= in.length()) && (start > -1) && (len > 0)) {
            for (int i = start; i < (start + len); i++) {
                ret += String.valueOf(in.charAt(i));
            }
        }
        
        return ret;
    }
    
    
    /**
     * Takes a string, in, and looks for a matching substring, lookFor, that may have a number of mismatches in it. 
     * Example:    in = "applebananaorange"
     *             lookFor =  "binana"
     *             misMax = 1
     *         
     *          lookFor is part of in with one mismatch in the second letter of lookFor
     *          This method will return 
     *              int[0][0] = 5     [0][0] is the first case of a match found with allowed number of mismatches = position of first char in String in
     *              int[0][1] = 6     [0][1] within this first case found the first mismatch [1] is at position 6 in String in
     * 
     *          General form of this double array that is returned is
     *          int[each instance of a match within allowable mismatches][number of mismatch within String lookFor, not 0] = position in String in
     * 
     *          not 0 above means 0 in the second bracket of returned int[][], [x][0], indicates the position of the first char of that 
     *          particular match found.
     * 
     *          Position begins at 0.
     * 
     * @param in The string to look in.
     * @param lookFor The string we are looking for.
     * @param misMax The maximum number of mismatches within String lookFor.
     * @return int[][] explained in method details.
     */
    
    public static int[][] fsWMis(String in, String lookFor, int misMax) {
        
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> misIndex = new ArrayList<Integer>();
        boolean match = false;
        int k = -1, mis = 0;
        String build = "";
        for (int i = 0; i < (in.length() - lookFor.length() + 1); i++) {
            k = -1;
            mis = 0;
            build = "";
            misIndex.add(i);
            for (int j = i; j < (lookFor.length() + i); j++) {
                k++;
                build += String.valueOf(in.charAt(j));
                if (!(String.valueOf(in.charAt(j)).equals(String.valueOf(lookFor.charAt(k))))) { 
                    mis++;
                    misIndex.add(j);
                    match = true;
                    if (mis > misMax) {
                        misIndex = new ArrayList<Integer>();             
                        break;
                    }
                }  
                if (k == (lookFor.length() - 1)) {
                   ret.add(misIndex);
                   misIndex = new ArrayList<Integer>();
                }      
            }  
        }
        return ALToArrayInt2d(ret);
    }
    
    /**
     * Removes first and last characters of a string
     * @param in String to be modified.
     * @return ret Modified string
     */
    public static String takeOutFirstAndLastChar(String in) {
        String ret = "";
        for (int i = 1; i < (in.length() - 1); i++) {
            ret += String.valueOf(in.charAt(i));
        }
        
        return ret;
    }
    
    public static String[] breakAt(String delimit, String in, int numOfPieces) {
        String[] ret = new String[numOfPieces];
        for (int i = 0; i < numOfPieces; i++) {
            ret[i] = "";
        }
        int currentPiece = 0;
        
        for (int i = 0; i < in.length(); i++) {
            if (String.valueOf(in.charAt(i)).equals(delimit) && (currentPiece < (numOfPieces - 1))) {
                currentPiece++;
                ret[currentPiece] += String.valueOf(in.charAt(i));
                System.out.println("in check ret[" + currentPiece + "] = " + ret[currentPiece]);
            }
            else {
                ret[currentPiece] += String.valueOf(in.charAt(i));
                System.out.println("ret[" + currentPiece + "] = " + ret[currentPiece]);
            }
        }
        return ret;
    }
    
    /**
     * Creates a string from given string, in, that inserts, insert, in between each double occurrence
     * of delimit.
     * Example:
     * in = suggar
     * delimit = g
     * insert = !
     *              
     * Call method as insertBetween("!", "g", "suggar");
     * return string = sug!gar
     * 
     * @param insert String to be inserted at the double occurrence of delimit.
     * @param delimit String to look for double occurrence of.
     * @param in String to look in for delimit, and insert into.
     * @return build New String with inserts.
     */
    public static String insertBetween(String insert, String delimit, String in) {
        int count = 0;
        String build = "";
        for (int i = 0; i < (in.length() - 1); i++) {
            if (String.valueOf(in.charAt(i)).equals(delimit) && (String.valueOf(in.charAt(i + 1)).equals(delimit))) {
                build += delimit;
                build += insert;
            }
            else {
                build += String.valueOf(in.charAt(i));
            }
        }
        return build;
    }
    
    /**
     * Creates a string from given string, in, that inserts, insert, in between a digit and the
     * delimit string in that order. Digit first then delimit string
     * Example:
     * in = banana;apple;100"orange";something
     * delimit = " , the quotes character as a string
     * insert = ; , semicolon character as a string
     * 
     * Call method as    insertBetweenNumberAndDelimit(";", "\"", in)
     * return string = banana;apple;100;"orange";something
     * @param insert String to be inserted at the occurrence of digit then delimit string.
     * @param delimit Delimiting string
     * @param in String to look in for delimit, and insert into.
     * @return build New String with inserts.
     */
    public static String insertBetweenNumberAndDelimit(String insert, String delimit, String in) {
        String build = "";
        for (int i = 0; i < (in.length() - 1); i++) {
            if (isInteger(String.valueOf(in.charAt(i))) && (String.valueOf(in.charAt(i + 1)).equals(delimit))) {
                build += String.valueOf(in.charAt(i));
                build += insert;
            }
            else {
                build += String.valueOf(in.charAt(i));
            }
        }
        return build;
    }
    
    /**
     * Creates an array of Strings where each element is a string that exists between two
     * delimit Strings in String in. No delimit at front of in and no delimit at end of in format.
     * 
     * Example:
     * in = 200;45;"Banana";orange;3600
     * delimit = ;   (semi colon)
     * return = array of strings: 200, 45, "Banana", orange, 3600
     * 
     * @param delimit Delimiting string.
     * @param in String to look in.
     * @return array of strings.
     */
    public static String[] getBetween(String delimit, String in) {
        ArrayList<String> strings = new ArrayList<String>();
        String addIt = "";

        for (int i = 0; i < in.length(); i++) {
            if (String.valueOf(in.charAt(i)).equals(delimit)) {
                strings.add(addIt);
                addIt = "";
            }
            else {
                addIt += String.valueOf(in.charAt(i));
            }
            if (i == (in.length() - 1)) {
                strings.add(addIt);
            }
        }
        return ALToArray(strings);     
    }
    
    /**
     * Creates a string from given string, in, that inserts, insert at every i'th occurrence of insertAt.
     * Every i'th occurrence starting at start.
     * 
     * Example:
     * insert = ;  (semi colon)
     * in = apple_orangeRainbow_yellowWatermelon
     * start = 6
     * insertAt = 4
     * return = apple_o;rang;eRai;nbow;_yel;lowW;ater;melo;n
     *                6    4    4    4    4    4    4    
     *              start  i    i    i    i    i    i
     * @param insert String to be modified.
     * @param in String to be inserted at every i'th occurrence of insertAt within the string in.
     * @param start Index to begin inserting, insert, at every i'th occurrence of insertAt.
     * @param insertAt Insert at every i'th occurrence of insertAt.
     * @return build Modified string
     * 
     */
    public static String insertAtEveryIndex(String insert, String in, int start, int insertAt) {
        String build = "";
        int count = -1;
        for (int i = 0; i < in.length(); i++) {
            build += String.valueOf(in.charAt(i));
            count += 1;
            if (i == start) {
                build += insert;
                count = 0;
            }
            else if ((count == insertAt) && (i > start)) {
                build += insert;
                count = 0;
            }
            
        }
        return build;
    }
    
    /**
     * Inserts a string, insert, between two given strings, first and second, with a minimum
     * number of characters in between the two given strings, first and second.
     * @param in String to be modified.
     * @param insert String to be inserted.
     * @param first First string to indicate an insertion.
     * @param second Second string to indicate and insertion between first and second.
     * @param minChars A minimum number of characters that must be between first and second for 
     *                insertion to take place.
     * @return build The modified string with insertions.
     */
    public static String insertBetweenTwo(String in, String insert, String first, String second, int minChars) {
        String build = "";
        int minCount = -1;
        boolean counting = false;
        for (int i = 0; i < in.length(); i++) {
            if (counting) {
                minCount++;
                if ((minCount >= minChars) && (String.valueOf(in.charAt(i)).equals(second))) {
                    build += insert;
                    build += String.valueOf(in.charAt(i));
                    minCount = -1;
                    counting = false;
                }
                else if (String.valueOf(in.charAt(i)).equals(second)) {
                    build += String.valueOf(in.charAt(i));
                    minCount = -1;
                    counting = false;
                }
                else {
                    build += String.valueOf(in.charAt(i));
                }
            }
            else if (String.valueOf(in.charAt(i)).equals(first)) {
                counting = true;
                build += String.valueOf(in.charAt(i));
            }
            else {
                build += String.valueOf(in.charAt(i));
            }
        }
        return build;
    }
    
    public static int detectGeneticAAOrRYType(String in) {
    	final int GENERAL = 0, GENETIC = 1, RY = 2, AMINO_ACID = 3;
    	in = in.toLowerCase();
    	if (in.contains("n")) return AMINO_ACID;
    	if (in.contains("d")) return AMINO_ACID;
    	if (in.contains("q")) return AMINO_ACID;
    	if (in.contains("e")) return AMINO_ACID;
    	if (in.contains("h")) return AMINO_ACID;
    	if (in.contains("i")) return AMINO_ACID;
    	if (in.contains("l")) return AMINO_ACID;
    	if (in.contains("k")) return AMINO_ACID;
    	if (in.contains("m")) return AMINO_ACID;
    	if (in.contains("f")) return AMINO_ACID;
    	if (in.contains("p")) return AMINO_ACID;
    	if (in.contains("s")) return AMINO_ACID;
    	if (in.contains("w")) return AMINO_ACID;
    	if (in.contains("v")) return AMINO_ACID;
    	if (in.contains("b")) return AMINO_ACID;
    	if (in.contains("z")) return AMINO_ACID;
    	if (in.contains("x")) return AMINO_ACID;
    	HashMap<String, Integer> map = Parser.countEachChar(in);
    	Iterator<String> iter = map.keySet().iterator();
    	while (iter.hasNext()) {
    		String key = iter.next();
    		if (key.equals("r") || (key.equals("y"))) {
    			if (map.size() == 2) {
    				return RY;
    			}
    		}
    		if (map.size() == 4) {
    			return GENETIC;
    		}
    	}
    	return GENERAL;
    }
    
    public static double compareTwoStrings(String one, String two) {
    	double comparePercent = 0.0;
    	int lenDiff = 0;
    	lenDiff = Math.abs(one.length() - two.length());
    	int iterTo = 0;
    	int numOfMisses = 0;
    	int greaterLen = 0;
    	if (one.length() < two.length()) {
    		iterTo = one.length();
    		greaterLen = two.length();
    	}
    	else {
    		iterTo = two.length();
    		greaterLen = one.length();
    	}
    	for (int i = 0; i < iterTo; i++) {
    		if (!(String.valueOf(one.charAt(i)).equals(String.valueOf(two.charAt(i))))) {
    			numOfMisses++;
    		}
    	}
    	System.out.println("in parser compare");
    	
    	numOfMisses += lenDiff;
    	System.out.println("numOfMisses: " + numOfMisses);
    	System.out.println("lenDiff: " + lenDiff);
    	comparePercent = (double)numOfMisses / (double)greaterLen;
    	comparePercent *= 100;
    	comparePercent = 100 - comparePercent;
    	return comparePercent;
    }
    
    public static HashMap<String, Integer> countEachChar(String in) {
    	HashMap<String, Integer> count = new HashMap<String, Integer>();
    	boolean newLetter = true;
    	String key = "", current = "";
    	for (int i = 0; i < in.length(); i++) {
    		newLetter = true;
    		current = String.valueOf(in.charAt(i));
    		Iterator<String> iter = count.keySet().iterator();
    		while (iter.hasNext()) {
    			key = (String)iter.next();
    			if (key.equals(current)) {
    				newLetter = false;
    				count.put(key, count.get(key) + 1);
    				break;
    			}
    			
    		}
    		if (newLetter) {
    			count.put(current, 1);
    		}
    	}
    	return count;
    }
    
    /**
     * Converts a one dimensional arraylist to an array.
     * @param strings Arraylist to convert.
     * @return array
     */
    public static String[] ALToArray(ArrayList<String> strings) {
        String[] ret = new String[strings.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = strings.get(i);
        }
        return ret;
    }
    
    /**
     * Converts a two dimensional arraylist to an array.
     * @param al Arraylist to convert.
     * @return array
     */
    public static int[][] ALToArrayInt2d(ArrayList<ArrayList<Integer>> al) {
        int s1, s2;
        s1 = al.size();
        if (s1 > 0) {
            int[][] ret = new int[s1][];
        
            for (int i = 0; i < s1; i++) {
                s2 = al.get(i).size();
                ret[i] = new int[s2];
           
                for (int j = 0; j < s2; j++) {
                    ret[i][j] = al.get(i).get(j);
                    System.out.println("ret[" + i + "][" + j + "] = " + ret[i][j]);
                }
            }
            System.out.println("The end");
            
            return ret;
        }
        else {
            return null;
        }
    }
    
    public static ArrayList<Integer> IntArrayToAL1d(int[] arr) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            ret.add(arr[i]);
        }
        return ret;
    }
    
    public static boolean isInteger(String in) {
        try {
            Integer.parseInt(in);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
