import java.util.Scanner;

public class PendakianSemeru {

    public static void main(String[] args) {

        /*
         Keterangan:
         H  = Jalur Hijau
         X  = Jurang / Tidak boleh dilewati
         P1 = Pos awal
         P2-P5 = Pos istirahat
         M  = Puncak Mahameru
         TC = Tanjakan Cinta
         RK = Ranu Kumbolo
        */

        String[][] map = {

        //0     1     2     3     4     5     6     7     8     9     10    11
        {"M", "X", "X", "X", "X", "X", "X", "X", "X", "H", "H", "H"}, // 0

        {"H", "X", "H", "H", "H", "X", "X", "P3","X", "H", "X", "H"}, // 1

        {"H", "X", "X", "X", "H", "P4","H", "H", "H", "H", "X", "H"}, // 2

        {"TC","H", "H", "H", "H", "X", "H", "H", "X", "X", "X", "H"}, // 3

        {"H", "H", "X", "H", "H", "X", "P2","X", "X", "X", "X", "H"}, // 4

        {"P5","H", "X", "RK","H", "X", "H", "H", "H", "H", "H", "P1"} // 5
        };

        Scanner input = new Scanner(System.in);

        // Input tenaga
        System.out.print("Masukkan tenaga awal : ");
        int tenaga = input.nextInt();
        input.nextLine();

        // Input jalur
        System.out.print("Masukkan jalur : ");
        String jalur = input.nextLine().toUpperCase();

        // Posisi awal P1
        int baris = 5;
        int kolom = 11;

        boolean selesai = false;

        for (int i = 0; i < jalur.length(); i++) {

            char langkah = jalur.charAt(i);

            int newBaris = baris;
            int newKolom = kolom;

            switch (langkah) {

                // Bergerak kiri
                case 'L':
                    newKolom--;
                    tenaga--;
                    break;

                // Bergerak kanan
                case 'R':
                    newKolom++;
                    tenaga--;
                    break;

                // Bergerak atas
                case 'U':
                    newBaris--;
                    tenaga--;
                    break;

                // Bergerak bawah
                case 'D':
                    newBaris++;
                    tenaga--;
                    break;

                // Istirahat
                case 'I':

                    // Hanya boleh di Pos
                    if (map[baris][kolom].startsWith("P")) {

                        tenaga += 10;

                        System.out.println(
                            "Istirahat berhasil, tenaga bertambah menjadi "
                            + tenaga
                        );

                    } else {

                        System.out.println(
                            "Mohon maaf, istirahat hanya diperbolehkan di Pos-pos yang tersedia"
                        );

                        selesai = true;
                    }

                    break;

                default:

                    System.out.println("Input langkah tidak valid");
                    selesai = true;
            }

            if (selesai) {
                break;
            }

            // Jika bukan istirahat
            if (langkah != 'I') {

                // Cek keluar map
                if (newBaris < 0 || newBaris >= map.length
                        || newKolom < 0 || newKolom >= map[0].length) {

                    System.out.println(
                        "Jalur anda salah, anda masuk ke jurang/blank"
                    );

                    selesai = true;
                    break;
                }

                // Cek jurang
                if (map[newBaris][newKolom].equals("X")) {

                    System.out.println(
                        "Jalur anda salah, anda masuk ke jurang/blank"
                    );

                    selesai = true;
                    break;
                }

                // Update posisi
                baris = newBaris;
                kolom = newKolom;
            }

            // Cek tenaga habis
            if (tenaga <= 0) {

                System.out.println(
                    "Jalur anda benar, tapi tenaga anda tidak akan kuat, "
                    + "coba jalur lain atau sempatkan istirahat terlebih dahulu"
                );

                selesai = true;
                break;
            }

            // Cek sampai Mahameru
            if (map[baris][kolom].equals("M")) {

                System.out.println(
                    "Selamat Pendakian anda berhasil mencapai "
                    + "Puncak Mahameru, sisa tenaga anda "
                    + tenaga
                );

                selesai = true;
                break;
            }
        }

        // Jika belum sampai puncak
        if (!selesai) {

            System.out.println(
                "Pendakian selesai."
            );

            System.out.println(
                "Posisi terakhir : (" + baris + "," + kolom + ")"
            );

            System.out.println(
                "Sisa tenaga : " + tenaga
            );
        }

        input.close();
    }
}