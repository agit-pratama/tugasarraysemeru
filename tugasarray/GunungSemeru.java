package tugasarray;
import java.util.Scanner;

public class GunungSemeru {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // MAP JALUR
        // 1 = jalur hijau (boleh dilewati)
        // 0 = jurang / blank
        // P = Pos / tempat istirahat

        char[][] map = {
                {'0','0','0','0','0','0','0','0'},
                {'0','1','1','1','1','1','1','0'},
                {'0','1','0','0','0','0','1','0'},
                {'0','1','1','1','P','0','1','0'},
                {'0','0','0','1','1','0','1','0'},
                {'0','P','1','1','1','1','1','0'},
                {'0','0','0','0','0','0','1','0'},
                {'0','0','0','0','0','0','1','0'}
        };

        // posisi awal P1
        int x = 1;
        int y = 1;

        System.out.print("Masukkan tenaga awal : ");
        int tenaga = input.nextInt();

        input.nextLine();

        System.out.print("Masukkan jalur : ");
        String jalur = input.nextLine();

        boolean salah = false;
        boolean berhasil = false;

        for (int i = 0; i < jalur.length(); i++) {

            char langkah = jalur.charAt(i);

            int newX = x;
            int newY = y;

            switch (langkah) {

                case 'L':
                    newY--;
                    tenaga--;
                    break;

                case 'R':
                    // cek apakah istirahat atau gerak kanan
                    if (map[x][y] == 'P') {
                        tenaga += 10;
                    } else {
                        System.out.println("Mohon maaf, istirahat hanya diperbolehkan di Pos-pos yang tersedia");
                        salah = true;
                    }
                    break;

                case 'U':
                    newX--;
                    tenaga--;
                    break;

                case 'D':
                    newX++;
                    tenaga--;
                    break;

                default:
                    System.out.println("Input langkah tidak valid");
                    salah = true;
            }

            if (salah)
                break;

            // cek batas map
            if (newX < 0 || newX >= map.length || newY < 0 || newY >= map[0].length) {
                System.out.println("Jalur anda salah, anda keluar map");
                salah = true;
                break;
            }

            // cek jurang / blank
            if (map[newX][newY] == '0') {
                System.out.println("Jalur anda salah, anda masuk ke jurang/blank");
                salah = true;
                break;
            }

            // update posisi
            x = newX;
            y = newY;
        }

        // titik puncak
        if (!salah) {

            // misal puncak di koordinat tertentu
            if (x == 1 && y == 6) {

                if (tenaga > 0) {
                    System.out.println("Selamat Pendakian anda berhasil mencapai Puncak Mahameru, sisa tenaga anda " + tenaga);
                } else {
                    System.out.println("Jalur anda benar, tapi tenaga anda tidak akan kuat, coba jalur lain atau sempatkan istirahat terlebih dahulu");
                }

            } else {
                System.out.println("Pendakian belum mencapai puncak");
            }
        }

        input.close();
    }
}