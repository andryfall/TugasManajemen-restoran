package com.mycompany.restoran;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AdminTest {

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin();
    }

    @Test
    public void testTambahBahanBaku() {
        BahanBaku bahan = new BahanBaku("Gula", 5000);
        admin.tambahBahanBaku(bahan);

        assertEquals(1, admin.getBahanBakuList().size());
    }

    @Test
    public void testTambahMenu() {
        Menu menu = new Menu("Nasi Goreng", 15000);
        admin.tambahMenu(menu);
        
        ArrayList<Menu> menuList = admin.getMenuList();
        assertEquals(1, menuList.size());
        assertEquals("Nasi Goreng", menuList.get(0).getNamaMenu());
        assertEquals(15000, menuList.get(0).getHarga());
    }

    @Test
    public void testHapusMenu() {
        Menu menu = new Menu("Nasi Goreng", 15000);
        admin.tambahMenu(menu);
        admin.hapusMenu(menu);
        
        ArrayList<Menu> menuList = admin.getMenuList();
        assertTrue(menuList.isEmpty());
    }

    @Test
    public void testUbahMenu() {
        Menu menu = new Menu("Nasi Goreng", 15000);
        admin.tambahMenu(menu);
        admin.ubahMenu(menu, "Mie Goreng", 12000);
        
        ArrayList<Menu> menuList = admin.getMenuList();
        assertEquals(1, menuList.size());
        assertEquals("Mie Goreng", menuList.get(0).getNamaMenu());
        assertEquals(12000, menuList.get(0).getHarga());
    }

    @Test
    public void testGetTotalHargaBahanBaku() {
        BahanBaku bahan1 = new BahanBaku("Gula", 5000);
        BahanBaku bahan2 = new BahanBaku("Tepung", 10000);
        admin.tambahBahanBaku(bahan1);
        admin.tambahBahanBaku(bahan2);
        
        int totalHarga = admin.getTotalHargaBahanBaku();
        assertEquals(15000, totalHarga);
    }
}