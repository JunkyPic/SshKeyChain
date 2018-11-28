package keychain.Models;

import java.util.TreeMap;

public class KeyChain{
    private TreeMap<String, String> keychain;

    public KeyChain(TreeMap<String, String> keychain) {
        this.keychain = keychain;
    }

    public String get(String key) {
        return keychain.get(key);
    }

    public TreeMap<String, String> getKeychain() {
        return keychain;
    }

    public void put(String key, String value) {
        keychain.put(key, value);
    }
}
