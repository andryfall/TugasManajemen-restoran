package com.mycompany.restoran;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.ArrayList;

public class KasirTest {
    private Kasir kasir;
    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin(); // Assuming a default constructor is available
        kasir = new Kasir(admin);
    }

    @Test
    public void testCatatTransaksi_NewMenu() {
        kasir.catatTransaksi("Nasi Goreng", 10000);
        assertTrue(kasir.transaksiHarian.containsKey("Nasi Goreng"));
        assertEquals(10000, kasir.transaksiHarian.get("Nasi Goreng"));
    }

    @Test
    public void testCatatTransaksi_ExistingMenu() {
        kasir.catatTransaksi("Nasi Goreng", 10000);
        kasir.catatTransaksi("Nasi Goreng", 5000);
        assertTrue(kasir.transaksiHarian.containsKey("Nasi Goreng"));
        assertEquals(15000, kasir.transaksiHarian.get("Nasi Goreng"));
    }

    @Test
    public void testAkumulasiTransaksi() {
        kasir.catatTransaksi("Nasi Goreng", 10000);
        kasir.catatTransaksi("Mie Goreng", 15000);

        // Mocking the admin.getTotalHargaBahanBaku() method
        admin.setTotalHargaBahanBaku(5000); // Assuming this is a setter method

        kasir.akumulasiTransaksi();

        // Assuming transaksiBulanan is accessible through a getter method
        assertFalse(kasir.transaksiHarian.containsKey("Nasi Goreng"));
        assertFalse(kasir.transaksiHarian.containsKey("Mie Goreng"));

        // Validate that the monthly transaction contains the expected data
        assertEquals(1, kasir.getTransaksiBulanan().size());
        assertEquals(20000, kasir.getTransaksiBulanan().get(0).get("Pendapatan Bersih"));
    }
}

// Mock Admin class
class Admin {
    private int totalHargaBahanBaku;

    private ArrayList<Menu> menuList = new ArrayList<>();
    private ArrayList<BahanBaku> bahanBakuList = new ArrayList<>();

    public int getTotalHargaBahanBaku() {
        return totalHargaBahanBaku;
    }

    public void setTotalHargaBahanBaku(int totalHargaBahanBaku) {
        this.totalHargaBahanBaku = totalHargaBahanBaku;
    }

    public void tambahMenu(Menu menu) {
        menuList.add(menu);
    }

    public void ubahMenu(Menu menu, String namaMenu, int harga) {
        menu.setNamaMenu(namaMenu);
        menu.setHarga(harga);
    }

    public void hapusMenu(Menu menu) {
        menuList.remove(menu);
    }

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }

    public void tambahBahanBaku(BahanBaku bahanBaku) {
        bahanBakuList.add(bahanBaku);
    }
}

// Mock Menu class
class Menu {
    private String namaMenu;
    private int harga;

    public Menu(String namaMenu, int harga) {
        this.namaMenu = namaMenu;
        this.harga = harga;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}

// Mock BahanBaku class
class BahanBaku {
    private String namaBahanBaku;
    private int harga;

    public BahanBaku(String namaBahanBaku, int harga) {
        this.namaBahanBaku = namaBahanBaku;
        this.harga = harga;
    }

    public String getNamaBahanBaku() {
        return namaBahanBaku;
    }

    public void setNamaBahanBaku(String namaBahanBaku) {
        this.namaBahanBaku = namaBahanBaku;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
