package pl.michaldurlak.AssetsInventoryManagement.upload_images;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.uploadcare.api.Client;
import com.uploadcare.api.File;
import com.uploadcare.upload.FileUploader;
import com.uploadcare.upload.UploadFailureException;
import com.uploadcare.upload.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;



@Service
public class QRcodeService {
    @Value("${qrcode.website.domain}")
    private String tempWebsite;

    private UploadcareService uploadcareService;

    @Autowired
    public QRcodeService(UploadcareService uploadcareService) {
        this.uploadcareService = uploadcareService;
    }


    //CREATING PNG QR CODE
    public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig( 0xFF000002 , 0xFFFFFF ) ;

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream,con);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }


    //GENERATE + UPLOAD QR CODE (RETURN STRING URL ADDRESS)
    public String generateRandomQRCode(Long idAssetNumber) {

        byte[] image;
        String finalUrlString = null;
        try {
            image = getQRCodeImage(tempWebsite+"/asset/"+idAssetNumber,250,250);

            //Upload image
            Client client = uploadcareService.getClient();

                //Uploadcare
                Uploader uploader = new FileUploader(client,image,idAssetNumber+".png");
                try {
                    File file = uploader.upload().save();
//                    System.out.println(file.getOriginalFileUrl());
                    finalUrlString = String.valueOf(file.getOriginalFileUrl());
                } catch (UploadFailureException e) {
//                    System.out.println("Upload failed :(");
                }
        } catch (WriterException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return finalUrlString;
    }

}
