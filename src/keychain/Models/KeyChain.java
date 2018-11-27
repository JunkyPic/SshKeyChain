package keychain.Models;

import keychain.FileIO.FileIO;

public class KeyChain extends AbstractKeyChain{
    public KeyChain(FileIO fileIO) {
        super(fileIO);
    }
}
