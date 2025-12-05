public class Station {
    
    // Menyimpan nama stasiun, bersifat private agar tidak bisa diakses langsung dari luar class
    private String name;

    // Constructor: dipanggil ketika membuat object Station baru
    public Station(String name) {

        // Validasi: nama tidak boleh null, kosong, atau hanya spasi
        if (name == null || name.trim().isEmpty()) 
            throw new IllegalArgumentException("Nama stasiun tidak boleh kosong");

        // Menyimpan nama yang sudah di-trim (menghapus spasi di depan & belakang)
        this.name = name.trim();
    }

    // Getter: mengembalikan nama stasiun
    public String getName() { 
        return name; 
    }

    // Setter: mengubah nama stasiun
    public void setName(String name) {

        // Validasi lagi untuk memastikan nama baru tidak kosong atau null
        if (name == null || name.trim().isEmpty()) 
            throw new IllegalArgumentException("Nama stasiun tidak boleh kosong");

        // Menyimpan nama baru setelah membersihkan spasi
        this.name = name.trim();
    }

    // Override: mengubah perilaku default toString() agar menampilkan nama stasiun
    @Override
    public String toString() { 
        return name; 
    }
}
