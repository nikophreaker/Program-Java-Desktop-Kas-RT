-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 06 Jul 2020 pada 05.15
-- Versi server: 10.1.38-MariaDB
-- Versi PHP: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kas`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id` int(4) NOT NULL,
  `username` varchar(24) NOT NULL,
  `password` varchar(18) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `level` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `nama`, `level`) VALUES
(1, 'admin', 'admin', 'pak bambang', 'admin'),
(2, 'bahar', '123', 'pak baharudin', 'admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `anggota`
--

CREATE TABLE `anggota` (
  `kd_anggota` varchar(50) NOT NULL,
  `nm_anggota` varchar(26) NOT NULL,
  `jml_anggota` int(2) NOT NULL,
  `tunggakan` int(12) NOT NULL,
  `ket_anggota` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `anggota`
--

INSERT INTO `anggota` (`kd_anggota`, `nm_anggota`, `jml_anggota`, `tunggakan`, `ket_anggota`) VALUES
('1327251234', 'Tri Surya Herdadi', 5, 0, ''),
('1327251432', 'Niko Prayoga Wiratama', 4, 0, ''),
('1327251433', 'Dafa Rizqullah Akbar', 3, 0, ''),
('1327251439', 'Bujank', 1, 50000, 'Jomblo');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kas_keluar`
--

CREATE TABLE `kas_keluar` (
  `no_kk` int(12) NOT NULL,
  `tgl_kk` date NOT NULL,
  `id_transaksi` int(5) NOT NULL,
  `ket_keluar` varchar(50) NOT NULL,
  `keluar` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kas_keluar`
--

INSERT INTO `kas_keluar` (`no_kk`, `tgl_kk`, `id_transaksi`, `ket_keluar`, `keluar`) VALUES
(1, '2020-04-24', 3, 'Perbulan', 35000),
(2, '2020-04-30', 2, 'Perbulan', 15000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kas_masuk`
--

CREATE TABLE `kas_masuk` (
  `no_km` int(12) NOT NULL,
  `kd_anggota` varchar(50) NOT NULL,
  `tgl_km` date NOT NULL,
  `id_transaksi` int(5) NOT NULL,
  `ket_masuk` varchar(50) NOT NULL,
  `masuk` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kas_masuk`
--

INSERT INTO `kas_masuk` (`no_km`, `kd_anggota`, `tgl_km`, `id_transaksi`, `ket_masuk`, `masuk`) VALUES
(1, '1327251234', '2020-03-28', 1, 'Iuran Kas', 30000),
(2, '1327251432', '2020-04-24', 1, 'Telat', 50000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(5) NOT NULL,
  `nm_transaksi` varchar(50) NOT NULL,
  `ket_transaksi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `nm_transaksi`, `ket_transaksi`) VALUES
(1, 'Iuran Warga', 'KAS RT'),
(2, 'Penyemprotan disinfektan', 'Dari kelurahan'),
(3, 'Penyemprotan Nyamuk', 'Biaya Penyemprotan Nyamuk'),
(4, 'Kas bulanan', 'Kas');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`kd_anggota`);

--
-- Indeks untuk tabel `kas_keluar`
--
ALTER TABLE `kas_keluar`
  ADD PRIMARY KEY (`no_kk`);

--
-- Indeks untuk tabel `kas_masuk`
--
ALTER TABLE `kas_masuk`
  ADD PRIMARY KEY (`no_km`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `kas_keluar`
--
ALTER TABLE `kas_keluar`
  MODIFY `no_kk` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `kas_masuk`
--
ALTER TABLE `kas_masuk`
  MODIFY `no_km` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
