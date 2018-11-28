package keychain.Config;

import keychain.Config.Exceptions.InvalidConfigException;

import java.util.ArrayList;

public class Validator {

    /**
     * Must have values in the config
     */
    private ArrayList<String> arrayList = new ArrayList<String>() {{
        add("KEYCHAIN_FILE");
    }};

    public void validate(Config config) throws InvalidConfigException{
        for (String key : arrayList) {
            if(null == config.getConfig().get(key)) {
                throw new InvalidConfigException("Malformed or missing required config key: " + key);
            }
        }
    }
}
