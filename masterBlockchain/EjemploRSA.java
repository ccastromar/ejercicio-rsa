package masterBlockchain;

import java.security.Signature;

public class EjemploRSA {

    public static void main(String[] args) throws Exception {
        // La primera vez genero las claves
        //generarClaves();
       //  System.exit(0);
        String mensaje = "Saludos desde el mundo cripto";
        System.out.println("Mensaje original:\n" + mensaje);
        String mensajeEncriptado = encriptarMensaje(mensaje);
       // descifrarMensaje(mensajeEncriptado);
        String mensajeFirmado = firmarMensaje(mensaje);
        verificarMensaje(mensaje, mensajeFirmado);
    }

    public static String encriptarMensaje(String str) throws Exception {
        RSA rsa2 = new RSA();
        rsa2.openFromDiskPublicKey("/tmp/profe.pub");
       // rsa2.openFromDiskPublicKey("/tmp/rsa.pub");
        String secure2 = rsa2.encrypt(str);
        System.out.println("\nMensaje Encriptado:");
        System.out.println(secure2);
        return secure2;
    }

    public static String firmarMensaje(String str) throws Exception {
        RSA rsa = new RSA();
        rsa.openFromDiskPrivateKey("/tmp/rsa.pri");
        rsa.openFromDiskPublicKey("/tmp/rsa.pub");
        String secure = rsa.sign(str);
        System.out.println("\nMensaje Firmado:");
        System.out.println(secure);
        return secure;
    }

    public static void verificarMensaje(String texto, String msgFirmado) throws Exception {
        RSA rsa = new RSA();
        rsa.openFromDiskPublicKey("/tmp/rsa.pub");
        boolean ver = rsa.verify(texto, msgFirmado);
        if (ver) {
            System.out.println("\nFirma verificada");
        } else {
            System.out.println("\nFirma no verificada");
        }
    }

    public static void generarClaves() throws Exception {
        RSA rsa = new RSA();
        rsa.genKeyPair(1024);
        rsa.saveToDiskPrivateKey("/tmp/rsa.pri");
        rsa.saveToDiskPublicKey("/tmp/rsa.pub");
        System.out.println("Clave pública y privada guardadas en disco");
    }

    public static void descifrarMensaje(String secure) throws Exception {
        RSA rsa2 = new RSA();       
        rsa2.openFromDiskPrivateKey("/tmp/rsa.pri");
        rsa2.openFromDiskPublicKey("/tmp/rsa.pub");
        String unsecure = rsa2.decrypt(secure);
        System.out.println("\nMensaje Descifrado:");
        System.out.println(unsecure);
    }
}
