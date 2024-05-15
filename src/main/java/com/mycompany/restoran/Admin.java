/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restoran;

/**
 *
 * @author Habli
 */
import java.util.ArrayList;

public class Admin {
    private ArrayList<BahanBaku> bahanBakuList = new ArrayList<BahanBaku>();
    private ArrayList<Menu> menuList = new ArrayList<Menu>();

    public void tambahBahanBaku(BahanBaku bahanBaku) {
        bahanBakuList.add(bahanBaku);
    }

    public void tambahMenu(Menu menu) {
        menuList.add(menu);
    }

    public void hapusMenu(Menu menu) {
        menuList.remove(menu);
    }

    public void ubahMenu(Menu menu, String namaMenu, int harga) {
        menu.setNamaMenu(namaMenu);
        menu.setHarga(harga);
    }

    public ArrayList<BahanBaku> getBahanBakuList() {
        return bahanBakuList;
    }

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }
    
    public int getTotalHargaBahanBaku() {
        int totalHarga = 0;
        for (BahanBaku bahanBaku : bahanBakuList) {
            totalHarga += bahanBaku.getHarga();
        }
        return totalHarga;
    }
}
