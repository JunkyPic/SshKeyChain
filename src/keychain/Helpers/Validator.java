package keychain.Helpers;

import keychain.FileIO.KeyChainFileIO;
import keychain.Models.KeyChain;

public class Validator {
    public final static String VALID = "VALID";

    public static String isAliasValid(String alias, KeyChain keyChain) {
        if(null == alias) {
            return "An unknown error occurred.";
        }

       if(null != keyChain.get(alias) ) {
           return "That alias already exists";
       }

       if(alias.trim().equals("")) {
           return "Alias cannot be empty";
       }

       if(alias.contains(KeyChainFileIO.DELIMITER)) {
           return "Invalid character \"" + KeyChainFileIO.DELIMITER + "\".";
       }

       return VALID;
    }

    /**
     * The address must contain "ssh", exactly one space, an @ symbol preceded by
     * at least one word and followed by any number of characters
     *
     * @param address The ssh address in the format of ssh user@ip/domain
     * @return int
     */
    public static String isAddressValid(String address) {
        if(!address.matches("(ssh\\s[A-Za-z0-9]*@.*)")) {
            return "Invalid ssh address.";
        }

        return VALID;
    }
}
