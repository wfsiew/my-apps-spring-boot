package com.example.myapps.services;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;

import uk.org.okapibarcode.backend.Code128;
import uk.org.okapibarcode.backend.HumanReadableLocation;
import uk.org.okapibarcode.output.Java2DRenderer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

@Service
public class BarcodeService {

    public BufferedImage generateCode128BarcodeImageBarcode4J(final String barcodeText) {
        final Code128Bean barcodeGenerator = new Code128Bean();
        final BitmapCanvasProvider canvas = new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);
     
        barcodeGenerator.generateBarcode(canvas, barcodeText);
        return canvas.getBufferedImage();
    }

    public BufferedImage generateCode128BarcodeImage(String barcodeText) throws Exception {
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 250, 60);
     
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public BufferedImage generateCode128BarcodeImageOkapi(String barcodeText) {
        Code128 bc = new Code128();
        bc.setFontName("Monospaced");
        bc.setFontSize(16);
        bc.setModuleWidth(1);
        bc.setBarHeight(50);
        bc.setHumanReadableLocation(HumanReadableLocation.NONE);
        bc.setContent(barcodeText);

        int width = bc.getWidth();
        int height = bc.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = image.createGraphics();
        Java2DRenderer renderer = new Java2DRenderer(g2d, 1, Color.WHITE, Color.BLACK);
        renderer.render(bc);
        return image;
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

    public byte[] getBytesFromBufferedImage(BufferedImage bufferedImage) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

    public String getBase64Image(BufferedImage bufferedImage) throws Exception {
        byte[] b = getBytesFromBufferedImage(bufferedImage);
        String s = Base64.getEncoder().encodeToString(b);
        String x = String.format("data:image/png;base64,%s", s);
        return x;
    }
}