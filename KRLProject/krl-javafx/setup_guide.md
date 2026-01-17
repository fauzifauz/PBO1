# Panduan Menjalankan Project KRL JavaFX

Berikut adalah langkah-langkah untuk menyiapkan dan menjalankan project ini:

## 1. Persiapan Database (MySQL)
Pastikan MySQL sudah berjalan di komputer Anda. Anda perlu membuat database bernama `krl_system` dan tabel-tabel yang diperlukan.

### Menggunakan SQL Script
Saya telah menyediakan file `database_setup.sql`. Anda bisa menjalankannya melalui MySQL CLI atau tools seperti phpMyAdmin/HeidiSQL.

Jika menggunakan terminal:
```bash
mysql -u root -p < database_setup.sql
```
*(Catatan: Jika tidak ada password, cukup tekan Enter saat diminta)*

**Konfigurasi Koneksi:**
Konfigurasi database ada di file:
`src/main/java/com/krlsystem/util/DBConnection.java`
Pastikan `USER` dan `PASSWORD` sesuai dengan settingan MySQL Anda.

## 2. Menjalankan Aplikasi
Project ini menggunakan Maven dan JavaFX. Untuk menjalankannya, gunakan perintah berikut di folder root project:

```bash
mvn javafx:run
```

## 3. Data Login Default
Setelah aplikasi berjalan, Anda bisa login dengan akun berikut (sesuai isi `database_setup.sql`):

| Role | Username | Password |
| :--- | :--- | :--- |
| **Admin** | admin | admin123 |
| **User** | user | user123 |

---
> [!NOTE]
> Pastikan Anda menggunakan **Java 21** sesuai dengan konfigurasi di `pom.xml`.
