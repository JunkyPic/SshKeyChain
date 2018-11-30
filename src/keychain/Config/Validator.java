package keychain.Config;

import keychain.Config.Exceptions.InvalidConfigException;

import java.util.ArrayList;

public class Validator {

    /**
     * Must have values in the config
     */
    private ArrayList<String> arrayList = new ArrayList<String>() {{
        add("KEYCHAIN_FILE");
        add("OPEN_TERMINAL_ON_CONNECT_BUTTON_PRESS");
    }};

    public void validate(Config config) throws InvalidConfigException{
        for (String key : arrayList) {
            if(null == config.getConfig().get(key)) {
                throw new InvalidConfigException("Malformed or missing required config key: " + key);
            }
        }
    }
}
