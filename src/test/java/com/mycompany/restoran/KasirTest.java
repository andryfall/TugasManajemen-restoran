package com.mycompany.restoran;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class KasirTest {

    private Kasir kasir;
    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin();
        BahanBaku bahan1 = new BahanBaku("Gula", 5000);
        BahanBaku bahan2 = new BahanBaku("Tepung", 10000);
        admin.tambahBahanBaku(bahan1);
        admin.tambahBahanBaku(bahan2);

        Menu menu1 = new Menu("Nasi Goreng", 15000);
        Menu menu2 = new Menu("Mie Goreng", 12000);
        admin.tambahMenu(menu1);
        admin.tambahMenu(menu2);

        kasir = new Kasir(admin);
    }

    @Test
    public void testCatatTransaksi_NewMenu() {
        kasir.catatTransaksi("Ayam Goreng", 20000);
        HashMap<String, Integer> transaksiHarian = kasir.transaksiHarian;  // Accessing default access level fieldd
        assertEquals(20000, transaksiHarian.get("Ayam Goreng").intValue());
    }

    @Test
    public void testCatatTransaksi_ExistingMenu() {
        kasir.catatTransaksi("Nasi Goreng", 15000);
        kasir.catatTransaksi("Nasi Goreng", 15000);
        HashMap<String, Integer> transaksiHarian = kasir.transaksiHarian;  // Accessing default access level field
        assertEquals(30000, transaksiHarian.get("Nasi Goreng").intValue());
    }

    @Test
    public void testAkumulasiTransaksi() {
        kasir.catatTransaksi("Nasi Goreng", 15000);
        kasir.catatTransaksi("Mie Goreng", 12000);
        kasir.akumulasiTransaksi();

        ArrayList<HashMap<String, Integer>> transaksiBulanan = kasir.getTransaksiBulanan();
        HashMap<String, Integer> transaksiHariIni = transaksiBulanan.get(0);

        int totalHargaBahanBaku = admin.getTotalHargaBahanBaku();
        int expectedPendapatanBersih = 27000 - totalHargaBahanBaku;

        assertEquals(expectedPendapatanBersih, transaksiHariIni.get("Pendapatan Bersih").intValue());
    }
}