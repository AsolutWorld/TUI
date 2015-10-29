package main.java.com.asolutworld.Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import main.java.com.asolutworld.Objects.Volunteer;


import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportManager {
    public static Document getPDF(ArrayList<Volunteer> data){
        Document document=new Document();
        try {
            CSVReader csvReader=new CSVReader(new FileReader("volunteer.csv"));

            PdfWriter.getInstance(document,new FileOutputStream("volunteer.pdf"));
            document.open();
            PdfPTable table=new PdfPTable(6);
            PdfPCell cell;
            String[] nextLine;
            while ((nextLine=csvReader.readNext())!=null){
                for(int i=0;i<6;i++){
                    cell=new PdfPCell(new Phrase(nextLine[i]));
                    table.addCell(cell);
                }
            }
            document.add(table);
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void makeCSV(ResultSet data){
        try {
            CSVWriter csvWriter=new CSVWriter(new FileWriter("volunteer.csv"),'\t');
            csvWriter.writeAll(data,true);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}
