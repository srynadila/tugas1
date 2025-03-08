import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private ArrayList<Mahasiswa> data = new ArrayList<>();
    private String filename = "src/data.csv";
    private Path path = Path.of(filename);

    public Database() {
        open();
    }

    public ArrayList<Mahasiswa> getData() {
        return data;
    }

    public void open() {
        try {
            List<String> lines = Files.readAllLines(path);
            data = new ArrayList<>();
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] element = line.split(",");
                String nim = element[0];
                String nama = element[1];
                String alamat = element[2];
                int semester = Integer.parseInt(element[3]);
                int sks = Integer.parseInt(element[4]);
                double ipk = Double.parseDouble(element[5]);
                Mahasiswa mhs = new Mahasiswa(nim, nama, alamat, semester, sks, ipk);
                data.add(mhs);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        StringBuilder sb = new StringBuilder();
        sb.append("NIM,NAMA,ALAMAT (KOTA),SEMESTER,SKS,IPK\n");
        if (!data.isEmpty()) {
            for (Mahasiswa mhs : data) {
                String line = mhs.getNim() + "," + mhs.getNama() + "," + mhs.getAlamat() + "," +
                        mhs.getSemester() + "," + mhs.getSks() + "," + mhs.getIpk() + "\n";
                sb.append(line);
            }
        }
        try {
            Files.writeString(path, sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void view() {
        System.out.println("======================================================================================");
        System.out.printf("| %-8.8S | %-20.20S | %-20.20S | %8.8S | %3.3S | %4.4S |%n",
                "NIM", "NAMA", "ALAMAT", "SEMESTER", "SKS", "IPK");
        System.out.println("---------------------------------------------------------------------------------------");
        for (Mahasiswa mhs : data) {
            System.out.printf("| %-8S | %-20.20S | %-20.20S | %8.8S | %3.3S | %4.4S |%n",
                    mhs.getNim(), mhs.getNama(), mhs.getAlamat(),
                    mhs.getSemester(), mhs.getSks(), mhs.getIpk());
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public boolean insert(String nim, String nama, String alamat, int semester, int sks, double ipk) {
        if (search(nim) != -1) {
            throw new IllegalArgumentException("Error: NIM " + nim + " sudah ada di database!");
        }
        Mahasiswa mhs = new Mahasiswa(nim, nama, alamat, semester, sks, ipk);
        data.add(mhs);
        save();
        return true;
    }

    public int search(String nim) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getNim().equalsIgnoreCase(nim)) {
                return i;
            }
        }
        return -1;
    }

    public boolean update(int index, String nim, String nama, String alamat, int semester, int sks, double ipk) {
        if (search(nim) != -1 && !data.get(index).getNim().equals(nim)) {
            throw new IllegalArgumentException("Error: NIM " + nim + " sudah ada di database!");
        }
        data.set(index, new Mahasiswa(nim, nama, alamat, semester, sks, ipk));
        save();
        return true;
    }

    public boolean delete(int index) {
        data.remove(index);
        save();
        return true;
    }
}
