-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 18, 2019 at 12:39 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parkir`
--

-- --------------------------------------------------------

--
-- Table structure for table `jenis_kendaraan`
--

CREATE TABLE `jenis_kendaraan` (
  `kd_kendaraan` char(3) NOT NULL,
  `nm_kendaraan` varchar(30) NOT NULL,
  `tarif` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jenis_kendaraan`
--

INSERT INTO `jenis_kendaraan` (`kd_kendaraan`, `nm_kendaraan`, `tarif`) VALUES
('001', 'Sepeda', 1000),
('002', 'Motor', 2000),
('003', 'Mobil', 5000),
('004', 'Truck', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `kd_kategori` char(3) NOT NULL,
  `nm_kategori` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`kd_kategori`, `nm_kategori`) VALUES
('1', 'Parkir Inap'),
('2', 'Parkir Biasa'),
('3', 'parkir bentar');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` varchar(255) NOT NULL,
  `pass` varchar(15) NOT NULL,
  `level` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `pass`, `level`) VALUES
('admin', 'admin', 'admin'),
('manager', 'manager', 'manager'),
('petugas', 'petugas', 'petugas');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `no_parkir` int(3) NOT NULL,
  `plat_nomor` varchar(10) NOT NULL,
  `kd_kategori` varchar(5) NOT NULL,
  `nm_kendaraan` varchar(30) NOT NULL,
  `tarif` int(10) NOT NULL,
  `kd_kendaraan` varchar(5) NOT NULL,
  `nm_kategori` varchar(40) NOT NULL,
  `jam_masuk` varchar(10) NOT NULL,
  `jam_keluar` varchar(10) NOT NULL,
  `lama_parkir` double NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`no_parkir`, `plat_nomor`, `kd_kategori`, `nm_kendaraan`, `tarif`, `kd_kendaraan`, `nm_kategori`, `jam_masuk`, `jam_keluar`, `lama_parkir`, `total`) VALUES
(1, 'abcd', '2', 'Mobil', 5000, '003', 'Parkir Biasa', '09.00', '10.00', 1, 5000),
(2, 'AD 1237 BB', '2', 'Motor', 2000, '002', 'Parkir Biasa', '09.00', '12.00', 3, 6000),
(3, 'AD 1 DB', '3', 'Sepeda', 1000, '001', 'parkir bentar', '08.00', '09.00', 1, 1000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jenis_kendaraan`
--
ALTER TABLE `jenis_kendaraan`
  ADD PRIMARY KEY (`kd_kendaraan`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kd_kategori`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`no_parkir`),
  ADD KEY `kd_kategori` (`kd_kategori`),
  ADD KEY `kd_kendaraan` (`kd_kendaraan`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`kd_kendaraan`) REFERENCES `jenis_kendaraan` (`kd_kendaraan`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`kd_kategori`) REFERENCES `kategori` (`kd_kategori`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
