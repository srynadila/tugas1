import java.util.Scanner;

public class userinterface {

    public static void menu() {
        System.out.println();
        System.out.println("+===============+");
        System.out.println("|   pilih menu  |");
        System.out.println("+---------------+");
        System.out.println("|   [C] :Create |");
        System.out.println("|   [R] :Read   |");
        System.out.println("|   [U] :Update |");
        System.out.println("|   [D] :Delete |");
        System.out.println("|   [X] :Exit   |");
        System.out.println("+===============+");
    }

    public static void main(String[] args) {
        Database db = new Database();
        System.out.println("APLIKASI SIMPEL CRUD");

        while (true) {
            menu();
            Scanner sc = new Scanner(System.in);
            System.out.print("Pilih: ");
            String pilihan = sc.nextLine().toUpperCase();

            try {
                switch (pilihan) {
                    case "C":
                        System.out.println("INFO: Anda memilih menu CREATE");
                        System.out.print("NIM             : ");
                        String nim = sc.nextLine();
                        System.out.print("NAMA MAHASISWA  : ");
                        String nama = sc.nextLine();
                        System.out.print("ALAMAT          : ");
                        String alamat = sc.nextLine();
                        System.out.print("SEMESTER        : ");
                        int semester = sc.nextInt();
                        System.out.print("SKS             : ");
                        int sks = sc.nextInt();
                        System.out.print("IPK             : ");
                        double ipk = sc.nextDouble();
                        sc.nextLine();
                        db.insert(nim, nama, alamat, semester, sks, ipk);
                        System.out.println("DATA BERHASIL DITAMBAHKAN");
                        break;

                    case "R":
                        db.view();
                        break;

                    case "U":
                        db.view();
                        System.out.print("Input NIM yang akan diupdate: ");
                        String key = sc.nextLine();
                        int index = db.search(key);
                        if (index == -1) {
                            System.out.println("Error: NIM tidak ditemukan!");
                            break;
                        }
                        System.out.print("NIM BARU          : ");
                        nim = sc.nextLine();
                        System.out.print("NAMA MAHASISWA    : ");
                        nama = sc.nextLine();
                        System.out.print("ALAMAT            : ");
                        alamat = sc.nextLine();
                        System.out.print("SEMESTER          : ");
                        semester = sc.nextInt();
                        System.out.print("SKS               : ");
                        sks = sc.nextInt();
                        System.out.print("IPK               : ");
                        ipk = sc.nextDouble();
                        sc.nextLine();
                        db.update(index, nim, nama, alamat, semester, sks, ipk);
                        System.out.println("DATA BERHASIL DIPERBAHARUI");
                        break;

                    case "D":
                        db.view();
                        System.out.print("Masukkan NIM yang akan dihapus: ");
                        key = sc.nextLine();
                        index = db.search(key);
                        if (index == -1) {
                            System.out.println("Error: NIM tidak ditemukan!");
                        } else {
                            db.delete(index);
                            System.out.println("DATA BERHASIL DIHAPUS");
                        }
                        break;

                    case "X":
                        System.out.println("Keluar dari aplikasi...");
                        System.exit(0);
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
