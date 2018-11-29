package keychain.Config;

import keychain.Config.Exceptions.InvalidConfigException;

import java.util.ArrayList;

public class Validator {

    /**
     * Must have values in the config
     */
    private ArrayList<String> arrayList = new ArrayList<>() {{
        add("KEYCHAIN_FILE");
        add("OPEN_TERMINAL_ON_ADDRESS_COPY");
    }};

    public void validate(Config config) throws InvalidConfigException{
        for (String key : arrayList) {
            if(null == config.getConfig().get(key)) {
                throw new InvalidConfigException("Malformed or missing required config key: " + key);
            }
        }
    }
}
