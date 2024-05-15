package com.mycompany.restoran;

import java.util.ArrayList;
import java.util.HashMap;

public class Kasir {
    HashMap<String, Integer> transaksiHarian = new HashMap<String, Integer>();
    private ArrayList<HashMap<String, Integer>> transaksiBulanan = new ArrayList<HashMap<String, Integer>>();
    private Admin admin;
    
    public Kasir(Admin admin) {
        this.admin = admin;
        this.transaksiBulanan = new ArrayList<HashMap<String, Integer>>();
    }

    public void catatTransaksi(String namaMenu, int harga) {
        if (transaksiHarian.containsKey(namaMenu)) {
            int jumlah = transaksiHarian.get(namaMenu);
            jumlah += harga;
            transaksiHarian.put(namaMenu, jumlah);
        } else {
            transaksiHarian.put(namaMenu, harga);
        }
    }
//sad
    public void akumulasiTransaksi() {
        int totalHargaTransaksi = 0;
        for (Integer harga : transaksiHarian.values()) {
            totalHargaTransaksi += harga;  
        }
        int totalHargaBahanBaku = admin.getTotalHargaBahanBaku();
        int pendapatanBersih = totalHargaTransaksi - totalHargaBahanBaku;
        HashMap<String, Integer> transaksiBulananHariIni = new HashMap<String, Integer>();
        transaksiBulananHariIni.put("Pendapatan Bersih", pendapatanBersih);
        transaksiBulanan.add(transaksiBulananHariIni);
        transaksiHarian = new HashMap<String, Integer>();
    }

    public ArrayList<HashMap<String, Integer>> getTransaksiBulanan() {
        return transaksiBulanan;
    }
}
