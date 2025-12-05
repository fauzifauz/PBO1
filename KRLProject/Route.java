public class Route {
    private String asal;
    private String tujuan;
    private double harga;

    public Route(String asal, String tujuan, double harga) {
        if (asal == null || tujuan == null) throw new IllegalArgumentException("Asal/Tujuan tidak boleh null");
        if (harga < 0) throw new IllegalArgumentException("Harga tidak boleh negatif");
        this.asal = asal.trim();
        this.tujuan = tujuan.trim();
        this.harga = harga;
    }

    public String getAsal() { return asal; }
    public String getTujuan() { return tujuan; }
    public double getHarga() { return harga; }

    public void setAsal(String a) { if (a==null) throw new IllegalArgumentException("Asal null"); this.asal = a.trim(); }
    public void setTujuan(String t) { if (t==null) throw new IllegalArgumentException("Tujuan null"); this.tujuan = t.trim(); }
    public void setHarga(double h) { if (h < 0) throw new IllegalArgumentException("Harga negatif"); this.harga = h; }

    @Override
    public String toString() {
        return asal + " -> " + tujuan + " | Rp" + String.format("%.0f", harga);
    }
}
