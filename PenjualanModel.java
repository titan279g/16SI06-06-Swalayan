package model;

import Koneksi.KoneksiDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author titan
 */
public class PenjualanModel {
    
    
    private KoneksiDB koneksi;
    private ResultSet rs;
    private String query;
    private boolean status;
    private List<PenjualanModel> listPenj;
    
    public PenjualanModel() {
        koneksi = new KoneksiDB();
        koneksi.koneksiDb();
    }
    
//    TABEL TRANSAKSI PENJUALAN
    private String idPenjualan;
    private String idKaryawan;
    private String idMember;
    private String tglPenjualan;
    private String totalTrans;

    public String geIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(String idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public String getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(String idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getTglPenjualan() {
        return tglPenjualan;
    }

    public void setTglPenjualan(String tglPenjualan) {
        this.tglPenjualan = tglPenjualan;
    }

    public String getTotalTrans() {
        return totalTrans;
    }

    public void setTotalTrans(String totalTrans) {
        this.totalTrans = totalTrans;
    }
    
//    TABEL DETAIL TRANSAKSI
    private String idBarang;
    private int harga;
    private int jumlah;
    private String namaBrg;

    public String getNamaBrg() {
        return namaBrg;
    }

    public void setNamaBrg(String namaBrg) {
        this.namaBrg = namaBrg;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    public List tampilTabel(String id) {
        query = "SELECT B.id_barang, B.nama_brg, B.harga, DP.jumlah, SUM(B.harga*DP.jumlah) AS Subtotal "
                + "FROM transaksi_penjualan TP JOIN detail_penjualan DP ON TP.id_penjualan=DP.id_penjualan "
                + "JOIN barang B ON DP.id_barang=B.id_barang"
                + "WHERE id_barang LIKE '%"+id+"%'";
        status = koneksi.eksekusi(query, true);
        if(status) {
            rs = koneksi.getRs();
            listPenj = new ArrayList<PenjualanModel>();
            
            try {
                while(rs.next()) {
                    PenjualanModel model = new PenjualanModel();
                    model.setIdBarang(rs.getString(1));
                    model.setNamaBrg(rs.getString(2));
                    model.setHarga(rs.getInt(3));
                    model.setJumlah(rs.getInt(4));
                    model.setTotalTrans(rs.getString(5));
                    listPenj.add(model);
                }
                rs.close();
                return listPenj;
            } catch (Exception e) {
                return null;
            }            
        }
        return null;
    }
    
}
