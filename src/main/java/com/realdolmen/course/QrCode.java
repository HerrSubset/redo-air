package com.realdolmen.course;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import javax.imageio.stream.ImageOutputStream;
import java.io.*;

/**
 * Created by NHOBB65 on 31/08/2016.
 *
 * Info page QRCODE
 * https://github.com/kenglxn/QRGen
 */
public class QrCode {
    public void generateQrCode (String url) {
        url = "http://localhost:8080/redo-air/booking.jsf?bookingid=" + url;
        ByteArrayOutputStream out = QRCode.from(url)
                .to(ImageType.PNG).stream();
        System.out.println("qr code generated" + url);

        try {
            FileOutputStream fout = new FileOutputStream(new File("QR_Code.JPG"));
            System.out.println("qr code generated" + out.toString());

            fout.write(out.toByteArray());

            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Do Logging
        } catch (IOException e) {
            e.printStackTrace();
            // Do Logging
        }


//        ByteArrayOutputStream out = QRCode.from(url)
//                .to(ImageType.PNG).stream();
//
//        try {
//            FileOutputStream fout = new FileOutputStream(new File("QR_Code.JPG"));
//
//            fout.write(out.toByteArray());
//
//            fout.flush();
//            fout.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            // Do Logging
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Do Logging
//        }
    }
}
