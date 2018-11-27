package keychain.Config;

import keychain.Config.Exceptions.InvalidConfigException;
import keychain.FileIO.ConfigFileIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private Map<String, String> config;

    public Map<String, String> getConfig() {
        return config;
    }

    public void buildConfig() throws IOException, InvalidConfigException {
        Map<String, String> config = new HashMap<>();

        ArrayList<String> parts = ConfigFileIO.toArrayByLine("config.txt");

        for (String element : parts){
            if(!element.contains("=")){
                throw new InvalidConfigException("Configuration file is malformed");
            }

            String[] split = element.split("=");
            config.put(split[0], split[1]);
        }

        this.config = config;
    }

}
