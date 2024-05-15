/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.restoran;

/**
 *
 * @author Habli
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Restoran {

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        Kasir kasir = new Kasir(admin);

        BahanBaku bahan1 = new BahanBaku("Gula", 5000);
        BahanBaku bahan2 = new BahanBaku("Tepung", 10000);
        admin.tambahBahanBaku(bahan1);
        admin.tambahBahanBaku(bahan2);

        Menu menu1 = new Menu("Nasi Goreng", 15000);
        Menu menu2 = new Menu("Mie Goreng", 12000);
        admin.tambahMenu(menu1);
        admin.tambahMenu(menu2);

        boolean exit = false;
        while (!exit) {
            System.out.println("=== RESTORAN MENU ===");
            System.out.println("1. Lihat daftar menu");
            System.out.println("2. Tambah menu baru");
            System.out.println("3. Ubah menu");
            System.out.println("4. Hapus menu");
            System.out.println("5. Catat transaksi");
            System.out.println("6. Akumulasi transaksi");
            System.out.println("7. Lihat transaksi bulanan");
            System.out.println("8. add pengeluaran");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu (0-8): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the leftover newline character

            switch (choice) {
                case 1:
                    System.out.println("Daftar Menu:");
                    ArrayList<Menu> menuList = admin.getMenuList();
                    for (Menu menu : menuList) {
                        System.out.println(menu.getNamaMenu() + "\tRp. " + menu.getHarga());
                    }
                    break;
                case 2:
                    System.out.print("Masukkan nama menu baru: ");
                    String namaMenuBaru = scanner.nextLine();
                    System.out.print("Masukkan harga menu baru: ");
                    int hargaMenuBaru = Integer.parseInt(scanner.nextLine());
                    Menu menuBaru = new Menu(namaMenuBaru, hargaMenuBaru);
                    admin.tambahMenu(menuBaru);
                    System.out.println("Menu berhasil ditambahkan.");
                    break;
                case 3:
                    System.out.print("Pilih menu yang ingin diubah (Pilih dengan angka): ");
                    int pilihan = Integer.parseInt(scanner.nextLine());
                    Menu menu = admin.getMenuList().get(pilihan - 1);
                    System.out.print("Nama menu baru: ");
                    String namaMenu = scanner.nextLine();
                    System.out.print("Harga menu baru: ");
                    int harga = Integer.parseInt(scanner.nextLine());
                    admin.ubahMenu(menu, namaMenu, harga);
                    System.out.println("Menu berhasil diubah.");
                    break;

                case 4:
                    System.out.print("Pilih menu yang ingin dihapus (Pilih dengan angka): ");
                    pilihan = Integer.parseInt(scanner.nextLine());
                    menu = admin.getMenuList().get(pilihan - 1);
                    admin.hapusMenu(menu);
                    System.out.println("Menu berhasil dihapus.");
                    break;

                case 5:
                    ArrayList<Menu> menuList5 = admin.getMenuList();
                    System.out.print("Masukkan nama menu: ");
                    String namaMenu5 = scanner.nextLine();
                    System.out.print("Berapa: ");
                    int berapa = scanner.nextInt();
                    Menu menu5 = null;
                    for (Menu m : menuList5) {
                        if (m.getNamaMenu().equalsIgnoreCase(namaMenu5)) {
                            menu5 = m;
                            break;
                        }
                    }
                    if (menu5 != null) {
                        int harga5 = menu5.getHarga();
                        for (int i = 0; i < berapa; i++){
                            kasir.catatTransaksi(namaMenu5, harga5);
                        }
                        System.out.println("Transaksi berhasil dicatat.");
                    } else {
                        System.out.println("Menu dengan nama " + namaMenu5 + " tidak ditemukan.");
                    }
                    break;
                case 6:
                    kasir.akumulasiTransaksi();
                    System.out.println("Transaksi berhasil diakumulasi.");
                    break;
                case 7:
                    // Lihat Transaksi Bulanan
                    ArrayList<HashMap<String, Integer>> transaksiBulanan = kasir.getTransaksiBulanan();
                    for (int i = 0; i < transaksiBulanan.size(); i++) {
                        HashMap<String, Integer> transaksiHarian = transaksiBulanan.get(i);
                        for (String menuu : transaksiHarian.keySet()) {
                            int jumlah = transaksiHarian.get(menuu);
                            System.out.println(menuu + "\tRp. " + jumlah);
                        }
                    }
                    break;
                case 8:
                    System.out.print("Masukkan nama bahan makanan baru: ");
                    String namaBahanBaru = scanner.nextLine();
                    System.out.print("Masukkan harga bahan makanan baru: ");
                    int hargaBahanBaru = Integer.parseInt(scanner.nextLine());
                    BahanBaku bahanBaru = new BahanBaku(namaBahanBaru, hargaBahanBaru);
                    admin.tambahBahanBaku(bahanBaru);
                    System.out.println("Bahan makanan berhasil ditambahkan.");
                    break;
                case 0:
                    // Keluar
                    exit = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
        }
    }

    scanner.close();
    }
}
