package keychain.Helpers;

import keychain.FileIO.KeyChainFileIO;
import keychain.Models.KeyChain;
import keychain.Views.Errors.Errors;

public class Validator {
    public final static int VALIDATOR_STATUS_ALIAS_EXISTS = 0;
    public final static int VALIDATOR_STATUS_ALIAS_EMPTY = 1;
    public final static int VALIDATOR_STATUS_ALIAS_INVALID_CHARACTER = 2;
    public final static int VALIDATOR_STATUS_ADDRESS_INVALID = 3;

    public static int isAliasValid(String alias, KeyChain keyChain) {
        if(null == alias) {
            return Errors.UNKNOWN_ERROR;
        }

       if(null != keyChain.get(alias) ) {
           return VALIDATOR_STATUS_ALIAS_EXISTS;
       }

       if(alias.equals("")) {
           return VALIDATOR_STATUS_ALIAS_EMPTY;
       }

       if(alias.contains(KeyChainFileIO.DELIMITER)) {
           return VALIDATOR_STATUS_ALIAS_INVALID_CHARACTER;
       }

       return 1000;
    }

    /**
     * The address must contain "ssh", exactly one space, an @ symbol preceded by
     * at least one word and followed by any number of characters
     *
     * @param address The ssh address in the format of ssh user@ip/domain
     * @return int
     */
    public static int isAddressValid(String address) {
        if(!address.matches("(ssh\\s[A-Za-z]*@.*)")) {
            return VALIDATOR_STATUS_ADDRESS_INVALID;
        }

        return 1000;
    }

    public static boolean isAddressValid(String address, String addr) {
        return !address.matches("(ssh\\s[A-Za-z]*@.*)");
    }

}
