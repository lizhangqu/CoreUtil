package zafu.edu.cn.coreutil;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Https 证书工具类
 * User:lizhangqu(513163535@qq.com)
 * Date:2015-09-02
 * Time: 12:52
 */
public class SSLUtil {
    /**
     * 返回SSLSocketFactory
     * @param certificates 证书的输入流
     * @return SSLSocketFactory
     */
    public static SSLSocketFactory getSSLSocketFactory(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            return socketFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
