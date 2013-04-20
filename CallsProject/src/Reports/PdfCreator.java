/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

/**
 *
 * @author Evan
 */
import Broker.PhysicianBroker;
import Container.Physician;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PdfCreator
{

    private static String FILE = "C:/Users/Asus/Documents/GitHub/CS-3720-Calls-2/CallsProject/reports/reports.pdf";
    // Font type setup
    private static Font labelFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font nameFont = new Font(Font.FontFamily.TIMES_ROMAN, 24,
            Font.UNDERLINE);
    private static Font detailFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
            Font.NORMAL);
    private static Font infoFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);
    private static Font physFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,
            Font.ITALIC);
    private static Font tblheadFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,
            Font.BOLD, BaseColor.BLUE);
    private static Font tblinfoFont = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.NORMAL);

// generateReports function
    public void generateReports(File file)
    {
        try
        {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            internalReportPage(document);
            document.newPage();
            externalReportPage(document);
            document.close();
        } catch (FileNotFoundException | DocumentException e)
        {
            System.out.println("file was not found");
        }
    }

    private void internalReportPage(Document document) throws DocumentException
    {
        Paragraph base = new Paragraph();
        basicInformation(base);
        document.add(new Paragraph("Internal Report", labelFont));
        document.add(new Paragraph(" "));
        document.add(base);
        Paragraph tableInternal = new Paragraph();
        createTableInternal(tableInternal);
        document.add(tableInternal);
    }

    private void externalReportPage(Document document) throws DocumentException
    {
        Paragraph base = new Paragraph();
        basicInformation(base);
        document.add(new Paragraph("External Report", labelFont));
        document.add(new Paragraph(" "));
        document.add(base);
        Paragraph tableExternal = new Paragraph();
        createTableExternal(tableExternal);
        document.add(tableExternal);
    }

    private void basicInformation(Paragraph base) throws DocumentException
    {
        base.add(new Paragraph("Lethbridge Clinic", nameFont));
        base.add(new Paragraph(" "));
        base.add(new Paragraph("Phone Number: 403-XXX-XXXX", detailFont));
        base.add(new Paragraph("Adress: ", detailFont));
        base.add(new Paragraph(" "));
        base.add(new Paragraph("Physicians: ", physFont));
        base.add(new Paragraph(" "));
    }

    private static void createTableInternal(Paragraph tableInt) throws BadElementException, DocumentException
    {
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);

        PdfPCell c1 = new PdfPCell(new Phrase("First Name", tblheadFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase("Last Name", tblheadFont));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase("Phone Number", tblheadFont));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c3);

        PdfPCell c4 = new PdfPCell(new Phrase("Date of Birth", tblheadFont));
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c4);

        PdfPCell c5 = new PdfPCell(new Phrase("Start Date", tblheadFont));
        c5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c5);

        PdfPCell c6 = new PdfPCell(new Phrase("End Date", tblheadFont));
        c6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c6);

        PdfPCell c7 = new PdfPCell(new Phrase("Address", tblheadFont));
        c7.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c7);

        table.setHeaderRows(1);

        ArrayList<Physician> phys = PhysicianBroker.getPhysicianBroker().getAllPhysiciansNames();

        for (int i = 0; i < phys.size(); i++)
        {
            //table.addCell(phys.get(i).getFirstName());
            //table.addCell(phys.get(i).getLastName());
            //table.addCell(phys.get(i).getPhoneNumber());
            PdfPCell firstname = new PdfPCell(new Phrase(phys.get(i).getFirstName(), tblinfoFont));
            firstname.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(firstname);
            PdfPCell lastname = new PdfPCell(new Phrase(phys.get(i).getLastName(), tblinfoFont));
            lastname.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(lastname);
            PdfPCell phonenum = new PdfPCell(new Phrase(phys.get(i).getPhoneNumber(), tblinfoFont));
            phonenum.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(phonenum);
            /*
             PdfPCell weekdays = new PdfPCell(new Phrase("40", tblinfoFont));
             weekdays.setHorizontalAlignment(Element.ALIGN_CENTER);
             table.addCell(weekdays);
             PdfPCell weekends = new PdfPCell(new Phrase("10", tblinfoFont));
             weekends.setHorizontalAlignment(Element.ALIGN_CENTER);
             table.addCell(weekends);
             PdfPCell holidays = new PdfPCell(new Phrase("3", tblinfoFont));
             holidays.setHorizontalAlignment(Element.ALIGN_CENTER);
             table.addCell(holidays);
             */
            PdfPCell dob = new PdfPCell(new Phrase(phys.get(i).getBirthDate(), tblinfoFont));
            dob.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(dob);
            PdfPCell sdate = new PdfPCell(new Phrase(phys.get(i).getStartDate(), tblinfoFont));
            sdate.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(sdate);
            PdfPCell edate = new PdfPCell(new Phrase(phys.get(i).getEndDate(), tblinfoFont));
            edate.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(edate);
            PdfPCell addr = new PdfPCell(new Phrase(phys.get(i).getAddress(), tblinfoFont));
            addr.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(addr);
        }

        int[] columnWidths = new int[]
        {
            10, 10, 13, 10, 10, 10, 17
        };
        table.setWidths(columnWidths);
        tableInt.add(table);
    }

    private static void createTableExternal(Paragraph tableExt) throws BadElementException, DocumentException
    {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);

        PdfPCell c1 = new PdfPCell(new Phrase("First Name", tblheadFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase("Last Name", tblheadFont));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase("Phone Number", tblheadFont));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c3);

        table.setHeaderRows(1);

        ArrayList<Physician> phys = PhysicianBroker.getPhysicianBroker().getAllPhysiciansNames();

        for (int i = 0; i < phys.size(); i++)
        {
            //table.addCell(phys.get(i).getFirstName());
            //table.addCell(phys.get(i).getLastName());
            //table.addCell(phys.get(i).getPhoneNumber());
            PdfPCell firstname = new PdfPCell(new Phrase(phys.get(i).getFirstName(), tblinfoFont));
            firstname.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(firstname);
            PdfPCell lastname = new PdfPCell(new Phrase(phys.get(i).getLastName(), tblinfoFont));
            lastname.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(lastname);
            PdfPCell phonenum = new PdfPCell(new Phrase(phys.get(i).getPhoneNumber(), tblinfoFont));
            phonenum.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(phonenum);
        }

        int[] columnWidths = new int[]
        {
            10, 10, 10
        };
        table.setWidths(columnWidths);
        tableExt.add(table);
    }
}