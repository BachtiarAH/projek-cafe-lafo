-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 26 Bulan Mei 2022 pada 18.00
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

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `count detail trans` (IN `kodeTransaksi` VARCHAR(13))  SELECT COUNT(detail_transaksi.kode_Menu) FROM detail_transaksi WHERE detail_transaksi.kode_transaksi = `kodeTransaksi`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `countResep` (INOUT `kodeMenu` CHAR(5))  SELECT COUNT(resep.kode_resep) FROM resep WHERE resep.kode_Menu = `kodeMenu`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `menulist` ()  BEGIN
SELECT * FROM db_lafo.menu;
END$$

DELIMITER ;

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
  `satuan` varchar(10) NOT NULL,
  `stok` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kode_Barang`, `Nama_barang`, `satuan`, `stok`) VALUES
('BRG002', 'Kopi bubuk', 'gram', 0),
('BRG003', 'MILO', 'buah', 1),
('BRGU01', 'gula pasir', 'buah', 12),
('BRKO01', 'kopi luak', 'buah', 12),
('BRLU02', 'kopi luak', 'buah', NULL),
('BRNE01', 'Nesscafe', 'buah', 24),
('BRSU01', 'susu uht', 'liter', NULL),
('BRSU02', 'susu kental manis', 'liter', 1),
('BRSU03', 'sukijan', 'buah', NULL),
('BRTE01', 'Teh celup', 'buah', NULL),
('MNKO04', 'kopicupal', 'buah', 4);

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
(6000, 12, 'DSUP050522001', 'buah', 'MSP0505220001', 'BRTE01', 10, 500),
(20000, 1000, 'DSUP050522002', 'gram', 'MSP0505220001', 'BRG002', 1000, 20),
(18000, 12, 'DSUP23052201', 'buah', 'MSP2305220001', 'BRGU01', 12, 1500),
(45000, 30, 'DSUP23052202', 'buah', 'MSP2305220001', 'BRNE01', 30, 1500),
(12000, 12, 'DSUP23052203', 'buah', 'MSP2305220002', 'BRLU02', 12, 1000),
(12000, 12, 'DSUP23052204', 'liter', 'MSP2305220002', 'BRSU01', 12, 1000),
(20000, 2, 'DSUP23052205', 'liter', 'MSP2305220002', 'BRSU02', 2, 10000),
(90000, 30, 'DSUP23052206', 'buah', 'MSP2305220002', 'BRG003', 30, 3000),
(20000, 4, 'DSUP24052201', 'buah', 'MSP2405220004', 'BRSU03', 4, 5000),
(12000, 12, 'DSUP24052202', 'buah', 'MSP2405220005', 'BRKO01', 12, 1000),
(12000, 12, 'DSUP24052203', 'buah', 'MSP2405220006', 'BRSU03', 12, 1000),
(12000, 12, 'DSUP24052204', 'buah', 'MSP2405220008', 'BRSU03', 12, 1000),
(34500, 23, 'DSUP24052205', 'buah', 'MSP2405220009', 'BRSU03', 23, 1500),
(12000, 12, 'DSUP24052206', 'buah', 'MSP2405220010', 'BRKO01', 12, 1000),
(30000, 4, 'DSUP25052201', 'buah', 'MSP2505220011', 'MNKO04', 4, 10000),
(6000, 1, 'DSUP26052201', 'liter', 'MSP2605220012', 'BRSU02', 1, 6000),
(20000, 10, 'DSUP26052202', 'buah', 'MSP2605220013', 'BRG003', 10, 2000),
(10000, 1, 'DSUP26052203', 'liter', 'MSP2605220014', 'BRSU02', 1, 10000),
(48000, 24, 'DSUP26052204', 'buah', 'MSP2605220015', 'BRNE01', 24, 2000),
(72000, 12, 'DSUP26052205', 'buah', 'MSP2605220016', 'BRGU01', 12, 6000);

--
-- Trigger `detail_suplai`
--
DELIMITER $$
CREATE TRIGGER `tambahStokBarang` AFTER INSERT ON `detail_suplai` FOR EACH ROW UPDATE barang SET barang.stok = barang.stok + new.stok WHERE barang.kode_Barang = new.kode_Barang
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

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`qty`, `sub_total`, `harga`, `detail_transaksi`, `kode_Menu`, `kode_transaksi`) VALUES
(1, 4000, 4000, 'DTR26052201', 'MN005', 'TRK26052201'),
(0, 5000, 5000, 'DTR26052202', 'MN004', 'TRK26052203'),
(1, 5000, 5000, 'DTR26052203', 'MN004', 'TRK26052203'),
(1, 5000, 5000, 'DTR26052204', 'MN004', 'TRK26052203'),
(1, 5000, 5000, 'DTR26052205', 'MN003', 'TRK26052206'),
(1, 5000, 5000, 'DTR26052206', 'MN004', 'TRK26052207'),
(1, 5000, 5000, 'DTR26052208', 'MN004', 'TRK26052207'),
(1, 5000, 5000, 'DTR26052209', 'MN004', 'TRK26052207'),
(1, 5000, 5000, 'DTR26052210', 'MN004', 'TRK26052208'),
(1, 5000, 5000, 'DTR26052211', 'MN004', 'TRK26052209'),
(1, 5000, 5000, 'DTR26052212', 'MN004', 'TRK26052210'),
(1, 5000, 5000, 'DTR26052213', 'MN004', 'TRK26052211'),
(1, 5000, 5000, 'DTR26052214', 'MN004', 'TRK26052212'),
(1, 5000, 5000, 'DTR26052215', 'MN004', 'TRK26052213');

--
-- Trigger `detail_transaksi`
--
DELIMITER $$
CREATE TRIGGER `kurangi stok Barang` AFTER INSERT ON `detail_transaksi` FOR EACH ROW UPDATE barang SET barang.stok = barang.stok - (new.qty * (SELECT  resep.qty FROM resep WHERE resep.kode_Menu = new.kode_Menu LIMIT 1))
    WHERE barang.kode_Barang = 
     (SELECT resep.kode_Barang FROM resep WHERE resep.kode_Menu = new.kode_Menu LIMIT 1)
$$
DELIMITER ;

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
('HARGANORMAL', 0, NULL, 'harga normal'),
('KESEPULUH', 1000, NULL, 'Diskon Pembelian Ke ');

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
('MN003', 'kopi hitam', 5000, 'minuman'),
('MN004', 'es milo', 5000, 'minuman'),
('MN005', 'Milo Anget', 4000, 'minuman');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menyuplai`
--

