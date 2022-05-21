-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 11 Bulan Mei 2022 pada 03.47
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
-- Database: `db_lafo`
--

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
