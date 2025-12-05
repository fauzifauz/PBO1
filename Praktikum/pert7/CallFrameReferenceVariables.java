package pert7;

public class CallFrameReferenceVariables {
    // method dengan parameter bertipe reference (objek String)
    private static void printAll(String s1, String s2, String s3) {
        // menampilkan isi dari masing-masing string
        System.out.println(s1.toString()); // cetak string pertama
        System.out.println(s2.toString()); // cetak string kedua
        System.out.println(s3.toString()); // cetak string ketiga
    }

    public static void main(String args[]) {
        // deklarasi variabel referensi (objek)
        String str1, str2, str3;

        // inisialisasi objek String baru
        str1 = new String("string_1");
        str2 = new String("string_2");
        str3 = new String("string_3");

        // panggil method printAll dengan tiga parameter referensi
        printAll(str1, str2, str3);
    }
}
