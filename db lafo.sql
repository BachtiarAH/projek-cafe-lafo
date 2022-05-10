-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 10 Bulan Mei 2022 pada 16.28
-- Versi server: 10.4.21-MariaDB
-- Versi PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_banaj`
--
CREATE DATABASE IF NOT EXISTS `db_banaj` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db_banaj`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `beli_product`
--

CREATE TABLE `beli_product` (
  `id_beliProduct` varchar(11) NOT NULL,
  `supplier` varchar(8) NOT NULL,
  `tanggal_beliProduct` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_beli_product`
--

CREATE TABLE `detail_beli_product` (
  `id_beliProduct` varchar(11) NOT NULL,
  `jumlahBeli` int(32) NOT NULL,
  `product` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_retur`
--

CREATE TABLE `detail_retur` (
  `id_returSupplier` varchar(11) NOT NULL,
  `product` varchar(32) NOT NULL,
  `jumlah_rusak` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `detail_retur`
--

INSERT INTO `detail_retur` (`id_returSupplier`, `product`, `jumlah_rusak`) VALUES
('TR96146366', 'SMO0003', 1),
('TR62166744', 'SBN0001', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` varchar(11) NOT NULL,
  `kode_product` varchar(8) NOT NULL,
  `sub_total` int(32) NOT NULL,
  `qty` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Trigger `detail_transaksi`
--
DELIMITER $$
CREATE TRIGGER `kurang_stok` BEFORE INSERT ON `detail_transaksi` FOR EACH ROW update product
set stok=stok-new.qty
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `kode_kategori` varchar(8) NOT NULL,
  `nama_kategori` varchar(64) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`kode_kategori`, `nama_kategori`, `create_at`, `update_at`) VALUES
('K001', 'sampo ', '2022-04-20 12:41:27', '2022-04-20 12:41:27'),
('K002', 'sabun', '2022-04-20 12:41:38', '2022-04-20 12:41:38'),
('K003', 'parfume', '2022-04-20 12:41:51', '2022-04-20 12:41:51');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `id_pegawai` varchar(8) NOT NULL,
  `nama_pegawai` varchar(64) NOT NULL,
  `username` varchar(32) NOT NULL,
  `create_at` datetime NOT NULL,
  `update_at` datetime NOT NULL,
  `role` int(2) NOT NULL,
  `status` enum('Aktive','Tidak Aktive') NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `nama_pegawai`, `username`, `create_at`, `update_at`, `role`, `status`, `password`) VALUES
('pgw001', 'ucup', 'ucup', '2022-03-23 15:37:52', '2022-03-23 15:37:52', 0, 'Aktive', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE `product` (
  `kode_product` varchar(8) NOT NULL,
  `nama_product` varchar(64) NOT NULL,
  `stok` int(32) NOT NULL,
  `harga_beli` int(32) NOT NULL,
  `harga_jual` int(32) NOT NULL,
  `supplier` varchar(8) NOT NULL,
  `kategori` varchar(8) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `update_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `rusak` int(32) DEFAULT 0,
  `total_stok` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`kode_product`, `nama_product`, `stok`, `harga_beli`, `harga_jual`, `supplier`, `kategori`, `create_at`, `update_at`, `rusak`, `total_stok`) VALUES
('SBN0001', 'sabun ke 1', 99, 1000, 1001, 'S001', 'K002', '2022-04-20 05:42:43', '2022-04-20 05:42:43', 1, 100);

--
-- Trigger `product`
--
DELIMITER $$
CREATE TRIGGER `delete_retur` AFTER UPDATE ON `product` FOR EACH ROW DELETE from detail_retur where jumlah_rusak =0
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `retur_customer`
--

CREATE TABLE `retur_customer` (
  `id_transaksi` varchar(11) NOT NULL,
  `tanggal_retur` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `retur_supplier`
--

CREATE TABLE `retur_supplier` (
  `kode_supplier` varchar(8) NOT NULL,
  `tanggal_rtr` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_returSupplier` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `retur_supplier`
--

INSERT INTO `retur_supplier` (`kode_supplier`, `tanggal_rtr`, `id_returSupplier`) VALUES
('S001', '2022-04-20 05:42:44', 'TR62166744'),
('S001', '2022-04-20 05:11:12', 'TR96146366');

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

CREATE TABLE `supplier` (
  `kode_supplier` varchar(8) NOT NULL,
  `nama_supplier` varchar(64) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`kode_supplier`, `nama_supplier`, `create_at`, `update_at`) VALUES
('S001', 'banaj supplier 1', '2022-04-19 20:00:44', '2022-04-19 20:00:44'),
('S002', 'banaj suplier 2', '2022-04-19 20:00:53', '2022-04-19 20:00:53'),
('S003', 'banaj supplier 3', '2022-04-19 20:01:02', '2022-04-19 20:01:02'),
('S004', 'supplier 4', '2022-04-19 20:02:34', '2022-04-19 20:02:34'),
('S005', 'supplier 5', '2022-04-19 20:02:47', '2022-04-19 20:02:47');

-- --------------------------------------------------------

--
-- Struktur dari tabel `toko`
--

CREATE TABLE `toko` (
  `id_toko` varchar(8) NOT NULL,
  `nama_toko` varchar(64) NOT NULL,
  `alamat_toko` varchar(128) NOT NULL,
  `create_at` datetime NOT NULL,
  `update_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(11) NOT NULL,
  `tanggal_transaksi` datetime NOT NULL,
  `grand_total` int(32) NOT NULL,
  `bayar` int(32) NOT NULL,
  `id_pegawai` varchar(8) NOT NULL,
  `kembali` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tanggal_transaksi`, `grand_total`, `bayar`, `id_pegawai`, `kembali`) VALUES
('tr0012', '2022-03-23 15:38:26', 100000, 1000000, 'pgw001', 0);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `beli_product`
--
ALTER TABLE `beli_product`
  ADD PRIMARY KEY (`id_beliProduct`),
  ADD KEY `supplier` (`supplier`);

--
-- Indeks untuk tabel `detail_beli_product`
--
ALTER TABLE `detail_beli_product`
  ADD KEY `id_beliProduct` (`id_beliProduct`),
  ADD KEY `product` (`product`);

--
-- Indeks untuk tabel `detail_retur`
--
ALTER TABLE `detail_retur`
  ADD KEY `retur_sup` (`id_returSupplier`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `kode_product` (`kode_product`);

--
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kode_kategori`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_pegawai`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indeks untuk tabel `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`kode_product`),
  ADD KEY `supplier` (`supplier`),
  ADD KEY `kategori` (`kategori`);

--
-- Indeks untuk tabel `retur_customer`
--
ALTER TABLE `retur_customer`
  ADD KEY `id_transaksi` (`id_transaksi`);

--
-- Indeks untuk tabel `retur_supplier`
--
ALTER TABLE `retur_supplier`
  ADD PRIMARY KEY (`id_returSupplier`),
  ADD KEY `rtr_sup` (`kode_supplier`);

--
-- Indeks untuk tabel `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`kode_supplier`);

--
-- Indeks untuk tabel `toko`
--
ALTER TABLE `toko`
  ADD PRIMARY KEY (`id_toko`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `beli_product`
--
ALTER TABLE `beli_product`
  ADD CONSTRAINT `beli_product_ibfk_2` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`kode_supplier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_beli_product`
--
ALTER TABLE `detail_beli_product`
  ADD CONSTRAINT `detail_beli_product_ibfk_1` FOREIGN KEY (`id_beliProduct`) REFERENCES `beli_product` (`id_beliProduct`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_beli_product_ibfk_2` FOREIGN KEY (`product`) REFERENCES `product` (`kode_product`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_retur`
--
ALTER TABLE `detail_retur`
  ADD CONSTRAINT `retur_sup` FOREIGN KEY (`id_returSupplier`) REFERENCES `retur_supplier` (`id_returSupplier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`kode_product`) REFERENCES `product` (`kode_product`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`kode_supplier`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`kategori`) REFERENCES `kategori` (`kode_kategori`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `retur_customer`
--
ALTER TABLE `retur_customer`
  ADD CONSTRAINT `retur_customer_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `retur_supplier`
--
ALTER TABLE `retur_supplier`
  ADD CONSTRAINT `rtr_sup` FOREIGN KEY (`kode_supplier`) REFERENCES `supplier` (`kode_supplier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id_pegawai`);
--
-- Database: `db_lafo`
--
CREATE DATABASE IF NOT EXISTS `db_lafo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db_lafo`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `akun`
--

CREATE TABLE `akun` (
  `Username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `Id_Pegawai` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `akun`
--

INSERT INTO `akun` (`Username`, `password`, `Id_Pegawai`) VALUES
('bakti', '1234', 'ADM05052201'),
('bilqis', '1234', 'PGW05052201');

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kode_Barang` char(6) NOT NULL,
  `Nama_barang` varchar(50) NOT NULL,
  `satuan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kode_Barang`, `Nama_barang`, `satuan`) VALUES
('BRG001', 'Teh celup', 'buah'),
('BRG002', 'Kopi bubuk', 'gram');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_suplai`
--

CREATE TABLE `detail_suplai` (
  `harga_beli` float NOT NULL,
  `qty` float NOT NULL,
  `Id_detail_suplai` char(13) NOT NULL,
  `satuan` varchar(10) NOT NULL,
  `Kode_Menyuplai` char(13) NOT NULL,
  `kode_Barang` char(6) NOT NULL,
  `stok` float NOT NULL,
  `harga_beli_per_satuan` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `detail_suplai`
--

INSERT INTO `detail_suplai` (`harga_beli`, `qty`, `Id_detail_suplai`, `satuan`, `Kode_Menyuplai`, `kode_Barang`, `stok`, `harga_beli_per_satuan`) VALUES
(6000, 12, 'DSUP050522001', 'buah', 'MSP0505220001', 'BRG001', 12, 500),
(20000, 1000, 'DSUP050522002', 'gram', 'MSP0505220001', 'BRG002', 1000, 20);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `qty` float NOT NULL,
  `sub_total` float NOT NULL,
  `harga` float NOT NULL,
  `detail_transaksi` char(14) NOT NULL,
  `kode_Menu` char(5) NOT NULL,
  `kode_transaksi` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `diskon`
--

CREATE TABLE `diskon` (
  `kode_diskon` char(13) NOT NULL,
  `jumlah_diskon` float NOT NULL,
  `tenggat_diskon` date DEFAULT NULL,
  `nama` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `diskon`
--

INSERT INTO `diskon` (`kode_diskon`, `jumlah_diskon`, `tenggat_diskon`, `nama`) VALUES
('HARGANORMAL', 0, NULL, 'harga normal');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE `menu` (
  `kode_Menu` char(5) NOT NULL,
  `Nama_Menu` varchar(25) NOT NULL,
  `Harga` float NOT NULL,
  `Kategori` char(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `menu`
--

INSERT INTO `menu` (`kode_Menu`, `Nama_Menu`, `Harga`, `Kategori`) VALUES
('MN001', 'es teh', 4000, 'minuman'),
('MN002', 'teh panas', 4000, 'minuman'),
('MN003', 'kopi hitam', 5000, 'minuman');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menyuplai`
--

CREATE TABLE `menyuplai` (
  `Kode_Menyuplai` char(13) NOT NULL,
  `Tanggal_menyuplai` date NOT NULL,
  `kode_suplaier` char(11) NOT NULL,
  `Id_Pegawai` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `menyuplai`
--

INSERT INTO `menyuplai` (`Kode_Menyuplai`, `Tanggal_menyuplai`, `kode_suplaier`, `Id_Pegawai`) VALUES
('MSP0505220001', '2022-05-05', 'SUP05052202', 'PGW05052201');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `Id_Pegawai` char(11) NOT NULL,
  `Nama_Pegawai` varchar(50) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `No_Hp` char(13) NOT NULL,
  `Tanggal_Terdaftar` date NOT NULL,
  `status` varchar(10) NOT NULL,
  `hak_akses` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`Id_Pegawai`, `Nama_Pegawai`, `gender`, `Alamat`, `No_Hp`, `Tanggal_Terdaftar`, `status`, `hak_akses`) VALUES
('ADM05052201', 'bakti', 'L', 'Madiun ', '081222333444', '2022-05-05', 'AKTIF', 'ADMIN'),
('PGW05052201', 'Bilqis', 'P', 'Madiun', '082444555666', '2022-05-05', 'AKTIF', 'PEGAWAI');

-- --------------------------------------------------------

--
-- Struktur dari tabel `resep`
--

CREATE TABLE `resep` (
  `qty` float NOT NULL,
  `kode_Barang` char(6) NOT NULL,
  `kode_Menu` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `resep`
--

INSERT INTO `resep` (`qty`, `kode_Barang`, `kode_Menu`) VALUES
(1, 'BRG001', 'MN001'),
(1, 'BRG001', 'MN002'),
(20, 'BRG002', 'MN003');

-- --------------------------------------------------------

--
-- Struktur dari tabel `retur`
--

CREATE TABLE `retur` (
  `kode_Retur` char(13) NOT NULL,
  `qty` float NOT NULL,
  `Id_detail_suplai` char(13) NOT NULL,
  `kode_barang` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Trigger `retur`
--
DELIMITER $$
CREATE TRIGGER `kurangi_stok` AFTER INSERT ON `retur` FOR EACH ROW BEGIN

UPDATE detail_suplai SET 
detail_suplai.qty = detail_suplai.qty - new.qty

WHERE 
new.kode_barang = detail_suplai.kode_Barang
AND
new.Id_detail_suplai = detail_suplai.Id_detail_suplai;

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `suplier`
--

CREATE TABLE `suplier` (
  `kode_suplaier` char(11) NOT NULL,
  `No_Telp` char(13) NOT NULL,
  `Alamat` varchar(225) NOT NULL,
  `nama_suplier` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `suplier`
--

INSERT INTO `suplier` (`kode_suplaier`, `No_Telp`, `Alamat`, `nama_suplier`) VALUES
('SUP05052201', '084637216482', 'JEMBER', 'RUMAH BAKTI'),
('SUP05052202', '084726384231', 'MADIUN', 'Toko Grosir Bu Anik');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_penjualan`
--

CREATE TABLE `transaksi_penjualan` (
  `kode_transaksi` char(13) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `uangPelanggan` float NOT NULL,
  `Total` float NOT NULL,
  `Kembalian` float NOT NULL,
  `Id_Pegawai` char(11) NOT NULL,
  `kode_diskon` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `akun`
--
ALTER TABLE `akun`
  ADD KEY `Id_Pegawai` (`Id_Pegawai`);

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_Barang`);

--
-- Indeks untuk tabel `detail_suplai`
--
ALTER TABLE `detail_suplai`
  ADD PRIMARY KEY (`Id_detail_suplai`),
  ADD KEY `Kode_Menyuplai` (`Kode_Menyuplai`),
  ADD KEY `kode_Barang` (`kode_Barang`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD KEY `kode_Menu` (`kode_Menu`),
  ADD KEY `kode_transaksi` (`kode_transaksi`);

--
-- Indeks untuk tabel `diskon`
--
ALTER TABLE `diskon`
  ADD PRIMARY KEY (`kode_diskon`);

--
-- Indeks untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`kode_Menu`);

--
-- Indeks untuk tabel `menyuplai`
--
ALTER TABLE `menyuplai`
  ADD PRIMARY KEY (`Kode_Menyuplai`),
  ADD KEY `kode_suplaier` (`kode_suplaier`),
  ADD KEY `Id_Pegawai` (`Id_Pegawai`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`Id_Pegawai`);

--
-- Indeks untuk tabel `resep`
--
ALTER TABLE `resep`
  ADD KEY `kode_Barang` (`kode_Barang`),
  ADD KEY `kode_Menu` (`kode_Menu`);

--
-- Indeks untuk tabel `retur`
--
ALTER TABLE `retur`
  ADD PRIMARY KEY (`kode_Retur`),
  ADD KEY `Id_detail_suplai` (`Id_detail_suplai`);

--
-- Indeks untuk tabel `suplier`
--
ALTER TABLE `suplier`
  ADD PRIMARY KEY (`kode_suplaier`);

--
-- Indeks untuk tabel `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD PRIMARY KEY (`kode_transaksi`),
  ADD KEY `Id_Pegawai` (`Id_Pegawai`),
  ADD KEY `kode_diskon` (`kode_diskon`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `akun`
--
ALTER TABLE `akun`
  ADD CONSTRAINT `akun_ibfk_1` FOREIGN KEY (`Id_Pegawai`) REFERENCES `pegawai` (`Id_Pegawai`);

--
-- Ketidakleluasaan untuk tabel `detail_suplai`
--
ALTER TABLE `detail_suplai`
  ADD CONSTRAINT `detail_suplai_ibfk_1` FOREIGN KEY (`Kode_Menyuplai`) REFERENCES `menyuplai` (`Kode_Menyuplai`),
  ADD CONSTRAINT `detail_suplai_ibfk_2` FOREIGN KEY (`kode_Barang`) REFERENCES `barang` (`kode_Barang`);

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`kode_Menu`) REFERENCES `menu` (`kode_Menu`),
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`kode_transaksi`) REFERENCES `transaksi_penjualan` (`kode_transaksi`);

--
-- Ketidakleluasaan untuk tabel `menyuplai`
--
ALTER TABLE `menyuplai`
  ADD CONSTRAINT `menyuplai_ibfk_1` FOREIGN KEY (`kode_suplaier`) REFERENCES `suplier` (`kode_suplaier`),
  ADD CONSTRAINT `menyuplai_ibfk_2` FOREIGN KEY (`Id_Pegawai`) REFERENCES `pegawai` (`Id_Pegawai`);

--
-- Ketidakleluasaan untuk tabel `resep`
--
ALTER TABLE `resep`
  ADD CONSTRAINT `resep_ibfk_1` FOREIGN KEY (`kode_Barang`) REFERENCES `barang` (`kode_Barang`),
  ADD CONSTRAINT `resep_ibfk_2` FOREIGN KEY (`kode_Menu`) REFERENCES `menu` (`kode_Menu`);

--
-- Ketidakleluasaan untuk tabel `retur`
--
ALTER TABLE `retur`
  ADD CONSTRAINT `retur_ibfk_1` FOREIGN KEY (`Id_detail_suplai`) REFERENCES `detail_suplai` (`Id_detail_suplai`);

--
-- Ketidakleluasaan untuk tabel `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD CONSTRAINT `transaksi_penjualan_ibfk_1` FOREIGN KEY (`Id_Pegawai`) REFERENCES `pegawai` (`Id_Pegawai`),
  ADD CONSTRAINT `transaksi_penjualan_ibfk_2` FOREIGN KEY (`kode_diskon`) REFERENCES `diskon` (`kode_diskon`);
--
-- Database: `db_lavo`
--
CREATE DATABASE IF NOT EXISTS `db_lavo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db_lavo`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kode_Barang` char(6) NOT NULL,
  `Nama_barang` varchar(50) NOT NULL,
  `kode_Kategori` char(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_suplai`
--

CREATE TABLE `detail_suplai` (
  `harga_beli` float NOT NULL,
  `qty` float NOT NULL,
  `Id_detail_suplai` char(13) NOT NULL,
  `Kode_Menyuplai` char(13) NOT NULL,
  `kode_Barang` char(6) NOT NULL,
  `kode_satuan` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Trigger `detail_suplai`
--
DELIMITER $$
CREATE TRIGGER `log_detail_suplier` AFTER INSERT ON `detail_suplai` FOR EACH ROW BEGIN

INSERT INTO log_datail_suplai
(`Id_Detail_sulplai`, `harga_beli`, `qty`, `Kode_Menyuplai`, `kode_Barang`, `kode_satuan`)
VALUES 
(new.Id_detail_suplai, new.harga_beli, new.qty, new.Kode_Menyuplai, new.kode_Barang, new.kode_satuan);

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `qty` float NOT NULL,
  `sub_total` float NOT NULL,
  `harga` float NOT NULL,
  `detail_transaksi` char(14) NOT NULL,
  `kode_Menu` char(5) NOT NULL,
  `kode_transaksi` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `diskon`
--

CREATE TABLE `diskon` (
  `kode_diskon` char(13) NOT NULL,
  `jumlah_diskon` float NOT NULL,
  `tenggat_diskon` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `hak_akses`
--

CREATE TABLE `hak_akses` (
  `kode_hak_akses` char(1) NOT NULL,
  `hak_akses` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `jenis_diskon`
--

CREATE TABLE `jenis_diskon` (
  `kode_jenis_diskon` char(5) NOT NULL,
  `jenis_diskon` varchar(25) NOT NULL,
  `kode_diskon` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori_barang`
--

CREATE TABLE `kategori_barang` (
  `kode_Kategori` char(3) NOT NULL,
  `kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori_menu`
--

CREATE TABLE `kategori_menu` (
  `Kode_Kategori` char(2) NOT NULL,
  `Kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `log_datail_suplai`
--

CREATE TABLE `log_datail_suplai` (
  `Id_Detail_sulplai` char(13) NOT NULL,
  `harga_beli` float NOT NULL,
  `qty` float NOT NULL,
  `Kode_Menyuplai` char(13) NOT NULL,
  `kode_Barang` char(6) NOT NULL,
  `kode_satuan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE `menu` (
  `kode_Menu` char(5) NOT NULL,
  `Nama_Menu` varchar(25) NOT NULL,
  `Harga` float NOT NULL,
  `Kode_Kategori` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `menyuplai`
--

CREATE TABLE `menyuplai` (
  `Kode_Menyuplai` char(13) NOT NULL,
  `Tanggal_menyuplai` date NOT NULL,
  `kode_suplaier` char(11) NOT NULL,
  `Id_Pegawai` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `resep`
--

CREATE TABLE `resep` (
  `qty` float NOT NULL,
  `kode_Barang` char(6) NOT NULL,
  `kode_Menu` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `retur`
--

CREATE TABLE `retur` (
  `kode_Retur` char(13) NOT NULL,
  `qty` float NOT NULL,
  `Id_detail_suplai` char(13) NOT NULL,
  `kode_barang` char(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Trigger `retur`
--
DELIMITER $$
CREATE TRIGGER `kurangi_stok(retur)` AFTER INSERT ON `retur` FOR EACH ROW BEGIN

UPDATE detail_suplai SET 
detail_suplai.qty = detail_suplai.qty - new.qty

WHERE 
new.kode_barang = detail_suplai.kode_Barang
AND
new.Id_detail_suplai = detail_suplai.Id_detail_suplai;

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `satuan_kuantiti`
--

CREATE TABLE `satuan_kuantiti` (
  `kode_satuan` char(2) NOT NULL,
  `nama_Satuan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `status`
--

CREATE TABLE `status` (
  `kode_status` char(2) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `suplier`
--

CREATE TABLE `suplier` (
  `kode_suplaier` char(11) NOT NULL,
  `No_Telp` char(13) NOT NULL,
  `Alamat` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_penjualan`
--

CREATE TABLE `transaksi_penjualan` (
  `kode_transaksi` char(13) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `uangPelanggan` float NOT NULL,
  `Total` float NOT NULL,
  `Kembalian` float NOT NULL,
  `Id_Pegawai` char(11) NOT NULL,
  `kode_diskon` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `Id_Pegawai` char(11) NOT NULL,
  `Nama_Pegawai` varchar(50) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `No_Hp` char(13) NOT NULL,
  `Tanggal_Terdaftar` int(11) NOT NULL,
  `kode_hak_akses` char(1) NOT NULL,
  `kode_status` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_Barang`),
  ADD KEY `kode_Kategori` (`kode_Kategori`);

--
-- Indeks untuk tabel `detail_suplai`
--
ALTER TABLE `detail_suplai`
  ADD PRIMARY KEY (`Id_detail_suplai`),
  ADD KEY `Kode_Menyuplai` (`Kode_Menyuplai`),
  ADD KEY `kode_Barang` (`kode_Barang`),
  ADD KEY `kode_satuan` (`kode_satuan`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD KEY `kode_Menu` (`kode_Menu`),
  ADD KEY `kode_transaksi` (`kode_transaksi`);

--
-- Indeks untuk tabel `diskon`
--
ALTER TABLE `diskon`
  ADD PRIMARY KEY (`kode_diskon`);

--
-- Indeks untuk tabel `hak_akses`
--
ALTER TABLE `hak_akses`
  ADD PRIMARY KEY (`kode_hak_akses`);

--
-- Indeks untuk tabel `jenis_diskon`
--
ALTER TABLE `jenis_diskon`
  ADD PRIMARY KEY (`kode_jenis_diskon`),
  ADD KEY `kode_diskon` (`kode_diskon`);

--
-- Indeks untuk tabel `kategori_barang`
--
ALTER TABLE `kategori_barang`
  ADD PRIMARY KEY (`kode_Kategori`);

--
-- Indeks untuk tabel `kategori_menu`
--
ALTER TABLE `kategori_menu`
  ADD PRIMARY KEY (`Kode_Kategori`);

--
-- Indeks untuk tabel `log_datail_suplai`
--
ALTER TABLE `log_datail_suplai`
  ADD PRIMARY KEY (`Id_Detail_sulplai`);

--
-- Indeks untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`kode_Menu`),
  ADD KEY `Kode_Kategori` (`Kode_Kategori`);

--
-- Indeks untuk tabel `menyuplai`
--
ALTER TABLE `menyuplai`
  ADD PRIMARY KEY (`Kode_Menyuplai`),
  ADD KEY `kode_suplaier` (`kode_suplaier`),
  ADD KEY `Id_Pegawai` (`Id_Pegawai`);

--
-- Indeks untuk tabel `resep`
--
ALTER TABLE `resep`
  ADD KEY `kode_Barang` (`kode_Barang`),
  ADD KEY `kode_Menu` (`kode_Menu`);

--
-- Indeks untuk tabel `retur`
--
ALTER TABLE `retur`
  ADD PRIMARY KEY (`kode_Retur`),
  ADD KEY `Id_detail_suplai` (`Id_detail_suplai`);

--
-- Indeks untuk tabel `satuan_kuantiti`
--
ALTER TABLE `satuan_kuantiti`
  ADD PRIMARY KEY (`kode_satuan`);

--
-- Indeks untuk tabel `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`kode_status`);

--
-- Indeks untuk tabel `suplier`
--
ALTER TABLE `suplier`
  ADD PRIMARY KEY (`kode_suplaier`);

--
-- Indeks untuk tabel `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD PRIMARY KEY (`kode_transaksi`),
  ADD KEY `Id_Pegawai` (`Id_Pegawai`),
  ADD KEY `kode_diskon` (`kode_diskon`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Id_Pegawai`),
  ADD KEY `kode_hak_akses` (`kode_hak_akses`),
  ADD KEY `kode_status` (`kode_status`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`kode_Kategori`) REFERENCES `kategori_barang` (`kode_Kategori`);

--
-- Ketidakleluasaan untuk tabel `detail_suplai`
--
ALTER TABLE `detail_suplai`
  ADD CONSTRAINT `detail_suplai_ibfk_1` FOREIGN KEY (`Kode_Menyuplai`) REFERENCES `menyuplai` (`Kode_Menyuplai`),
  ADD CONSTRAINT `detail_suplai_ibfk_2` FOREIGN KEY (`kode_Barang`) REFERENCES `barang` (`kode_Barang`),
  ADD CONSTRAINT `detail_suplai_ibfk_3` FOREIGN KEY (`kode_satuan`) REFERENCES `satuan_kuantiti` (`kode_satuan`);

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`kode_Menu`) REFERENCES `menu` (`kode_Menu`),
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`kode_transaksi`) REFERENCES `transaksi_penjualan` (`kode_transaksi`);

--
-- Ketidakleluasaan untuk tabel `jenis_diskon`
--
ALTER TABLE `jenis_diskon`
  ADD CONSTRAINT `jenis_diskon_ibfk_1` FOREIGN KEY (`kode_diskon`) REFERENCES `diskon` (`kode_diskon`);

--
-- Ketidakleluasaan untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`Kode_Kategori`) REFERENCES `kategori_menu` (`Kode_Kategori`);

--
-- Ketidakleluasaan untuk tabel `menyuplai`
--
ALTER TABLE `menyuplai`
  ADD CONSTRAINT `menyuplai_ibfk_1` FOREIGN KEY (`kode_suplaier`) REFERENCES `suplier` (`kode_suplaier`),
  ADD CONSTRAINT `menyuplai_ibfk_2` FOREIGN KEY (`Id_Pegawai`) REFERENCES `user` (`Id_Pegawai`);

--
-- Ketidakleluasaan untuk tabel `resep`
--
ALTER TABLE `resep`
  ADD CONSTRAINT `resep_ibfk_1` FOREIGN KEY (`kode_Barang`) REFERENCES `barang` (`kode_Barang`),
  ADD CONSTRAINT `resep_ibfk_2` FOREIGN KEY (`kode_Menu`) REFERENCES `menu` (`kode_Menu`);

--
-- Ketidakleluasaan untuk tabel `retur`
--
ALTER TABLE `retur`
  ADD CONSTRAINT `retur_ibfk_1` FOREIGN KEY (`Id_detail_suplai`) REFERENCES `detail_suplai` (`Id_detail_suplai`);

--
-- Ketidakleluasaan untuk tabel `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD CONSTRAINT `transaksi_penjualan_ibfk_1` FOREIGN KEY (`Id_Pegawai`) REFERENCES `user` (`Id_Pegawai`),
  ADD CONSTRAINT `transaksi_penjualan_ibfk_2` FOREIGN KEY (`kode_diskon`) REFERENCES `diskon` (`kode_diskon`);

--
-- Ketidakleluasaan untuk tabel `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`kode_hak_akses`) REFERENCES `hak_akses` (`kode_hak_akses`),
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`kode_status`) REFERENCES `status` (`kode_status`);
--
-- Database: `ek4tech`
--
CREATE DATABASE IF NOT EXISTS `ek4tech` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ek4tech`;
--
-- Database: `ek4_tech`
--
CREATE DATABASE IF NOT EXISTS `ek4_tech` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ek4_tech`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `akun`
--

CREATE TABLE `akun` (
  `password` varchar(25) NOT NULL,
  `id_pegawai` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `akun`
--

INSERT INTO `akun` (`password`, `id_pegawai`) VALUES
('admin', 'admin'),
('1234', '2'),
('1234', '3'),
('1234', '4'),
('1234', '8'),
('1234', '9');

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` char(5) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `harga` float NOT NULL,
  `jumlah` int(11) NOT NULL,
  `jenis` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `harga`, `jumlah`, `jenis`) VALUES
('1', 'router mikrotik', 500000, 94, 'aksesoris'),
('2', 'usbv', 100000, 187, 'kabel'),
('3', 'laptop asus', 5000000, 5, 'komputer'),
('6', 'handphone', 1000000, 3, 'komputer'),
('7', 'mouse', 10000, 10, 'aksesoris');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_trans`
--

CREATE TABLE `detail_trans` (
  `quantitas` int(11) NOT NULL,
  `total_harga` float NOT NULL,
  `id_transaksi` int(11) NOT NULL,
  `id_barang` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `detail_trans`
--

INSERT INTO `detail_trans` (`quantitas`, `total_harga`, `id_transaksi`, `id_barang`) VALUES
(1, 500000, 1, '1'),
(2, 500000, 2, '1'),
(1, 500000, 3, '1'),
(5, 100000, 3, '2'),
(5, 5000000, 3, '3'),
(2, 500000, 4, '1'),
(2, 100000, 5, '2'),
(3, 100000, 6, '2'),
(1, 100000, 7, '2'),
(2, 100000, 8, '2');

--
-- Trigger `detail_trans`
--
DELIMITER $$
CREATE TRIGGER `kurangistok` AFTER INSERT ON `detail_trans` FOR EACH ROW UPDATE barang
SET jumlah = jumlah - NEW.quantitas
WHERE id_barang = NEW.id_barang
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `jabatan`
--

CREATE TABLE `jabatan` (
  `jabata` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `jabatan`
--

INSERT INTO `jabatan` (`jabata`) VALUES
('admin'),
('kasir'),
('lain lain'),
('manager'),
('office boy'),
('security'),
('supir');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jenis`
--

CREATE TABLE `jenis` (
  `jenis` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `jenis`
--

INSERT INTO `jenis` (`jenis`) VALUES
('aksesoris'),
('asu'),
('bajingan'),
('casing'),
('jaringan'),
('kabel'),
('komputer'),
('lain lain');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `id_pegawai` char(5) NOT NULL,
  `nama_pegawai` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `notelp` varchar(13) NOT NULL,
  `jabata` varchar(20) NOT NULL,
  `keterangan` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `nama_pegawai`, `alamat`, `notelp`, `jabata`, `keterangan`) VALUES
('2', 'bachtiar', 'madiun', '082234429795', 'kasir', ''),
('3', 'aryai', 'banjarejo', '085111222333', 'office boy', 'aktif'),
('4', 'bilqi', '089327481239', 'manager', 'lain lain', ''),
('8', 'roni', 'jember', '085111222333', 'kasir', ''),
('9', 'zeni', 'madiun', '089123456789', 'kasir', 'aktif'),
('admin', 'admin', '', '', 'admin', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

CREATE TABLE `penjualan` (
  `id_transaksi` int(11) NOT NULL,
  `waktu` date NOT NULL,
  `total_dibayar` float NOT NULL,
  `id_pegawai` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `penjualan`
--

INSERT INTO `penjualan` (`id_transaksi`, `waktu`, `total_dibayar`, `id_pegawai`) VALUES
(1, '2021-12-13', 500000, 'admin'),
(2, '2021-12-13', 1000000, 'admin'),
(3, '2021-12-15', 26000000, 'admin'),
(4, '2022-01-16', 1000000, 'admin'),
(5, '2022-01-16', 200000, 'admin'),
(6, '2022-01-16', 600000, 'admin'),
(7, '2022-01-16', 100000, 'admin'),
(8, '2022-01-16', 200000, 'admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `akun`
--
ALTER TABLE `akun`
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD KEY `jenis` (`jenis`);

--
-- Indeks untuk tabel `detail_trans`
--
ALTER TABLE `detail_trans`
  ADD PRIMARY KEY (`id_transaksi`,`id_barang`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indeks untuk tabel `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`jabata`);

--
-- Indeks untuk tabel `jenis`
--
ALTER TABLE `jenis`
  ADD PRIMARY KEY (`jenis`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_pegawai`),
  ADD KEY `jabata` (`jabata`);

--
-- Indeks untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `akun`
--
ALTER TABLE `akun`
  ADD CONSTRAINT `akun_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id_pegawai`);

--
-- Ketidakleluasaan untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`jenis`) REFERENCES `jenis` (`jenis`);

--
-- Ketidakleluasaan untuk tabel `detail_trans`
--
ALTER TABLE `detail_trans`
  ADD CONSTRAINT `detail_trans_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `penjualan` (`id_transaksi`),
  ADD CONSTRAINT `detail_trans_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Ketidakleluasaan untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD CONSTRAINT `pegawai_ibfk_1` FOREIGN KEY (`jabata`) REFERENCES `jabatan` (`jabata`);

--
-- Ketidakleluasaan untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id_pegawai`);
--
-- Database: `kbd_minggu_12`
--
CREATE DATABASE IF NOT EXISTS `kbd_minggu_12` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `kbd_minggu_12`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tm_dosen`
--

CREATE TABLE `tm_dosen` (
  `NIP` char(18) NOT NULL,
  `nama_dos` varchar(25) DEFAULT NULL,
  `alamat_dos` varchar(100) DEFAULT NULL,
  `no_hp_dos` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tm_dosen`
--

INSERT INTO `tm_dosen` (`NIP`, `nama_dos`, `alamat_dos`, `no_hp_dos`) VALUES
('197111151998021001', 'Adi Heru Utomo', 'Jember', '085233111999'),
('198012122005011001', 'Prawidya Destarianto', 'Jember', '085233222999'),
('198511282008121002', 'Aji Seto Arifianto', 'Jember', '085233555999'),
('199205282018032001', 'Bety Etikasari', 'Jember', '085233333999'),
('199305102021032001', 'Lukie Perdanasari', 'Jember', '085233444999');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tm_mahasiswa`
--

CREATE TABLE `tm_mahasiswa` (
  `NIM` char(9) NOT NULL,
  `nama_mhs` varchar(50) DEFAULT NULL,
  `alamat_mhs` varchar(100) DEFAULT NULL,
  `no_hp_mhs` varchar(13) DEFAULT NULL,
  `NIP` char(18) DEFAULT NULL,
  `JK` enum('P','L') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tm_mahasiswa`
--

INSERT INTO `tm_mahasiswa` (`NIM`, `nama_mhs`, `alamat_mhs`, `no_hp_mhs`, `NIP`, `JK`) VALUES
('E41211266', 'Lusy Damayanti', 'Situbondo', '085233999111', '199305102021032001', 'P'),
('E41211320', 'Achmad Zakariya', 'Jember', '085233999222', '198511282008121002', 'L'),
('E41211762', 'Firmansyah Fikriawan', 'Banyuwangi', '085233777888', '199205282018032001', 'L'),
('E41212263', 'Zulfianti', 'Lumajang', '085233555666', '199205282018032001', 'P'),
('E41212357', 'Ahmad Syifa Ar Rizqi', 'Jember', '085233333444', '199305102021032001', 'L'),
('E41212368', 'Bhakti Dwi', 'Jember', '085233111222', '199205282018032001', 'L');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tm_matakuliah`
--

CREATE TABLE `tm_matakuliah` (
  `kode_mk` char(9) NOT NULL,
  `nama_mk` varchar(25) DEFAULT NULL,
  `sks` int(2) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tm_matakuliah`
--

INSERT INTO `tm_matakuliah` (`kode_mk`, `nama_mk`, `sks`, `semester`) VALUES
('TIF110704', 'Logika dan Algoritma', 2, 1),
('TIF110705', 'Konsep Basis Data', 2, 1),
('TIF110708', 'Workshop Basis Data', 4, 1),
('TIF120706', 'Perencanaan Proyek Perang', 2, 2),
('TIF120708', 'Workshop Manajemen Proyek', 4, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tr_ambil_mk`
--

CREATE TABLE `tr_ambil_mk` (
  `kode_mk` char(9) DEFAULT NULL,
  `NIM` char(9) DEFAULT NULL,
  `nilai` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tr_ambil_mk`
--

INSERT INTO `tr_ambil_mk` (`kode_mk`, `NIM`, `nilai`) VALUES
('TIF110705', 'E41212263', 90),
('TIF110708', 'E41212263', 85),
('TIF110705', 'E41212357', 80),
('TIF110705', 'E41211320', 91),
('TIF110704', 'E41211320', 85),
('TIF110704', 'E41212263', 70);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tr_pengampu`
--

CREATE TABLE `tr_pengampu` (
  `NIP` char(18) DEFAULT NULL,
  `kode_mk` char(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tr_pengampu`
--

INSERT INTO `tr_pengampu` (`NIP`, `kode_mk`) VALUES
('198012122005011001', 'TIF110705'),
('199205282018032001', 'TIF110705'),
('199305102021032001', 'TIF110708'),
('199205282018032001', 'TIF110708');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tm_dosen`
--
ALTER TABLE `tm_dosen`
  ADD PRIMARY KEY (`NIP`);

--
-- Indeks untuk tabel `tm_mahasiswa`
--
ALTER TABLE `tm_mahasiswa`
  ADD PRIMARY KEY (`NIM`),
  ADD KEY `NIP` (`NIP`);

--
-- Indeks untuk tabel `tm_matakuliah`
--
ALTER TABLE `tm_matakuliah`
  ADD PRIMARY KEY (`kode_mk`);

--
-- Indeks untuk tabel `tr_ambil_mk`
--
ALTER TABLE `tr_ambil_mk`
  ADD KEY `kode_mk` (`kode_mk`),
  ADD KEY `NIM` (`NIM`);

--
-- Indeks untuk tabel `tr_pengampu`
--
ALTER TABLE `tr_pengampu`
  ADD KEY `NIP` (`NIP`),
  ADD KEY `kode_mk` (`kode_mk`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tm_mahasiswa`
--
ALTER TABLE `tm_mahasiswa`
  ADD CONSTRAINT `tm_mahasiswa_ibfk_1` FOREIGN KEY (`NIP`) REFERENCES `tm_dosen` (`NIP`);

--
-- Ketidakleluasaan untuk tabel `tr_ambil_mk`
--
ALTER TABLE `tr_ambil_mk`
  ADD CONSTRAINT `tr_ambil_mk_ibfk_1` FOREIGN KEY (`kode_mk`) REFERENCES `tm_matakuliah` (`kode_mk`),
  ADD CONSTRAINT `tr_ambil_mk_ibfk_2` FOREIGN KEY (`NIM`) REFERENCES `tm_mahasiswa` (`NIM`);

--
-- Ketidakleluasaan untuk tabel `tr_pengampu`
--
ALTER TABLE `tr_pengampu`
  ADD CONSTRAINT `tr_pengampu_ibfk_1` FOREIGN KEY (`NIP`) REFERENCES `tm_dosen` (`NIP`),
  ADD CONSTRAINT `tr_pengampu_ibfk_2` FOREIGN KEY (`kode_mk`) REFERENCES `tm_matakuliah` (`kode_mk`);
--
-- Database: `mahasiswa`
--
CREATE DATABASE IF NOT EXISTS `mahasiswa` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `mahasiswa`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `akun`
--

CREATE TABLE `akun` (
  `nim` varchar(9) NOT NULL,
  `password` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `akun`
--

INSERT INTO `akun` (`nim`, `password`) VALUES
('1234', '1234'),
('A32210893', '1234'),
('A37066699', '1234'),
('B2121836', '1234'),
('B32210722', '1234'),
('C31219573', '1234'),
('C39646339', '1234'),
('D30718905', '1234'),
('D41216865', '1234'),
('E41211010', '1234'),
('E41211696', '1234'),
('E46397516', '1234');

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kodebrg` varchar(15) NOT NULL,
  `nama` varchar(15) DEFAULT NULL,
  `satuan` varchar(5) DEFAULT NULL,
  `harga_beli` int(11) DEFAULT NULL,
  `harga_jual` int(11) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `daftar_dosen`
--

CREATE TABLE `daftar_dosen` (
  `NIP` char(10) NOT NULL,
  `nama_dosen` varchar(50) NOT NULL,
  `no_hp` char(13) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `daftar_dosen`
--

INSERT INTO `daftar_dosen` (`NIP`, `nama_dosen`, `no_hp`, `alamat`) VALUES
('0160436012', 'Sabrina Sari', '0812349900', 'Pekanbaru'),
('0260432002', 'Maya Ari Putri', '0812345234', 'Palembang'),
('0275430005', 'Susi Indriani', '08126565234', 'Bogor'),
('0480432066', 'Tia Santrini', '0812451177', 'Padang'),
('0576431001', 'M. Siddiq', '0812979005', 'Jakarta'),
('0770435006', 'Rubin Hadi', '0812567678', 'Papua'),
('0869437003', 'Mustalifah', '0812338877', 'Aceh'),
('1080432007', 'Arif Budiman', '0812456345', 'Makasar'),
('1992052820', 'Bety Etikasari, S.Pd, M.Pd', '085233975628', 'Jember'),
('1993051020', 'Lukie Perdanasari, S.Kom., M.T.', '087757636646', 'jember');

--
-- Trigger `daftar_dosen`
--
DELIMITER $$
CREATE TRIGGER `before_delete_dosen` BEFORE DELETE ON `daftar_dosen` FOR EACH ROW BEGIN
INSERT INTO history_delet_dosen 
 SET nip = old.nip, 
 nama_dosen = old.nama_dosen, 
 alamat=old.alamat, 
 phone = old.no_hp,
 waktu = NOW();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `history_delet_dosen`
--

CREATE TABLE `history_delet_dosen` (
  `id_log` int(10) NOT NULL,
  `nip` char(18) DEFAULT NULL,
  `nama_dosen` varchar(25) DEFAULT NULL,
  `alamat` char(25) DEFAULT NULL,
  `phone` char(13) DEFAULT NULL,
  `waktu` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `history_delet_dosen`
--

INSERT INTO `history_delet_dosen` (`id_log`, `nip`, `nama_dosen`, `alamat`, `phone`, `waktu`) VALUES
(3, '1980051720', 'Dwi Putro Sarwo Setyohadi', 'Jember', '085111222333', '2021-12-10 14:33:46');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jual`
--

CREATE TABLE `jual` (
  `nofaktur` varchar(15) NOT NULL,
  `faktur` varchar(15) DEFAULT NULL,
  `tgl` date DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `bayar` int(11) DEFAULT NULL,
  `kembali` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `log_mahasiswa`
--

CREATE TABLE `log_mahasiswa` (
  `id_log` int(10) NOT NULL,
  `nim` char(9) DEFAULT NULL,
  `alamat_lama` varchar(35) DEFAULT NULL,
  `alamat_baru` varchar(35) DEFAULT NULL,
  `waktu` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `log_mahasiswa`
--

INSERT INTO `log_mahasiswa` (`id_log`, `nim`, `alamat_lama`, `alamat_baru`, `waktu`) VALUES
(1, 'E41211696', 'Madiun', 'Malang', '2021-12-10');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mata_kuliah`
--

CREATE TABLE `mata_kuliah` (
  `kode_matkul` char(5) NOT NULL,
  `nama_matkul` varchar(50) NOT NULL,
  `jumlah_SKS` tinyint(3) UNSIGNED DEFAULT 2,
  `kode_jur` char(5) DEFAULT NULL,
  `NIP` char(5) DEFAULT NULL,
  `NIP_Dosen` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mata_kuliah`
--

INSERT INTO `mata_kuliah` (`kode_matkul`, `nama_matkul`, `jumlah_SKS`, `kode_jur`, `NIP`, `NIP_Dosen`) VALUES
('APAL', 'Aplikasi Algoritma', 2, 'J0006', NULL, '1992052820'),
('APTR', 'Aplikasi Turunan', 3, 'J0006', NULL, '1993051020'),
('BIDAS', 'Biologi Dasar', 4, 'J0002', NULL, '0770435006'),
('BKMG', 'Biokimia Dasar', 3, 'J0007', NULL, '0275430005'),
('BSGM', 'Basic Grammar', 2, 'J0005', NULL, '1080432007'),
('CITRA', 'Pengolahan Citra', 5, 'J0006', NULL, '0480432066'),
('DAKS', 'Dasar Akuntansi', 2, 'J0004', NULL, '0480432066'),
('FIDAS', 'Fisika Dasar', 9, 'J0009', NULL, '0576431001'),
('JRKOM', 'Jaringan Komputer', 7, 'J0006', NULL, '0480432066'),
('KIMDA', 'Kimia Dasar', 6, 'J0010', NULL, '0770435006'),
('MMDAS', 'Matematika Dasar', 3, 'J0011', NULL, '0869437003'),
('PBASE', 'Pengantar Database', 7, 'J0006', NULL, '0480432066');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mata_kuliah_v2`
--

CREATE TABLE `mata_kuliah_v2` (
  `kode_matkul` char(6) NOT NULL,
  `jumlah_SKS` tinyint(3) UNSIGNED DEFAULT 2,
  `semester` tinyint(4) DEFAULT NULL,
  `NIP_dosen` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mata_kuliah_v2`
--

INSERT INTO `mata_kuliah_v2` (`kode_matkul`, `jumlah_SKS`, `semester`, `NIP_dosen`) VALUES
('CITRA', 2, 2, '0275430005'),
('FIDAS', 2, 6, '0489432066'),
('JRKOM', 2, 3, '0576431001'),
('KIMDA', 2, 4, '0160436012'),
('MMDAS', 4, 2, '0869437003'),
('PBASE', 2, 6, '0160436012');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mhs`
--

CREATE TABLE `mhs` (
  `nim` varchar(9) NOT NULL,
  `nama` varchar(225) NOT NULL,
  `alamat` varchar(225) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `kode_jur` char(5) DEFAULT NULL,
  `NIP` char(5) DEFAULT NULL,
  `NIP_Dosen` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `mhs`
--

INSERT INTO `mhs` (`nim`, `nama`, `alamat`, `phone`, `kode_jur`, `NIP`, `NIP_Dosen`) VALUES
('A32210893', 'Sri Astutik Anggraeni', 'Malang', '082211337766', 'J0001', NULL, '0160436012'),
('A37066699', 'Wati Anisa Ibrahim', 'jember', '082237329479', 'J0001', NULL, '0160436012'),
('B2121836', 'Ahmad Wira', 'Jember', '082348576849', 'J0001', NULL, '0160436012'),
('B32210722', 'Enni Oktafia Nur Cahyati', 'Madura', '089736248743', 'J0001', NULL, '0160436012'),
('C31219573', 'Wahyu Idris', 'Jember', '084735376859', 'J0002', NULL, '0260432002'),
('C39646339', 'Harun Ratna Kasih', 'jember', '085473529462', 'J0002', NULL, '0260432002'),
('D30718905', 'Widya Cahya Intan', 'Surabaya', '082384752134', 'J0004', NULL, '0275430005'),
('D41216865', 'Lutfi Bachtiar', 'Jember', '08572836539', 'J0004', NULL, '0275430005'),
('E41211696', 'Bachtiar Arya Habibie', 'Malang', '082234439795', 'J0006', NULL, '0480432066'),
('E46397516', 'Putra Daud Susila', 'jember', '082237462819', 'J0006', NULL, '0480432066');

--
-- Trigger `mhs`
--
DELIMITER $$
CREATE TRIGGER `update_alamat_mahasiswa` BEFORE UPDATE ON `mhs` FOR EACH ROW BEGIN 
	INSERT INTO log_mahasiswa
    SET NIM = OLD.nim,
    alamat_lama = OLD.alamat ,
	alamat_baru = NEW.alamat,
 	waktu = NOW();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_detail_krs`
--

CREATE TABLE `tbl_detail_krs` (
  `kode_krs` char(10) DEFAULT NULL,
  `kode_matkul` char(5) DEFAULT NULL,
  `hari` varchar(7) DEFAULT NULL,
  `jam` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_detail_krs`
--

INSERT INTO `tbl_detail_krs` (`kode_krs`, `kode_matkul`, `hari`, `jam`) VALUES
('FATPMBJ8Da', 'CITRA', 'senin', '08:12:15'),
('FATPMBJ8Da', 'JRKOM', 'senin', '08:12:15'),
('FATPMBJ8Da', 'PBASE', 'senin', '08:12:15'),
('FATPMBJ8Da', 'CITRA', 'senin', '08:12:15'),
('FATPMBJ8Da', 'JRKOM', 'senin', '08:12:15'),
('FATPMBJ8Da', 'PBASE', 'senin', '08:12:15'),
('gLnUGoFZpP', 'KIMDA', 'minggu', '08:51:52'),
('gLnUGoFZpP', 'MMDAS', 'minggu', '08:51:52'),
('gLnUGoFZpP', 'KIMDA', 'minggu', '08:51:52'),
('gLnUGoFZpP', 'MMDAS', 'minggu', '08:51:52'),
('sTtzCngX0c', 'KIMDA', 'minggu', '15:51:52'),
('sTtzCngX0c', 'MMDAS', 'minggu', '15:51:52'),
('sTtzCngX0c', 'KIMDA', 'minggu', '15:51:52'),
('sTtzCngX0c', 'MMDAS', 'minggu', '15:51:52'),
('sTtzCngX0z', 'KIMDA', 'minggu', '06:51:52'),
('sTtzCngX0z', 'MMDAS', 'minggu', '06:51:52'),
('sTtzCngX0z', 'KIMDA', 'minggu', '06:51:52'),
('sTtzCngX0z', 'MMDAS', 'minggu', '06:51:52'),
('YBqN1Wqu5C', 'CITRA', 'minggu', '12:00:52'),
('YBqN1Wqu5C', 'JRKOM', 'minggu', '12:00:52'),
('YBqN1Wqu5C', 'PBASE', 'minggu', '12:00:52'),
('YBqN1Wqu5C', 'CITRA', 'minggu', '12:00:52'),
('YBqN1Wqu5C', 'JRKOM', 'minggu', '12:00:52'),
('YBqN1Wqu5C', 'PBASE', 'minggu', '12:00:52'),
('zEkIlnEylC', 'KIMDA', 'minggu', '09:51:52'),
('zEkIlnEylC', 'MMDAS', 'minggu', '09:51:52'),
('zEkIlnEylC', 'KIMDA', 'minggu', '09:51:52'),
('zEkIlnEylC', 'MMDAS', 'minggu', '09:51:52');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_jurusan`
--

CREATE TABLE `tbl_jurusan` (
  `kode_jur` char(5) NOT NULL,
  `nama_jur` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_jurusan`
--

INSERT INTO `tbl_jurusan` (`kode_jur`, `nama_jur`) VALUES
('J0001', 'Produksi Pertanian'),
('J0002', 'Peternakan'),
('J0003', 'Bahasa Komunikasi da'),
('J0004', 'Managemen Agribisnis'),
('J0005', 'Kesehatan'),
('J0006', 'Teknologi Informasi'),
('J0007', 'Teknologi Pertanian'),
('J0008', 'Teknik'),
('j0009', 'Fisika'),
('J0010', 'Kimian'),
('J0011', 'Matematika');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_krs`
--

CREATE TABLE `tbl_krs` (
  `kode_krs` char(10) NOT NULL,
  `tanggal_krs` date DEFAULT NULL,
  `nim` char(9) DEFAULT NULL,
  `kode_jur` char(5) DEFAULT NULL,
  `jumlah_sks` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `tbl_krs`
--

INSERT INTO `tbl_krs` (`kode_krs`, `tanggal_krs`, `nim`, `kode_jur`, `jumlah_sks`) VALUES
('FATPMBJ8Da', '2021-11-14', 'E41211696', 'J0006', 11),
('gLnUGoFZpP', '2021-11-14', 'A37066699', 'J0001', 8),
('rPVRZrEd8h', '2021-08-07', 'C31219573', 'J0002', 11),
('S3oD0FFvJ6', '2021-08-14', 'B2121836', 'J0006', 16),
('s8MEoG8Ae3', '2021-11-26', 'D41216865', 'J0004', 10),
('sTtzCngX0c', '2021-11-14', 'A32210893', 'J0001', 8),
('sTtzCngX0i', '2021-11-21', 'A32210893', 'J0001', 8),
('sTtzCngX0z', '2021-11-14', 'A32210893', 'J0001', 8),
('YBqN1Wqu5C', '2021-11-14', 'E46397516', 'J0006', 11),
('zEkIlnEylC', '2021-11-14', 'B32210722', 'J0001', 8);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`nim`);

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kodebrg`);

--
-- Indeks untuk tabel `daftar_dosen`
--
ALTER TABLE `daftar_dosen`
  ADD PRIMARY KEY (`NIP`);

--
-- Indeks untuk tabel `history_delet_dosen`
--
ALTER TABLE `history_delet_dosen`
  ADD PRIMARY KEY (`id_log`);

--
-- Indeks untuk tabel `jual`
--
ALTER TABLE `jual`
  ADD PRIMARY KEY (`nofaktur`);

--
-- Indeks untuk tabel `log_mahasiswa`
--
ALTER TABLE `log_mahasiswa`
  ADD PRIMARY KEY (`id_log`);

--
-- Indeks untuk tabel `mata_kuliah`
--
ALTER TABLE `mata_kuliah`
  ADD PRIMARY KEY (`kode_matkul`),
  ADD KEY `kode_jur` (`kode_jur`),
  ADD KEY `NIP` (`NIP`),
  ADD KEY `NIP_Dosen` (`NIP_Dosen`);

--
-- Indeks untuk tabel `mata_kuliah_v2`
--
ALTER TABLE `mata_kuliah_v2`
  ADD PRIMARY KEY (`kode_matkul`);

--
-- Indeks untuk tabel `mhs`
--
ALTER TABLE `mhs`
  ADD PRIMARY KEY (`nim`),
  ADD KEY `kode_jur` (`kode_jur`),
  ADD KEY `NIP_Dosen` (`NIP_Dosen`);

--
-- Indeks untuk tabel `tbl_detail_krs`
--
ALTER TABLE `tbl_detail_krs`
  ADD KEY `kode_krs` (`kode_krs`),
  ADD KEY `kode_matkul` (`kode_matkul`);

--
-- Indeks untuk tabel `tbl_jurusan`
--
ALTER TABLE `tbl_jurusan`
  ADD PRIMARY KEY (`kode_jur`);

--
-- Indeks untuk tabel `tbl_krs`
--
ALTER TABLE `tbl_krs`
  ADD PRIMARY KEY (`kode_krs`),
  ADD KEY `nim` (`nim`),
  ADD KEY `kode_jur` (`kode_jur`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `history_delet_dosen`
--
ALTER TABLE `history_delet_dosen`
  MODIFY `id_log` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `log_mahasiswa`
--
ALTER TABLE `log_mahasiswa`
  MODIFY `id_log` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `mata_kuliah`
--
ALTER TABLE `mata_kuliah`
  ADD CONSTRAINT `mata_kuliah_ibfk_1` FOREIGN KEY (`kode_jur`) REFERENCES `tbl_jurusan` (`kode_jur`),
  ADD CONSTRAINT `mata_kuliah_ibfk_2` FOREIGN KEY (`NIP`) REFERENCES `daftar_dosen` (`NIP`),
  ADD CONSTRAINT `mata_kuliah_ibfk_3` FOREIGN KEY (`NIP_Dosen`) REFERENCES `daftar_dosen` (`NIP`);

--
-- Ketidakleluasaan untuk tabel `mhs`
--
ALTER TABLE `mhs`
  ADD CONSTRAINT `mhs_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `akun` (`nim`),
  ADD CONSTRAINT `mhs_ibfk_2` FOREIGN KEY (`kode_jur`) REFERENCES `tbl_jurusan` (`kode_jur`),
  ADD CONSTRAINT `mhs_ibfk_3` FOREIGN KEY (`NIP`) REFERENCES `daftar_dosen` (`NIP`),
  ADD CONSTRAINT `mhs_ibfk_4` FOREIGN KEY (`NIP_Dosen`) REFERENCES `daftar_dosen` (`NIP`);

--
-- Ketidakleluasaan untuk tabel `tbl_detail_krs`
--
ALTER TABLE `tbl_detail_krs`
  ADD CONSTRAINT `tbl_detail_krs_ibfk_1` FOREIGN KEY (`kode_krs`) REFERENCES `tbl_krs` (`kode_krs`),
  ADD CONSTRAINT `tbl_detail_krs_ibfk_2` FOREIGN KEY (`kode_matkul`) REFERENCES `mata_kuliah` (`kode_matkul`);

--
-- Ketidakleluasaan untuk tabel `tbl_krs`
--
ALTER TABLE `tbl_krs`
  ADD CONSTRAINT `tbl_krs_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `mhs` (`nim`),
  ADD CONSTRAINT `tbl_krs_ibfk_2` FOREIGN KEY (`kode_jur`) REFERENCES `tbl_jurusan` (`kode_jur`);
--
-- Database: `perusahaan`
--
CREATE DATABASE IF NOT EXISTS `perusahaan` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `perusahaan`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `divisi`
--

CREATE TABLE `divisi` (
  `id_divisi` char(2) NOT NULL,
  `nama_divisi` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `divisi`
--

INSERT INTO `divisi` (`id_divisi`, `nama_divisi`) VALUES
('A1', 'divisi A1'),
('A2', 'divisi A2'),
('B1', 'divisi B1'),
('B2', 'divisi B2'),
('B3', 'divisi B3'),
('C1', 'divisi C1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `id_pegawai` char(5) NOT NULL,
  `nama_pegawi` varchar(50) DEFAULT NULL,
  `id_divisi` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `nama_pegawi`, `id_divisi`) VALUES
('00001', 'adi', 'A1'),
('00002', 'aldo', 'A1'),
('00003', 'aldi', 'A1'),
('00004', 'agus', 'A2'),
('00005', 'bakti', 'A2'),
('00006', 'bika', 'A2'),
('00007', 'cahya', 'B1'),
('00008', 'surya', 'B1'),
('00009', 'nina', 'B2'),
('00010', 'alwi', 'B2'),
('00011', 'andika', 'B2'),
('00012', 'fahru', 'B3'),
('00013', 'fahri', 'C1'),
('00014', 'rifki', 'C1');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `divisi`
--
ALTER TABLE `divisi`
  ADD PRIMARY KEY (`id_divisi`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_pegawai`),
  ADD KEY `id_divisi` (`id_divisi`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD CONSTRAINT `pegawai_ibfk_1` FOREIGN KEY (`id_divisi`) REFERENCES `divisi` (`id_divisi`);
--
-- Database: `perusahaan_constraint`
--
CREATE DATABASE IF NOT EXISTS `perusahaan_constraint` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `perusahaan_constraint`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `divisi`
--

CREATE TABLE `divisi` (
  `id_divisi` char(2) NOT NULL,
  `nama_divisi` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `divisi`
--

INSERT INTO `divisi` (`id_divisi`, `nama_divisi`) VALUES
('A1', 'divisi A1'),
('A2', 'divisi A2'),
('B1', 'divisi B1'),
('B2', 'divisi B2'),
('C1', 'divisi C1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `id_pegawai` char(5) NOT NULL,
  `nama_pegawai` varchar(50) DEFAULT NULL,
  `id_divisi` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `nama_pegawai`, `id_divisi`) VALUES
('00001', 'adi', 'A1'),
('00002', 'aldo', 'A1'),
('00003', 'aldi', 'A1'),
('00004', 'agus', 'A2'),
('00005', 'bakti', 'A2'),
('00006', 'bika', 'A2'),
('00007', 'cahya', 'B1'),
('00008', 'surya', 'B1'),
('00009', 'nina', 'B2'),
('00010', 'alwi', 'B2'),
('00013', 'fahri', 'C1'),
('00014', 'rifki', 'C1');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `divisi`
--
ALTER TABLE `divisi`
  ADD PRIMARY KEY (`id_divisi`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_pegawai`),
  ADD KEY `idConstraint` (`id_divisi`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD CONSTRAINT `idConstraint` FOREIGN KEY (`id_divisi`) REFERENCES `divisi` (`id_divisi`) ON DELETE CASCADE ON UPDATE CASCADE;
--
-- Database: `perusahaan_konstrain`
--
CREATE DATABASE IF NOT EXISTS `perusahaan_konstrain` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `perusahaan_konstrain`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `divisi`
--

CREATE TABLE `divisi` (
  `id_divisi` char(2) NOT NULL,
  `nama_divisi` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `id_pegawai` char(5) NOT NULL,
  `nama_pegawai` varchar(50) DEFAULT NULL,
  `id_divisi` char(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `divisi`
--
ALTER TABLE `divisi`
  ADD PRIMARY KEY (`id_divisi`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_pegawai`);
--
-- Database: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(10) UNSIGNED NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_length` text COLLATE utf8_bin DEFAULT NULL,
  `col_collation` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) COLLATE utf8_bin DEFAULT '',
  `col_default` text COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `settings_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

--
-- Dumping data untuk tabel `pma__designer_settings`
--

INSERT INTO `pma__designer_settings` (`username`, `settings_data`) VALUES
('root', '{\"relation_lines\":\"true\",\"angular_direct\":\"direct\",\"snap_to_grid\":\"off\"}');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `export_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `template_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `template_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp(),
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Dumping data untuk tabel `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"mahasiswa\",\"table\":\"mhs\"},{\"db\":\"mahasiswa\",\"table\":\"akun\"},{\"db\":\"mahasiswa\",\"table\":\"phone\"},{\"db\":\"basis_data_mahasiswa\",\"table\":\"akun\"}]');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT 0,
  `x` float UNSIGNED NOT NULL DEFAULT 0,
  `y` float UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin DEFAULT NULL,
  `data_sql` longtext COLLATE utf8_bin DEFAULT NULL,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Dumping data untuk tabel `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2021-10-06 06:34:02', '{\"Console\\/Mode\":\"collapse\",\"lang\":\"id\"}');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Struktur dari tabel `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Indeks untuk tabel `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indeks untuk tabel `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Indeks untuk tabel `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indeks untuk tabel `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indeks untuk tabel `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Indeks untuk tabel `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indeks untuk tabel `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indeks untuk tabel `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indeks untuk tabel `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indeks untuk tabel `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indeks untuk tabel `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indeks untuk tabel `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Indeks untuk tabel `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indeks untuk tabel `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Database: `program_studi`
--
CREATE DATABASE IF NOT EXISTS `program_studi` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `program_studi`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `dosen`
--

CREATE TABLE `dosen` (
  `NIP` char(18) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_hp` char(13) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `khs`
--

CREATE TABLE `khs` (
  `nilai` int(11) NOT NULL,
  `grade` char(2) NOT NULL,
  `kode_matkul` char(5) NOT NULL,
  `NIM` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kuliah`
--

CREATE TABLE `kuliah` (
  `kode_matkul` char(5) NOT NULL,
  `NIM` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `NIM` varchar(9) NOT NULL,
  `nama_mahasiswa` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `NIP` char(18) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `matakuliah`
--

CREATE TABLE `matakuliah` (
  `kode_matkul` char(5) NOT NULL,
  `nama_matkul` varchar(50) NOT NULL,
  `sks` int(11) NOT NULL,
  `semester` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `mengampu`
--

CREATE TABLE `mengampu` (
  `kode_matkul` char(5) NOT NULL,
  `NIP` char(18) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`NIP`);

--
-- Indeks untuk tabel `khs`
--
ALTER TABLE `khs`
  ADD PRIMARY KEY (`kode_matkul`,`NIM`),
  ADD KEY `NIM` (`NIM`);

--
-- Indeks untuk tabel `kuliah`
--
ALTER TABLE `kuliah`
  ADD PRIMARY KEY (`kode_matkul`,`NIM`),
  ADD KEY `NIM` (`NIM`);

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`NIM`),
  ADD KEY `NIP` (`NIP`);

--
-- Indeks untuk tabel `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD PRIMARY KEY (`kode_matkul`);

--
-- Indeks untuk tabel `mengampu`
--
ALTER TABLE `mengampu`
  ADD PRIMARY KEY (`kode_matkul`,`NIP`),
  ADD KEY `NIP` (`NIP`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `khs`
--
ALTER TABLE `khs`
  ADD CONSTRAINT `khs_ibfk_1` FOREIGN KEY (`kode_matkul`) REFERENCES `matakuliah` (`kode_matkul`),
  ADD CONSTRAINT `khs_ibfk_2` FOREIGN KEY (`NIM`) REFERENCES `mahasiswa` (`NIM`);

--
-- Ketidakleluasaan untuk tabel `kuliah`
--
ALTER TABLE `kuliah`
  ADD CONSTRAINT `kuliah_ibfk_1` FOREIGN KEY (`kode_matkul`) REFERENCES `matakuliah` (`kode_matkul`),
  ADD CONSTRAINT `kuliah_ibfk_2` FOREIGN KEY (`NIM`) REFERENCES `mahasiswa` (`NIM`);

--
-- Ketidakleluasaan untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `mahasiswa_ibfk_1` FOREIGN KEY (`NIP`) REFERENCES `dosen` (`NIP`);

--
-- Ketidakleluasaan untuk tabel `mengampu`
--
ALTER TABLE `mengampu`
  ADD CONSTRAINT `mengampu_ibfk_1` FOREIGN KEY (`kode_matkul`) REFERENCES `matakuliah` (`kode_matkul`),
  ADD CONSTRAINT `mengampu_ibfk_2` FOREIGN KEY (`NIP`) REFERENCES `dosen` (`NIP`);
--
-- Database: `s2_cafe_lafo`
--
CREATE DATABASE IF NOT EXISTS `s2_cafe_lafo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `s2_cafe_lafo`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `diskon`
--

CREATE TABLE `diskon` (
  `kode_diskon` char(11) NOT NULL,
  `jumlah_diskon` float NOT NULL,
  `tenggat_diskon` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `hak_akses`
--

CREATE TABLE `hak_akses` (
  `kode_hak_akses` char(5) NOT NULL,
  `hak_akses` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `jenis_diskon`
--

CREATE TABLE `jenis_diskon` (
  `kode_jenis_diskon` char(11) NOT NULL,
  `jenis_diskon` varchar(225) NOT NULL,
  `kode_diskon` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `Kode_Kategori` char(5) NOT NULL,
  `Kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `stok`
--

CREATE TABLE `stok` (
  `kode_stok` char(13) NOT NULL,
  `harga_beli` float NOT NULL,
  `qty` int(11) NOT NULL,
  `kode_barang` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `suplai`
--

CREATE TABLE `suplai` (
  `kode_suplaier` char(13) NOT NULL,
  `Nama_Suplaier` varchar(225) NOT NULL,
  `No_Telp` char(13) NOT NULL,
  `Alamat` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user_aplikasi`
--

CREATE TABLE `user_aplikasi` (
  `Id_Pegawai` char(13) NOT NULL,
  `Nama_Pegawai` varchar(225) NOT NULL,
  `Gender` enum('L','P') NOT NULL,
  `Alamat` varchar(225) NOT NULL,
  `No_Hp` char(13) NOT NULL,
  `Status_user` varchar(10) NOT NULL,
  `Tanggal_Terdaftar` date NOT NULL,
  `kode_hak_akses` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `diskon`
--
ALTER TABLE `diskon`
  ADD PRIMARY KEY (`kode_diskon`);

--
-- Indeks untuk tabel `hak_akses`
--
ALTER TABLE `hak_akses`
  ADD PRIMARY KEY (`kode_hak_akses`);

--
-- Indeks untuk tabel `jenis_diskon`
--
ALTER TABLE `jenis_diskon`
  ADD PRIMARY KEY (`kode_jenis_diskon`),
  ADD KEY `kode_diskon` (`kode_diskon`);

--
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`Kode_Kategori`);

--
-- Indeks untuk tabel `stok`
--
ALTER TABLE `stok`
  ADD PRIMARY KEY (`kode_stok`);

--
-- Indeks untuk tabel `suplai`
--
ALTER TABLE `suplai`
  ADD PRIMARY KEY (`kode_suplaier`);

--
-- Indeks untuk tabel `user_aplikasi`
--
ALTER TABLE `user_aplikasi`
  ADD PRIMARY KEY (`Id_Pegawai`),
  ADD KEY `kode_hak_akses` (`kode_hak_akses`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `jenis_diskon`
--
ALTER TABLE `jenis_diskon`
  ADD CONSTRAINT `jenis_diskon_ibfk_1` FOREIGN KEY (`kode_diskon`) REFERENCES `diskon` (`kode_diskon`);

--
-- Ketidakleluasaan untuk tabel `user_aplikasi`
--
ALTER TABLE `user_aplikasi`
  ADD CONSTRAINT `user_aplikasi_ibfk_1` FOREIGN KEY (`kode_hak_akses`) REFERENCES `hak_akses` (`kode_hak_akses`);
--
-- Database: `s2_cafe_lafo_v2`
--
CREATE DATABASE IF NOT EXISTS `s2_cafe_lafo_v2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `s2_cafe_lafo_v2`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kode_Barang` char(15) NOT NULL,
  `Nama_barang` varchar(225) NOT NULL,
  `Harga` float NOT NULL,
  `Kode_Kategori` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kode_Barang`, `Nama_barang`, `Harga`, `Kode_Kategori`) VALUES
('MMN2204202201', 'nescafe', 5000, 'minum'),
('MMN2404202201', 'indomi goreng', 6000, 'makan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_suplai`
--

CREATE TABLE `detail_suplai` (
  `harga_beli` float NOT NULL,
  `qty` int(11) NOT NULL,
  `Id_detail_suplai` char(13) NOT NULL,
  `Kode_Menyuplai` char(13) NOT NULL,
  `kode_Barang` char(15) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `detail_suplai`
--

INSERT INTO `detail_suplai` (`harga_beli`, `qty`, `Id_detail_suplai`, `Kode_Menyuplai`, `kode_Barang`, `tanggal`) VALUES
(3000, 12, 'DSUP240422001', 'MNP2404220002', 'MMN2204202201', '2022-04-24'),
(3000, 5, 'DSUP240422002', 'MNP2404220001', 'MMN2404202201', '2022-04-24');

--
-- Trigger `detail_suplai`
--
DELIMITER $$
CREATE TRIGGER `duplikat_Detilsuplai_kestok` AFTER INSERT ON `detail_suplai` FOR EACH ROW BEGIN
INSERT INTO stok
SET
stok.kode_stok = new.id_detail_suplai,
stok.harga_beli = new.harga_beli,
stok.kode_barang = new.kode_Barang,
stok.tanggal = new.tanggal;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `qty` int(11) NOT NULL,
  `sub_total` float NOT NULL,
  `kode_stok` char(13) NOT NULL,
  `kode_transaksi` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`qty`, `sub_total`, `kode_stok`, `kode_transaksi`) VALUES
(1, 1, 'DSUP240422001', 'TR24042022001');

--
-- Trigger `detail_transaksi`
--
DELIMITER $$
CREATE TRIGGER `kurangin_stok` AFTER INSERT ON `detail_transaksi` FOR EACH ROW BEGIN
    
UPDATE stok SET
stok.qty = stok.qty - new.qty 
    WHERE
    stok.kode_stok = new.kode_stok;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `diskon`
--

CREATE TABLE `diskon` (
  `kode_diskon` char(11) NOT NULL,
  `nama_Diskon` varchar(100) NOT NULL,
  `jumlah_diskon` float NOT NULL,
  `tenggat_diskon` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `diskon`
--

INSERT INTO `diskon` (`kode_diskon`, `nama_Diskon`, `jumlah_diskon`, `tenggat_diskon`) VALUES
('HARGANORMAL', 'tanpa diskon', 0, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `hak_akses`
--

CREATE TABLE `hak_akses` (
  `kode_hak_akses` char(5) NOT NULL,
  `hak_akses` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `hak_akses`
--

INSERT INTO `hak_akses` (`kode_hak_akses`, `hak_akses`) VALUES
('admin', 'admin'),
('pgw01', 'pegawai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jenis_diskon`
--

CREATE TABLE `jenis_diskon` (
  `kode_jenis_diskon` char(11) NOT NULL,
  `jenis_diskon` varchar(225) NOT NULL,
  `kode_diskon` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `Kode_Kategori` char(5) NOT NULL,
  `Kategori` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`Kode_Kategori`, `Kategori`) VALUES
('makan', 'makanan'),
('minum', 'minuman');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menyuplai`
--

CREATE TABLE `menyuplai` (
  `Kode_Menyuplai` char(13) NOT NULL,
  `Tanggal_menyuplai` date NOT NULL,
  `Total` float NOT NULL,
  `kode_suplaier` char(13) NOT NULL,
  `Id_Pegawai` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `menyuplai`
--

INSERT INTO `menyuplai` (`Kode_Menyuplai`, `Tanggal_menyuplai`, `Total`, `kode_suplaier`, `Id_Pegawai`) VALUES
('MNP2404220001', '2022-04-24', 0, 'SUP2404202201', '18042022admin'),
('MNP2404220002', '2022-04-24', 0, 'SUP2404202202', '18042022admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penngguna`
--

CREATE TABLE `penngguna` (
  `Id_Pegawai` char(13) NOT NULL,
  `Nama_Pegawai` varchar(225) NOT NULL,
  `Gender` char(1) NOT NULL,
  `Alamat` varchar(225) NOT NULL,
  `No_Hp` char(13) NOT NULL,
  `Status_pengguna` char(1) NOT NULL,
  `Tanggal_Terdaftar` date NOT NULL,
  `kode_hak_akses` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `penngguna`
--

INSERT INTO `penngguna` (`Id_Pegawai`, `Nama_Pegawai`, `Gender`, `Alamat`, `No_Hp`, `Status_pengguna`, `Tanggal_Terdaftar`, `kode_hak_akses`) VALUES
('18042022admin', 'admin', '?', 'Politeknik Negeri Jember, Jember, Jawa timur, Indonesia', '082234439795', 'a', '2022-04-18', 'admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `stok`
--

CREATE TABLE `stok` (
  `kode_stok` char(13) NOT NULL,
  `harga_beli` float NOT NULL,
  `qty` int(11) NOT NULL,
  `kode_barang` char(13) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `stok`
--

INSERT INTO `stok` (`kode_stok`, `harga_beli`, `qty`, `kode_barang`, `tanggal`) VALUES
('DSUP240422001', 3000, -1, 'MMN2204202201', '2022-04-24'),
('DSUP240422002', 3000, 0, 'MMN2404202201', '2022-04-24');

-- --------------------------------------------------------

--
-- Struktur dari tabel `suplai`
--

CREATE TABLE `suplai` (
  `kode_suplaier` char(13) NOT NULL,
  `Nama_Suplaier` varchar(225) NOT NULL,
  `No_Telp` char(13) NOT NULL,
  `Alamat` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `suplai`
--

INSERT INTO `suplai` (`kode_suplaier`, `Nama_Suplaier`, `No_Telp`, `Alamat`) VALUES
('SUP2404202201', 'PT Suplai Minuman', '081222333444', 'Jember '),
('SUP2404202202', 'PT Suplier Makanan', '082111333444', 'Jember');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_penjualan`
--

CREATE TABLE `transaksi_penjualan` (
  `kode_transaksi` char(13) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `uangPelanggan` float NOT NULL,
  `Total` float NOT NULL,
  `Kembalian` float NOT NULL,
  `Id_Pegawai` char(13) NOT NULL,
  `kode_diskon` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi_penjualan`
--

INSERT INTO `transaksi_penjualan` (`kode_transaksi`, `tanggal_transaksi`, `uangPelanggan`, `Total`, `Kembalian`, `Id_Pegawai`, `kode_diskon`) VALUES
('TR24042022001', '2022-04-24', 10000, 6000, 4000, '18042022admin', 'HARGANORMAL');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode_Barang`),
  ADD KEY `Kode_Kategori` (`Kode_Kategori`);

--
-- Indeks untuk tabel `detail_suplai`
--
ALTER TABLE `detail_suplai`
  ADD PRIMARY KEY (`Id_detail_suplai`),
  ADD KEY `Kode_Menyuplai` (`Kode_Menyuplai`),
  ADD KEY `kode_Barang` (`kode_Barang`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD KEY `kode_stok` (`kode_stok`),
  ADD KEY `kode_transaksi` (`kode_transaksi`);

--
-- Indeks untuk tabel `diskon`
--
ALTER TABLE `diskon`
  ADD PRIMARY KEY (`kode_diskon`);

--
-- Indeks untuk tabel `hak_akses`
--
ALTER TABLE `hak_akses`
  ADD PRIMARY KEY (`kode_hak_akses`);

--
-- Indeks untuk tabel `jenis_diskon`
--
ALTER TABLE `jenis_diskon`
  ADD PRIMARY KEY (`kode_jenis_diskon`),
  ADD KEY `kode_diskon` (`kode_diskon`);

--
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`Kode_Kategori`);

--
-- Indeks untuk tabel `menyuplai`
--
ALTER TABLE `menyuplai`
  ADD PRIMARY KEY (`Kode_Menyuplai`),
  ADD KEY `kode_suplaier` (`kode_suplaier`),
  ADD KEY `Id_Pegawai` (`Id_Pegawai`);

--
-- Indeks untuk tabel `penngguna`
--
ALTER TABLE `penngguna`
  ADD PRIMARY KEY (`Id_Pegawai`),
  ADD KEY `kode_hak_akses` (`kode_hak_akses`);

--
-- Indeks untuk tabel `stok`
--
ALTER TABLE `stok`
  ADD PRIMARY KEY (`kode_stok`);

--
-- Indeks untuk tabel `suplai`
--
ALTER TABLE `suplai`
  ADD PRIMARY KEY (`kode_suplaier`);

--
-- Indeks untuk tabel `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD PRIMARY KEY (`kode_transaksi`),
  ADD KEY `Id_Pegawai` (`Id_Pegawai`),
  ADD KEY `kode_diskon` (`kode_diskon`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`Kode_Kategori`) REFERENCES `kategori` (`Kode_Kategori`);

--
-- Ketidakleluasaan untuk tabel `detail_suplai`
--
ALTER TABLE `detail_suplai`
  ADD CONSTRAINT `detail_suplai_ibfk_1` FOREIGN KEY (`Kode_Menyuplai`) REFERENCES `menyuplai` (`Kode_Menyuplai`),
  ADD CONSTRAINT `detail_suplai_ibfk_2` FOREIGN KEY (`kode_Barang`) REFERENCES `barang` (`kode_Barang`);

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`kode_stok`) REFERENCES `stok` (`kode_stok`),
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`kode_transaksi`) REFERENCES `transaksi_penjualan` (`kode_transaksi`);

--
-- Ketidakleluasaan untuk tabel `jenis_diskon`
--
ALTER TABLE `jenis_diskon`
  ADD CONSTRAINT `jenis_diskon_ibfk_1` FOREIGN KEY (`kode_diskon`) REFERENCES `diskon` (`kode_diskon`);

--
-- Ketidakleluasaan untuk tabel `menyuplai`
--
ALTER TABLE `menyuplai`
  ADD CONSTRAINT `menyuplai_ibfk_1` FOREIGN KEY (`kode_suplaier`) REFERENCES `suplai` (`kode_suplaier`),
  ADD CONSTRAINT `menyuplai_ibfk_2` FOREIGN KEY (`Id_Pegawai`) REFERENCES `penngguna` (`Id_Pegawai`);

--
-- Ketidakleluasaan untuk tabel `penngguna`
--
ALTER TABLE `penngguna`
  ADD CONSTRAINT `penngguna_ibfk_1` FOREIGN KEY (`kode_hak_akses`) REFERENCES `hak_akses` (`kode_hak_akses`);

--
-- Ketidakleluasaan untuk tabel `transaksi_penjualan`
--
ALTER TABLE `transaksi_penjualan`
  ADD CONSTRAINT `transaksi_penjualan_ibfk_1` FOREIGN KEY (`Id_Pegawai`) REFERENCES `penngguna` (`Id_Pegawai`),
  ADD CONSTRAINT `transaksi_penjualan_ibfk_2` FOREIGN KEY (`kode_diskon`) REFERENCES `diskon` (`kode_diskon`);
--
-- Database: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
