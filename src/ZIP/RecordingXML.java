package ZIP;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class RecordingXML {
    public static void main(String[] args) {

        String filename = "C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\input.xml";
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\out_xml.zip"));
            FileInputStream fis= new FileInputStream(filename);)
        {

            ZipEntry entry1 = new ZipEntry("XML_input.xml");
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();


        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

}