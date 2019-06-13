package cryptanalysis.blowfish;

import cryptanalysis.datastructures.MyArrayList;

/**
 * Class that creates Blowfish encryption and decryption
 */
public class Blowfish {

    Boxes boxes = new Boxes();

    public long[] boxP = new Boxes().pbox;
    public long[][] boxS = new Boxes().sBox;
    public long left = 0, right = 0;
    MyArrayList<String> list = new MyArrayList();
    String textString;
    byte[] allbytes;

    /**
     * Initializing the P-array and S-boxes with values derived from pi
     *
     * @param key key used in encryption
     */
    public Blowfish(String text, String key) {

        int position = 0;
        int build = 0;
        int keyLength = key.length();
        byte[] k = key.getBytes();
        for (int i = 0; i < 18; ++i) {

            for (int j = 0; j < 4; j++) {
                build = (build << 8) | (((int) k[position]) & 0x0ff);

                if (++position == keyLength) {
                    position = 0;
                }
            }
            boxP[i] ^= build;

        }
        left = 0x00000000;
        right = 0x00000000;
        for (int i = 0; i < 18; i += 2) {
            encrypt(left, right);
            boxP[i] = left;
            boxP[i + 1] = right;
            System.out.println("P[" + i + "] = " + boxP[i]);
        }
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 256; j += 2) {
                encrypt(left, right);
                boxS[i][j] = left;
                boxS[i][j + 1] = right;
            }
        } 

        textString = text;
        int pituus = textString.length();
        int jakojaannos = 0;
        int h = 0;
        if (pituus % 8 == 0) {
            jakojaannos = 0;
        } else {
            jakojaannos = 8 - (pituus % 8);
        }
        System.out.println("jakojäännös " + jakojaannos);
        allbytes = new byte[textString.length() + jakojaannos];

        for (int i = 0; i < allbytes.length - jakojaannos; i++) {
            allbytes[i] = (byte) textString.charAt(i);
        }
        for (int i = 0; i < jakojaannos; i++) {
            allbytes[i + textString.length()] = 32;
        }

    }

    /**
     * Handles encryption
     *
     * @return hex
     */
    public String encryption() {
        int x = 0;
        char[] hexchar = new char[allbytes.length * 2];
        byte[] encrypted = null;
        String hex = "";
        for (int i = 0; i < allbytes.length; i += 8) {
            byte[] block = new byte[8];
            byte c = 0;

            for (int k = 0; k < 8; k++) {

                c = allbytes[i + k];

                block[k] = c;

            }
            //bytes to 32 bits
            left = ((block[3] & 0xffL)) | ((block[2] & 0xFFL) << 8) | ((block[1] & 0xFFL) << 16) | ((block[0] & 0xFFL) << 24);
            right = ((block[7] & 0xffL)) | ((block[6] & 0xFFL) << 8) | ((block[5] & 0xFFL) << 16) | ((block[4] & 0xFFL) << 24);

            encrypt(left, right);
            encrypted = toBytes(left, right);

            for (int j = 0; j < 8; j++) { 
                byte b1 = encrypted[j];
                char[] blaa = new char[2];
                blaa = byteToHex(b1);
                hexchar[x] = blaa[0];
                hexchar[x + 1] = blaa[1];

                x += 2;
            }

        }

        return String.valueOf(hexchar);
    }

    /**
     * Handles decryption
     *
     * @param text text in hex String to be decrypted
     * @return decrypted string in plaintext
     */
    public String decryption(String text) {

        int count = 0;

        byte[] bytes = this.hexStringToBytes(text);
        char[] avattu = new char[bytes.length];

        for (int i = 0; i < bytes.length; i += 8) {
            byte[] b = new byte[8];
            int j = 0;

            for (int k = 0; k < 8; k++) {
                byte c = bytes[i + k];
                b[j + k] = c;

            }

            defineLeftAndRight(b);

            decrypt(left, right);

            byte[] dec = new byte[8];
            dec = toBytes(left, right);

            for (int k = 0; k < 8; k++) {
                char cha = (char) dec[k];
                avattu[count] = cha;
                count++;
            }

        }
        return String.valueOf(avattu);

    }

    /**
     * Takes one byte and splits them to long so that text can get decrypted
     *
     * @param b one byte
     */
    public void defineLeftAndRight(byte[] b) {
        left = 0;
        right = 0;
        for (int i = 0; i < 4; i++) {

            left = (left << 8) + (b[i] & 0xff);
            right = (right << 8) + (b[i + 4] & 0xff);

        }
    }

    /**
     * Splits text to certain size of parts and saves it to ArrayList. In this
     * case size 4.
     *
     * @param text text to be splitted
     * @param size size of parts
     * @return
     */
    public MyArrayList<String> splitToParts(String text, int size) {

        MyArrayList<String> splitted = new MyArrayList();
        for (int i = 0; i < text.length(); i += size) {
            splitted.add(text.substring(i, Math.min(text.length(), i + size)));
        }
        return splitted;
    }

    /**
     * Puts left and right parts to byte[]
     *
     * @param left
     * @param right
     * @return byte[]
     */
    public byte[] toBytes(long left, long right) {

        byte[] bytes = new byte[8];
        bytes[0] = (byte) (left >> 24 & 0xff);
        bytes[1] = (byte) (left >> 16 & 0xff);
        bytes[2] = (byte) (left >> 8 & 0xff);
        bytes[3] = (byte) (left & 0xff);
        bytes[4] = (byte) (right >> 24 & 0xff);
        bytes[5] = (byte) (right >> 16 & 0xff);
        bytes[6] = (byte) (right >> 8 & 0xff);
        bytes[7] = (byte) (right & 0xff);

        return bytes;
    }

    /**
     * Encryption routine of Blowfish cipher.
     *
     * @param longL
     * @param longR
     */
    public void encrypt(long longL, long longR) {
        for (int i = 0; i < 16; i += 2) {
            longL ^= boxP[i]; // L = L XOR P[i]
            longR ^= f(longL);
            longR ^= boxP[i + 1];
            longL ^= f(longR);
        }
        longL ^= boxP[16];
        longR ^= boxP[17];

        //swap(L, R);
        long help = longL;
        longL = longR;
        longR = help;
        left = longL;
        right = longR;

    }

    /**
     * Decryption routine of blowfish
     *
     * @param longLeft
     * @param longRight
     */
    public void decrypt(long longLeft, long longRight) {
        for (int i = 16; i > 0; i -= 2) {
            longLeft ^= boxP[i + 1];
            longRight ^= f(longLeft);
            longRight ^= boxP[i];
            longLeft ^= f(longRight);
        }
        longLeft ^= boxP[1];
        longRight ^= boxP[0];
        long help = longLeft;
        longLeft = longRight;
        longRight = help;
        left = longLeft;
        right = longRight;
    }

    /**
     * The F-function splits the 32-bit input into four eight-bit quarters, and
     * uses the quarters as input to the S-boxes
     *
     * @param x long from encrypt routine
     * @return changed long
     */
    public long f(long x) {

        long h = boxS[0][(int) (x >> 24)] & boxS[1][(int) (x >> 16 & 0xff)];
        h = (h ^ (boxS[2][(int) (x >> 8 & 0xff)]) & boxS[3][(int) (x & 0xff)]);
        return h;
    }

    /**
     * Changes hex string to byte[] form
     *
     * @param hex String in hex form
     * @return bytes
     */
    public byte[] hexStringToBytes(String hex) {
        if (hex.length() % 2 == 1) {
            throw new IllegalArgumentException(
                    "Not allowed");
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = hexToByte(hex.substring(i, i + 2));
        }
        return bytes;
    }

    /**
     * Changes one hex to byte
     *
     * @param hexString
     * @return byte
     */
    public byte hexToByte(String hexString) {
        int first = changeToint(hexString.charAt(0));
        int sec = changeToint(hexString.charAt(1));
        return (byte) ((first << 4) + sec);
    }

    /**
     * Changes char (part of the hex string) to int
     *
     * @param c char
     * @return changed char changed to int
     */
    private int changeToint(char c) {
        int changed = Character.digit(c, 16);
        if (changed == -1) {
            throw new IllegalArgumentException(
                    "Not hex char: " + c);
        }
        return changed;
    }

    /**
     * Changing bytes to hex form
     *
     * @param bytes
     * @return h hex string
     */
    public String changeToHex(byte[] bytes) {
        StringBuffer h = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            h.append(byteToHex(bytes[i]));

        }
        return h.toString();
    }

    /**
     * Changing one byte to hex
     *
     * @param b one byte
     * @return hex String
     */
    public char[] byteToHex(byte b) {
        char[] hex = new char[2];
        hex[0] = Character.forDigit((b >> 4) & 0xF, 16);
        hex[1] = Character.forDigit((b & 0xF), 16);
        return hex;
    }

}


