package ssatr.lab1.exercise4;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketsManager {

    private List<Ticket> ticketsList = new ArrayList<>();

    Ticket generateTicket(String eventName, String ticketHolder, double price) {
        System.out.println("Creating new ticket...");

        Ticket ticket = new Ticket(eventName, ticketHolder, price);
        ticketsList.add(ticket);

        return ticket;
    }

    public void generateTicketQr(Ticket ticket){
        QRCodeWriter barcodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = barcodeWriter.encode(ticket.toString(), BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage bi =  MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(bi, "png", new File("ticket_"+ticket.getTicketHolder()+".png"));
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void generateTicketPdf(Ticket ticket, String imagePath, int buyerCount) throws Exception {
        // Define the PDF output file path
        String pdfPath = "pdf_with_image" + buyerCount +".pdf";

        try {
            // Create a PdfWriter object
            PdfWriter writer = new PdfWriter(pdfPath);

            // Initialize the PDF document
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Create a document to work with layout
            Document document = new Document(pdfDoc);

            // Add a paragraph to the document
            document.add(new Paragraph(ticket.getTicketHolder() + " for " + ticket.getEventName()));

            // Load the image
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image image = new Image(imageData);

            // Optionally, scale the image to fit the page or set specific dimensions
            image.scaleToFit(200, 200);  // Example: scaling the image

            // Add the image to the document
            document.add(image);

            // Add more content (e.g., another paragraph)
            document.add(new Paragraph("Price: " + ticket.getPrice()));

            // Close the document
            document.close();

            System.out.println("PDF created with image successfully at: " + pdfPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    boolean validateTicket(Ticket ticket) {
        System.out.println("Validating ticket...");

        return true;
    }

}
