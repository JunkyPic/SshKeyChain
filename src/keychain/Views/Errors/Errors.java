package keychain.Views.Errors;

import keychain.FileIO.KeyChainFileIO;
import keychain.Helpers.Validator;

import java.util.HashMap;
import java.util.Map;

public final class Errors {
    public static final int GENERAL_ERROR_EMPTY_FIELDS = 100;
    public static final int UNKNOWN_ERROR = 99;

    public static final Map<Integer, String> errors = new HashMap<Integer, String>() {{
        put(Validator.VALIDATOR_STATUS_ALIAS_EXISTS, "That alias already exists");
        put(Validator.VALIDATOR_STATUS_ALIAS_EMPTY, "Alias cannot be empty");
        put(Validator.VALIDATOR_STATUS_ALIAS_INVALID_CHARACTER, "Invalid character \"" + KeyChainFileIO.DELIMITER + "\".");
        put(GENERAL_ERROR_EMPTY_FIELDS, "Some fields are empty. Make sure that both alias and address are filled.");
        put(UNKNOWN_ERROR, "An unknown error occurred.");
        put(Validator.VALIDATOR_STATUS_ADDRESS_INVALID, "Invalid ssh address.");
    }};

    public static String getErrorByCode(Integer code) {
        return errors.get(code);
    }
}
