package ZIP;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class RecordingJSON {
    public static void main(String[] args) {

        String filename = "C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\input.json";
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\out_json.zip"));
            FileInputStream fis= new FileInputStream(filename);)
        {

            ZipEntry entry1 = new ZipEntry("input.json");
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