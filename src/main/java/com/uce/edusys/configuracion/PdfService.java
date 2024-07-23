package com.uce.edusys.configuracion;

import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Service
public class PdfService {

    public byte[] generatePdfFromHtml(String html) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (OutputStream os = baos) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

}