CREATE TABLE `menyuplai` (
  `Kode_Menyuplai` char(13) NOT NULL,
  `Tanggal_menyuplai` date NOT NULL,
  `kode_suplaier` char(11) NOT NULL,
  `Id_Pegawai` char(11) NOT NULL,
  `total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `menyuplai`
--

INSERT INTO `menyuplai` (`Kode_Menyuplai`, `Tanggal_menyuplai`, `kode_suplaier`, `Id_Pegawai`, `total`) VALUES
('MSP0505220001', '2022-05-05', 'SUP05052202', 'PGW05052201', 26000),
('MSP2305220001', '2022-05-23', 'SUP05052202', 'ADM05052201', 0),
('MSP2305220002', '2022-05-23', 'SUP15052201', 'ADM05052201', 0),
('MSP2405220003', '2022-05-24', 'SUP15052202', 'ADM05052201', 0),
('MSP2405220004', '2022-05-24', 'SUP05052201', 'ADM05052201', 18000),
('MSP2405220005', '2022-05-24', 'SUP05052201', 'ADM05052201', 12000),
('MSP2405220006', '2022-05-24', 'SUP05052202', 'ADM05052201', 132000),
('MSP2405220007', '2022-05-24', 'SUP05052202', 'ADM05052201', 0),
('MSP2405220008', '2022-05-24', 'SUP05052202', 'ADM05052201', 12000),
('MSP2405220009', '2022-05-24', 'SUP05052202', 'ADM05052201', 34500),
('MSP2405220010', '2022-05-24', 'SUP15052201', 'ADM05052201', 12000),
('MSP2505220011', '2022-05-25', 'SUP15052201', 'ADM05052201', 30000),
('MSP2605220012', '2022-05-26', 'SUP15052202', 'ADM05052201', 6000),
('MSP2605220013', '2022-05-26', 'SUP15052202', 'ADM05052201', 20000),
('MSP2605220014', '2022-05-26', 'SUP15052202', 'ADM05052201', 10000),
('MSP2605220015', '2022-05-26', 'SUP05052202', 'ADM05052201', 48000),
('MSP2605220016', '2022-05-26', 'SUP15052201', 'ADM05052201', 72000);

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
  `kode_Menu` char(5) NOT NULL,
  `totalResepDDigunakan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `resep`
--

INSERT INTO `resep` (`qty`, `kode_Barang`, `kode_Menu`, `totalResepDDigunakan`) VALUES
(1, 'BRTE01', 'MN001', NULL),
(1, 'BRTE01', 'MN002', NULL),
(20, 'BRG002', 'MN003', NULL),
(5, 'BRGU01', 'MN001', 123),
(1, 'BRG003', 'MN004', 1),
(1, 'BRG003', 'MN005', 4);

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
('SUP05052202', '084726384231', 'MADIUN', 'Toko Grosir Bu Anik'),
('SUP15052201', '089123754897', 'Banyuwangi', 'bandar'),
('SUP15052202', '093412349532', 'Bondowoso', 'pengepul');

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
-- Dumping data untuk tabel `transaksi_penjualan`
--

INSERT INTO `transaksi_penjualan` (`kode_transaksi`, `tanggal_transaksi`, `uangPelanggan`, `Total`, `Kembalian`, `Id_Pegawai`, `kode_diskon`) VALUES
('TR24042022001', '2022-04-24', 10000, 8000, 2000, 'PGW05052201', 'HARGANORMAL'),
('TRK26052201', '2026-05-22', 5000, 4000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052202', '2026-05-22', 5000, 5000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052203', '2022-05-26', 10000, 5000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052204', '2026-05-22', 5000, 4000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052205', '2026-05-22', 5000, 4000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052206', '2026-05-22', 5000, 5000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052207', '2026-05-22', 5000, 15000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052208', '2026-05-22', 5000, 5000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052209', '2026-05-22', 5000, 5000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052210', '2026-05-22', 5000, 5000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052211', '2026-05-22', 5000, 5000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052212', '2026-05-22', 5000, 5000, 0, 'ADM05052201', 'HARGANORMAL'),
('TRK26052213', '2026-05-22', 5000, 5000, 0, 'ADM05052201', 'HARGANORMAL');

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
  ADD KEY `detail_suplai_ibfk_2` (`kode_Barang`),
  ADD KEY `detail_suplai_ibfk_1` (`Kode_Menyuplai`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`detail_transaksi`),
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
  ADD KEY `kode_Menu` (`kode_Menu`),
  ADD KEY `resep_ibfk_1` (`kode_Barang`);

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
  ADD CONSTRAINT `detail_suplai_ibfk_1` FOREIGN KEY (`Kode_Menyuplai`) REFERENCES `menyuplai` (`Kode_Menyuplai`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_suplai_ibfk_2` FOREIGN KEY (`kode_Barang`) REFERENCES `barang` (`kode_Barang`) ON UPDATE CASCADE;

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
  ADD CONSTRAINT `resep_ibfk_1` FOREIGN KEY (`kode_Barang`) REFERENCES `barang` (`kode_Barang`) ON UPDATE CASCADE,
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
