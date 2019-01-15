package controller;

import java.util.List;
import model.PenjualanModel;
import view.FormPenjualan;

/**
 *
 * @author titan
 */
public class PenjualanController {
    
    private FormPenjualan view;
    private PenjualanModel model;
    
    public PenyumbangController(FormPenjualan view) {
        this.view = view;
        model = new PenjualanModel();
    }
    
    //SELECT DATA
    public List selectPeny(String id, String nama) {
        return model.selectPenyumbang(id, nama);
    }
    
    // INSERT DATA
    public boolean insertPeny(){
        return model.insertPeny();
    }
    
    //UPDATE DATA
    public boolean updatePeny(String id){
        return model.updatePeny(id);
    }
    
    //HAPUS DATA
    public boolean hapusPeny(String id){
        return model.deletePeny(id);
    }
    
    public String idOto(){
        return model.idAuto();
    }
    
    public void input(  String id, String nama, String gender, String almt,
                        String telp) {
        model.setIdPenyumbang(id);
        model.setNamaPenyumbang(nama);
        model.setGender(gender);
        model.setAlamat(almt);
        model.setNoTelp(telp);
    }
    
}
