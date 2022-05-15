/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

/**
 *
 * @author Akrimi
 */
public class Facture {
/*    import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.JavaPIDEV.entities.Equipement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;*/

/**
 *
 * @author dell
 */
public class pdfequipement {
    /*public void liste_equipement() throws FileNotFoundException, DocumentException {

        EquipementCRUD ec = new EquipementCRUD();
        String message = "Voici la liste des équipement \n\n";

        String file_name = "src/PDF/liste_equipement.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Equipement> equipement = ec.affichageEquipement();
        PdfPTable table = new PdfPTable(5);

        
        
        PdfPCell cl1 = new PdfPCell(new Phrase("ref equipement"));
        table.addCell(cl1);
        PdfPCell cl = new PdfPCell(new Phrase("Nom equipement"));
        table.addCell(cl);
        PdfPCell cl2 = new PdfPCell(new Phrase("image"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("prix equipement"));
        table.addCell(cl3);
        PdfPCell cl4 = new PdfPCell(new Phrase("description"));
        table.addCell(cl4);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < equipement.size(); i++) {
            
            table.addCell("" + equipement.get(i).getRef_equipement());
            table.addCell("" + equipement.get(i).getNom_equipement());
            table.addCell("" + equipement.get(i).getImage());
            table.addCell("" + equipement.get(i).getPrix_equipement());
            table.addCell("" + equipement.get(i).getDescription_equipement());

        }
        document.add(table);

        document.close();

    }*/
}
    
}
