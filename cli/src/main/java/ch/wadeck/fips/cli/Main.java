package ch.wadeck.fips.cli;

import ch.wadeck.fips.liba.LibAClass1;
import ch.wadeck.fips.libb.LibBClass1;

public class Main {
    public static void main(String[] args) {
        new LibAClass1().hellFromA();
        new LibBClass1().hellFromB();

//        System.out.println("Providers list");
//        System.out.println(" ==== ");
//        Provider[] providers = Security.getProviders();
//        Arrays.stream(providers).forEach(provider -> {
//            System.out.println("Provider: " + provider.getName());
//        });
//        System.out.println(" ==== ");
        /*
         * Providers list
         *  ====
         * Provider: SUN
         * Provider: SunRsaSign
         * Provider: SunEC
         * Provider: SunJSSE
         * Provider: SunJCE
         * Provider: SunJGSS
         * Provider: SunSASL
         * Provider: XMLDSig
         * Provider: SunPCSC
         * Provider: SunMSCAPI
         *  ====
         */

        LibAClass1 a1 = new LibAClass1();
        LibBClass1 b1 = new LibBClass1();
        System.out.println("hash with MD5: " + a1.base64Encode(CliHelper.md5("From CLI helper MD5")));
        System.out.println("hash with MD5 (step3): " + a1.md5AndBase64_step3("From a1 MD5"));
//        System.out.println("hash with SHA1: " + b1.encodeSha1("SHA1 plaintext"));
//        System.out.println("hash with SHA256: " + b1.encodeSha256("SHA256 plaintext"));
        System.out.println("hash with SHA256_SUN: " + b1.encodeSha256_sun("SHA256 Sun plaintext"));
        System.out.println("encrypt with AES_CBC: " + b1.encryptAndBase64_AES_CBC_PKCS5Padding("AES CBC plaintext"));
//        System.out.println("encrypt with AES_ECB: " + new LibBClass1().encryptAndBase64_AES_ECB_PKCS5Padding("AES ECB plaintext"));
//        System.out.println("encrypt with ECB: " + new LibAClass1().base64Encode(new LibAClass1().encryptECB("ECB plaintext")));

//        System.out.println("Press any key to finish");
//
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
