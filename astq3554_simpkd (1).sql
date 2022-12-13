-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Waktu pembuatan: 13 Des 2022 pada 09.16
-- Versi server: 10.3.37-MariaDB-cll-lve
-- Versi PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `astq3554_simpkd`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_access_token`
--

CREATE TABLE `tb_access_token` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_pegawai` int(10) UNSIGNED NOT NULL,
  `token` varchar(255) NOT NULL,
  `platform` enum('0','1') DEFAULT NULL COMMENT '0 = Website, 1 = Mobile'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_access_token`
--

INSERT INTO `tb_access_token` (`id`, `id_pegawai`, `token`, `platform`) VALUES
(354, 18, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTgsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NjQ3OTA1ODgsImV4cCI6MTY2NDc5MTQ4OH0.FlPqrbuQF9y4-51fu3AiCojfOpGEQeRlJ5YY0RoytHE', '1'),
(355, 20, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjAsInJvbGUiOiJQUFRLIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njc4OTg4NzMsImV4cCI6MTY2Nzg5OTc3M30.fEhAHlrMZBv8m3UZnwueKn_ZJlp4HLpjga_xOwqjyWQ', '1'),
(361, 19, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTksInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTQ2MDAyNzIsImV4cCI6MTY1NDYwMTE3Mn0.P5HEzCufhhiyIgyS_f8SigNkCwk8dt6OR0wHQvmdhnQ', '1'),
(363, 26, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjYsInJvbGUiOiJTdXBlciBBZG1pbiIsInBsYXRmb3JtIjoiTW9iaWxlIiwiaWF0IjoxNjQzMjQ3MDc2LCJleHAiOjE2NDMyNDc5NzZ9.8y5ttknqPuxwg6cygs5jBS7h_8MTtPO1u9aUvi-JpdA', '1'),
(375, 23, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjMsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTQ0OTYyNDgsImV4cCI6MTY1NDQ5NzE0OH0.fG2ft_ULZTR2xPzuMgPw1MvE5-HXRL75W1SBAijznmY', '1'),
(481, 27, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjcsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTM1MjcyMTksImV4cCI6MTY1MzUyODExOX0.SMczp4jOHwCXcIKEWGUD30cS2TIHUkMEBGSxQmEHaLU', '1'),
(521, 24, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjQsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTQ4MjE3MjUsImV4cCI6MTY1NDgyMjYyNX0.aklzWjvLrqd8aTbX1-7hWYzX4U7lrKA5Wa3SZ2cCI_E', '1'),
(530, 30, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MzAsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NjcxODkyMTIsImV4cCI6MTY2NzE5MDExMn0.Nrb108_Z85hq-WpoLCjNfMICXV4iwqpJoV6KGfFJcZ4', '1'),
(562, 31, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MzEsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTQ0MzUyNjIsImV4cCI6MTY1NDQzNjE2Mn0.iPcYw_DXedKtYHPf3nOpADpbpq5lbdaRJBNlCpkFccU', '1'),
(634, 33, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MzMsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTQ3ODkzOTYsImV4cCI6MTY1NDc5MDI5Nn0.bPQq3QETvBiXwweaJXWzXIMnIRmD50GrWHmOgbRCcsM', '1'),
(638, 22, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjIsInJvbGUiOiJLZXBhbGEgVVBUIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njc4OTg5MDAsImV4cCI6MTY2Nzg5OTgwMH0.FfG1Txrd4rq56akXAHlYY-u-SOHHiq-TbT2wW4nWhXs', '1'),
(664, 25, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjUsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTUwMzkzMDgsImV4cCI6MTY1NTA0MDIwOH0.n3pMZhggAbbWjpSSi8exJMBZocruMdVVLLasP_G11JY', '1'),
(837, 40, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NDAsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NjczODU2NzMsImV4cCI6MTY2NzM4NjU3M30.6mSDiiPVsMtdyi_8SJQ9enLng6L0TJ3MaDXHq49sfZk', '1'),
(854, 44, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NDQsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NjgxNjAwNTksImV4cCI6MTY2ODE2MDk1OX0.nZQQViT3F67izFaGtQ_APgBYUH8g2KdIdz_oXAFeVcw', '1'),
(856, 42, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NDIsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njc4OTg2NTcsImV4cCI6MTY2Nzg5OTU1N30.zNIQCtgddD3mZDkyFspsq7pn5lL2gf-gWC4pvKMmh2s', '1'),
(872, 28, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjgsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njg1ODM0MzQsImV4cCI6MTY2ODU4NDMzNH0.ycbJ7A3oyaFm_hwwJJEHCPZuGJdnEnEq-UTGGsdUC0s', '1'),
(884, 26, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjYsInJvbGUiOiJTdXBlciBBZG1pbiIsInBsYXRmb3JtIjoiV2Vic2l0ZSIsImlhdCI6MTY2ODczNTUzOSwiZXhwIjoxNjY4NzM2NDM5fQ.8_wPe5HomMz4G_tuyAXn3uXvMi9p7U7NRVArQaPo03A', '0'),
(906, 17, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTcsInJvbGUiOiJLYXN1YmFnIFRVIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njg5MzkwODUsImV4cCI6MTY2ODkzOTk4NX0.l6SWggWsp8aOL1TysjnaHPhcpGt3Up5dpI68uvKY1xQ', '1'),
(918, 41, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NDEsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NjkxMjUyNzgsImV4cCI6MTY2OTEyNjE3OH0.qHrArSmpAXhqZIWV2seSanTxibkmhR03NUzk2Huderc', '1'),
(938, 15, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTUsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njk5MDQzMjMsImV4cCI6MTY2OTkwNTIyM30.PCuIvk8heJGKtu24VHxRoTjZuwd1JGF7ZeqB_6Tzemw', '1'),
(951, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6IkFkbWluIiwicGxhdGZvcm0iOiJXZWJzaXRlIiwiaWF0IjoxNjcwMjIxMDA3LCJleHAiOjE2NzAyMjE5MDd9._SOIOa4UlFuxaoWGhjaatmwIg6H_l5rDFtKhNj41Sg4', '0'),
(952, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6IkFkbWluIiwicGxhdGZvcm0iOiJXZWJzaXRlIiwiaWF0IjoxNjcwMjIxMDA3LCJleHAiOjE2NzAyMjE5MDd9._SOIOa4UlFuxaoWGhjaatmwIg6H_l5rDFtKhNj41Sg4', '0'),
(953, 16, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTYsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NzAyMTI0OTcsImV4cCI6MTY3MDIxMzM5N30.hrI_vAyKB8LAKlz0D1--hn2qwM8vE2Ox_nadU7tT6JI', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_berita_acara`
--

CREATE TABLE `tb_berita_acara` (
  `id` int(11) UNSIGNED NOT NULL,
  `id_kendaraan` int(11) UNSIGNED NOT NULL,
  `id_kasubagTU` int(11) UNSIGNED NOT NULL,
  `id_staf` int(11) UNSIGNED NOT NULL,
  `id_admin` int(11) UNSIGNED NOT NULL,
  `no_surat_penggunaan` varchar(255) NOT NULL,
  `no_surat_serah_terima` varchar(255) NOT NULL,
  `tanggal` date NOT NULL,
  `status_pegawai` tinyint(1) NOT NULL DEFAULT 0,
  `status_kasubagTU` tinyint(1) NOT NULL DEFAULT 0,
  `status_aktif_surat` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_berita_acara`
--

INSERT INTO `tb_berita_acara` (`id`, `id_kendaraan`, `id_kasubagTU`, `id_staf`, `id_admin`, `no_surat_penggunaan`, `no_surat_serah_terima`, `tanggal`, `status_pegawai`, `status_kasubagTU`, `status_aktif_surat`) VALUES
(44, 14, 17, 33, 1, '024/001/UPTD.PAL-2022', '024/002/UPTD.PAL-2022', '2022-06-09', 1, 1, 1),
(62, 6, 17, 30, 1, '024/011/UPTD.PAL-2022', '024/012/UPTD.PAL-2022', '2022-10-31', 1, 1, 1),
(64, 22, 17, 44, 1, '024/015/UPTD.PAL-2022', '024/016/UPTD.PAL-2022', '2022-11-08', 1, 1, 1),
(78, 3, 17, 23, 1, '024/007/UPTD.PAL-2022', '024/008/UPTD.PAL-2022', '2022-12-05', 0, 0, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_dasar`
--

CREATE TABLE `tb_dasar` (
  `id` int(10) UNSIGNED NOT NULL,
  `dasar` varchar(255) NOT NULL,
  `tanggal` date NOT NULL,
  `status_aktif` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_dasar`
--

INSERT INTO `tb_dasar` (`id`, `dasar`, `tanggal`, `status_aktif`) VALUES
(2, '918/103/DPA/2021', '2021-08-10', 0),
(3, '918/004/DPA/2021', '2021-08-12', 0),
(4, '918/005DPA/2021', '2021-08-10', 0),
(5, '918/006DPA/2021', '2021-10-13', 0),
(6, '918/104/DPA/2022', '2022-05-08', 0),
(7, '918/105/DPA/2022', '2022-06-05', 0),
(8, '909/105/DPA/2022', '2022-10-30', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_detail_pemeliharaan`
--

CREATE TABLE `tb_detail_pemeliharaan` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_pemeliharaan` int(10) UNSIGNED NOT NULL,
  `id_kendaraan` int(10) UNSIGNED NOT NULL,
  `jenis_kerusakan` text NOT NULL,
  `jumlah` tinyint(3) UNSIGNED NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_detail_pemeliharaan`
--

INSERT INTO `tb_detail_pemeliharaan` (`id`, `id_pemeliharaan`, `id_kendaraan`, `jenis_kerusakan`, `jumlah`, `keterangan`) VALUES
(52, 30, 5, 'kaca pecah', 1, 'kaca pecah'),
(53, 30, 9, 'ban pecah', 1, 'ban pecah'),
(71, 47, 15, 'ban pecah', 1, 'ban pecah'),
(75, 51, 21, 'ban pecah', 1, 'ban belakang pecah'),
(76, 52, 22, 'ban pecah', 1, 'ban belakang pecah'),
(79, 55, 2, 'ban pecah', 1, 'ban pecah'),
(85, 61, 1, 'ban pecah', 1, 'ban belakang pecah');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_detail_surat_perintah_tugas`
--

CREATE TABLE `tb_detail_surat_perintah_tugas` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_surat_perintah_tugas` int(10) UNSIGNED NOT NULL,
  `id_pegawai` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_detail_surat_perintah_tugas`
--

INSERT INTO `tb_detail_surat_perintah_tugas` (`id`, `id_surat_perintah_tugas`, `id_pegawai`) VALUES
(63, 43, 24),
(64, 43, 25),
(79, 55, 40),
(80, 56, 44),
(104, 73, 16);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_detail_surat_tanggung_jawab`
--

CREATE TABLE `tb_detail_surat_tanggung_jawab` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_surat_tanggung_jawab` int(10) UNSIGNED NOT NULL,
  `id_kendaraan` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_detail_surat_tanggung_jawab`
--

INSERT INTO `tb_detail_surat_tanggung_jawab` (`id`, `id_surat_tanggung_jawab`, `id_kendaraan`) VALUES
(89, 39, 5),
(90, 39, 9),
(111, 50, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kendaraan`
--

CREATE TABLE `tb_kendaraan` (
  `id` int(11) UNSIGNED NOT NULL,
  `id_model` int(11) UNSIGNED NOT NULL,
  `no_stnk` char(12) NOT NULL,
  `no_polisi` varchar(255) NOT NULL,
  `no_bpkb` varchar(255) NOT NULL,
  `no_mesin` varchar(255) NOT NULL,
  `no_rangka` varchar(255) NOT NULL,
  `no_register_barang` varchar(255) NOT NULL,
  `warna` varchar(255) NOT NULL,
  `harga_perolehan` bigint(20) NOT NULL,
  `tgl_samsat_pertama` date NOT NULL,
  `tgl_berlaku_kir` date DEFAULT NULL,
  `id_alat_monitoring` char(16) NOT NULL,
  `status_aktif` tinyint(1) NOT NULL DEFAULT 1,
  `status_pemakaian` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_kendaraan`
--

INSERT INTO `tb_kendaraan` (`id`, `id_model`, `no_stnk`, `no_polisi`, `no_bpkb`, `no_mesin`, `no_rangka`, `no_register_barang`, `warna`, `harga_perolehan`, `tgl_samsat_pertama`, `tgl_berlaku_kir`, `id_alat_monitoring`, `status_aktif`, `status_pemakaian`) VALUES
(1, 2, 'B 19 2684890', 'DK 1432 AS', 'H 05657790-S', '191919191909', 'MH1JB8114AK510509', '10.101.101.212', 'hitam', 300000000, '2023-09-08', '0000-00-00', '00000000946112aa', 1, 1),
(2, 1, 'B 19 2684341', 'DK 1789 A', 'H 05657790-O', 'JB81E1506143', 'MH1JB8114AK510548', '02.03.01.05.001.0002', 'Merah', 200000000, '2023-11-22', '0000-00-00', '000000002fe10735', 1, 1),
(3, 2, 'B 19 2684765', 'DK 454 SD', 'H 05657721-A', '191919191978', 'MH1JB8114AK510512', '10.101.101.202', 'Merah', 1200000000, '2023-07-23', '0000-00-00', '2222222222222222', 1, 1),
(4, 3, 'B 19 2684123', 'DK 1010 WQ', 'H 05657148-L', '191919191932', 'MH1JB8114AK510512', '10.101.101.111', 'Hitam', 13000000, '2023-07-23', '0000-00-00', '3333333333333333', 1, 0),
(5, 5, 'B 19 2684213', 'DK 432 TU', 'H 05657070-T', 'JB81E1506121', 'MH1JB8114AK510765', '02.03.01.05.001.0011', 'Hitam', 10909090, '2023-11-11', '0000-00-00', '00000000946112a2', 1, 1),
(6, 5, 'B 19 2684332', 'DK 121 EW', 'H 05677790-p', 'JB81E1506190', 'MH1JB8114AK510544', '02.03.01.05.001.0076', 'Putih', 12121212, '2023-11-21', '0000-00-00', '00000000946112b4', 1, 1),
(7, 6, 'B 19 2684267', 'DK 9876 AD', 'H 05657790-6', 'JB81E1506275', 'MH1JB8114AK510126', '1.1.1.1.11.1.11111', 'Hitam', 200000000, '2023-08-09', '2023-11-22', '1234567890165489', 1, 1),
(8, 7, 'B 19 2684303', 'DK 189 UY', 'H 05657790-E', 'JB81E1506121', 'MH1JB8114AK510512', '02.03.01.05.001.0123', 'Hitam', 120000000, '2023-11-03', '0000-00-00', '1234567890342167', 1, 0),
(9, 7, 'B 19 2684312', 'DK 1010 UT', 'H 05657790-C', 'JB81E1506101', 'MH1JB8114AK510167', '02.03.01.05.001.0016', 'Merah', 120000000, '2023-11-23', '2023-11-21', '1234567890102581', 1, 1),
(10, 10, 'B 19 2684321', 'DK 1678 H', 'H 05657790-Y', 'JB81E1506097', 'MH1JB8114AK510385', '02.03.01.05.001.0002', 'Silver', 300000000, '2023-11-03', '2023-11-04', '00000000946112b5', 1, 0),
(11, 10, 'B 19 2684541', 'DK 1345 TU', 'H 05657790-G', 'JB81E1506908', 'MH1JB8114AK510536', '02.03.01.05.001.0067', 'Hitam', 500000000, '2023-01-21', '2023-10-28', '1234567890197654', 1, 0),
(12, 11, 'B 19 2684344', 'DK 1498 IF', 'H 05657790-U', 'JB81E1506143', 'MH1JB8114AK510590', '02.03.01.05.001.0109', 'Putih', 400000000, '2023-05-30', '0000-00-00', '00000000946112a6', 1, 0),
(13, 8, 'B 19 2684190', 'DK 123 AB', 'H 05657090-A', 'JB81E1506012', 'MH1JB8114AK510092', '1.1.1.1.11.1.11312', 'Merah', 500000000, '2023-06-01', '0000-00-00', '00000000946113iw', 1, 0),
(14, 3, 'B 19 2684367', 'DK 1201 AB', 'H 05646120-G', 'JB81E1506926', 'MH1JB8114AK510982', '02.03.01.05.001.1098', 'Hitam', 500000000, '2023-05-29', '0000-00-00', '00000000946113qh', 1, 1),
(15, 8, 'B 19 2684145', 'DK 1234 AB', 'H 05657139-R', 'JB81E1506028', 'MH1JB8114AK510418', '02.03.01.05.001.1032', 'Silver', 500000000, '2023-05-25', '0000-00-00', '00000000946113ja', 1, 0),
(16, 11, 'B 19 2684146', 'DK 123', 'H 05650520-K', 'JB81E1506201', 'MH1JB8114AK512892', '02.03.01.05.001.0092', 'Hitam', 500000000, '2023-11-23', '0000-00-00', '00000000946113ut', 1, 0),
(20, 8, 'B 19 2684202', 'DK 145 FG', 'H 05650597-H', 'JB81E1506001', 'MH2JB8114AK512897', '02.03.01.05.001.0193', 'Hitam', 500000000, '2023-05-31', '0000-00-00', '00000000946111ls', 1, 0),
(21, 16, 'B 19 1684321', 'DK 7821 HA', 'H 05757790-Y', 'JB81W1506101', 'MH1JB8114AS510575', '02.03.02.05.001.0114', 'Hitam', 250000000, '2023-07-26', '0000-00-00', '0000000094611289', 1, 0),
(22, 10, 'B 19 2684217', 'DK 9999 BD', 'H 98657790-O', 'JB81E1506110', 'MH1JB8414AK510548', '02.03.01.05.001.0128', 'Abu - Abu', 500000000, '2022-12-23', '2023-12-31', '00000000946112s9', 1, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_lupa_password`
--

CREATE TABLE `tb_lupa_password` (
  `id` int(11) UNSIGNED NOT NULL,
  `id_pegawai` int(11) UNSIGNED NOT NULL,
  `kode` char(5) NOT NULL,
  `waktu_kadaluarsa` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_lupa_password`
--

INSERT INTO `tb_lupa_password` (`id`, `id_pegawai`, `kode`, `waktu_kadaluarsa`) VALUES
(1, 16, '26975', '2022-11-18 10:09:42'),
(2, 1, '50843', '2022-11-20 15:10:56');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_model_kendaraan`
--

CREATE TABLE `tb_model_kendaraan` (
  `id` int(11) UNSIGNED NOT NULL,
  `tipe` varchar(255) NOT NULL,
  `merk` varchar(255) NOT NULL,
  `tahun_pembuatan` year(4) NOT NULL,
  `isi_silinder` smallint(6) UNSIGNED NOT NULL,
  `jumlah_roda` tinyint(3) UNSIGNED NOT NULL,
  `status_aktif` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_model_kendaraan`
--

INSERT INTO `tb_model_kendaraan` (`id`, `tipe`, `merk`, `tahun_pembuatan`, `isi_silinder`, `jumlah_roda`, `status_aktif`) VALUES
(1, 'NF 125 TA', 'Daihatsu', 2019, 120, 3, 1),
(2, 'Xenia 1400', 'Daihatsu', 2021, 1400, 4, 1),
(3, 'NF 150 AB', 'Honda', 2020, 125, 2, 1),
(4, 'NF 175 BB', 'Daihatsu', 2030, 150, 2, 1),
(5, 'NF 175 GB', 'Honda', 2018, 175, 2, 1),
(6, 'Avanza 1500', 'Toyota', 2017, 1500, 4, 1),
(7, 'Avanza 1200', 'Toyota', 2021, 1000, 4, 1),
(8, 'Xpander Ultimate', 'Mitsubishi', 2020, 1500, 4, 1),
(9, 'Pajero Sport', 'Mitsubishi', 2021, 1500, 4, 1),
(10, 'Truck', 'Hino', 2020, 2000, 6, 1),
(11, 'Avanza Veloz', 'Toyota', 2019, 1400, 4, 0),
(16, 'Fortuner 2000', 'Toyota', 2020, 2000, 4, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pegawai`
--

CREATE TABLE `tb_pegawai` (
  `id` int(11) UNSIGNED NOT NULL,
  `nip` char(18) DEFAULT NULL,
  `nik` char(16) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `pangkat` varchar(255) DEFAULT NULL,
  `golongan` varchar(255) DEFAULT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `jabatan` varchar(255) NOT NULL,
  `no_hp` varchar(255) NOT NULL,
  `alamat` text NOT NULL,
  `role` enum('Admin','Kasubag TU','PPTK','Kepala UPT','Staf','Super Admin') NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `foto_profil` varchar(255) NOT NULL,
  `token_notifikasi` varchar(255) DEFAULT NULL,
  `status_ubah_password` tinyint(1) NOT NULL DEFAULT 0,
  `status_aktif` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_pegawai`
--

INSERT INTO `tb_pegawai` (`id`, `nip`, `nik`, `nama`, `pangkat`, `golongan`, `jenis_kelamin`, `jabatan`, `no_hp`, `alamat`, `role`, `email`, `password`, `foto_profil`, `token_notifikasi`, `status_ubah_password`, `status_aktif`) VALUES
(1, '170555111012312345', '1234567890123454', 'Dwika Kusuma', 'VII', 'I', 'L', 'Pengurus Barang', '081090110179', 'Badung', 'Admin', 'madekomang080@gmail.com', '$2y$10$.5lzueScnkezLvzQW35ON.IAfFN0CjrH5RUZ0rQ496WR9zukaa97C', 'https://simpkd.astungkarasarjana.com/res/images/1666839970-1015589967.png', NULL, 0, 1),
(15, NULL, '5101110900123451', 'I Gusti Ayu Sutini', NULL, NULL, 'P', 'Staf OP Laboratorium', '081321432456', 'Denpasar Bali', 'Staf', 'ayusutini@gmail.com', '$2y$10$4FSd5OtyLDvmd1I8Uu3xH.wJoKC31gaewHLZ4pOtotj7CtyzSzTr.', 'https://simpkd.astungkarasarjana.com/res/images/1663662105-465311617.jpeg', 'cyR-GTg2QJKQb6z0KW7iTg:APA91bGpWzzUjUMf3dEZawWEJEaGwWbO2T5gycmPUKMDf9z1KpZcaFWt3bSyJV3zS-ZtIYI2RjyP1DwMmxbsY2b8dKtO1HnrV-GIndXGYu8Ep2hcpzaFpI7lxTLl_vltD8vLY94I8oOw', 1, 1),
(16, NULL, '5109097865431236', 'Putu Dandy Surya Mancika', NULL, NULL, 'L', 'Staf OP Laboratorium', '089754321456', 'Denpasar Timur', 'Staf', 'putumade080@gmail.com', '$2y$10$6j2F8ey.Cu8wX1e/Pwnw3.YWPOnomJACM2/4dbxQNi1.e2m59BBJC', 'https://simpkd.astungkarasarjana.com/res/images/1663668849-117371621.jpeg', 'cyR-GTg2QJKQb6z0KW7iTg:APA91bGpWzzUjUMf3dEZawWEJEaGwWbO2T5gycmPUKMDf9z1KpZcaFWt3bSyJV3zS-ZtIYI2RjyP1DwMmxbsY2b8dKtO1HnrV-GIndXGYu8Ep2hcpzaFpI7lxTLl_vltD8vLY94I8oOw', 1, 1),
(17, '196408021985032009', '5017654328907651', 'Luh Agustini,S.Sos', 'Penata Tingkat I', 'III/d', 'P', 'Kepala Sub Bagian Tata Usaha', '085137546315', 'Denpasar Timur', 'Kasubag TU', 'luhagustini@gmail.com', '$2y$10$ZydZPKvkyX9/cjP6dXBEA.0vrFA1d1DxI5SqajUI.aOIJsHRleCZy', 'https://simpkd.astungkarasarjana.com/res/images/1667899221-554548808.jpeg', 'cqohEAnPSkGOauDtqaROcJ:APA91bFq5c5ngcxE55RTgeYMVujwJFq-6frP1JoFie5XoyI2vpUPxyMLANx__RMD3t1CajEwz8jWpWYOKpY-TmAcrbfKwPA-9pCxXumbUDGiyj0g-hLXhdirSX460IKgfFkcfKzGDZgn', 1, 1),
(18, NULL, '5109874563421234', 'I Gusti Ayu Merthahardianti, S.Si., MT', NULL, NULL, 'P', 'Koordinator IPAL', '089567432123', 'Jimbaran', 'Staf', 'ayumertha@gmail.com', '$2y$10$gIzJOEMBiPLfTJM4oixZxeTVRiRsDgJL4QBn9671FA3b3VOfdWvKK', '/home/ingj4917/public_html/simpkd/res/images/avatar_female.png', 'cSqL61-OTbe8aEPWB6VLuU:APA91bEcEqFJBD942xWUAw93zl9TJP5MVChdpzrvV68iC76eLGRcFATWFBgiS3SZGXtPo4140wmVgHiQxYkyntKYVbpGmrlomVHQd13gcST-PvoZHnOMUWXW9NprwBLio-O1iLMzhbEk', 1, 1),
(19, NULL, '5105421235645431', 'Made Arjana', NULL, NULL, 'L', 'Staf OP ME IPAL', '081432156754', 'Kuta', 'Staf', 'madearjana@gmail.com', '$2y$10$HFOHxd6xrTRiZEtYLzSgDeD98yPraKG6AFLaoHArLkJk.gwi.cKbi', 'http://simpkd.ingin-wisuda.id/res/images/1654600361-888122205.jpeg', NULL, 1, 1),
(20, '197102151998031010', '5109874321234327', 'Agus Sugiarto, ST', 'Pembina', 'IV/a', 'L', 'Kepala Seksi Pelaksana Teknis', '089324156123', 'Badung', 'PPTK', 'putusujana@gmail.com', '$2y$10$tBF..5rTku0CnSTvqALHCu4LTxa1qHjzR5mwoR.vAPWZsd4tlWzSa', 'http://simpkd.ingin-wisuda.id/res/images/1654820119-37816630.jpeg', 'c3gaqjKfRxqazRXqYCmGZB:APA91bG1hF3UWjNAv9UQQE8ii5N_BCu30tlhnmbLqV5kGLvxCCFqH48a_Pvg1l3y5v4CuIUpKIYmOkMCMXhfOXWri56SIeYBuCaLuXvskdR5fRFYJX3ScKdo3DYhl0EO8SHoACK7wExP', 1, 1),
(21, NULL, '5108765342130982', 'I Gusti Ngurah Gede Mahendra Jaya, SH', NULL, NULL, 'L', 'Pengadministrasian', '085324543678', 'Sanur', 'Staf', 'ngurahgede@gmail.com', '$2y$10$3ja9kCbvsEQFxWqxgrBnNeb6IYsW2WcrBVLzvHfYEOWQfa1tdOUaS', '/home/ingj4917/public_html/simpkd/res/images/avatar_male.png', NULL, 0, 1),
(22, '196802101998031001', '5102355437658902', 'Ida Bagus Putu Ari Chandana, ST, MSi', 'Kepala UPT', 'I/a', 'L', 'Kepala UPT Pengelolaan Air Limbah Dinas PU dan Penataan Ruang Provinsi Bali', '089123432145', 'Denpasar', 'Kepala UPT', 'arichandana@gmail.com', '$2y$10$sGFSdP5sGJynK9fjvp2xFeltS51XDoBWHQimrFuvwVOU0mvzrisee', 'http://simpkd.ingin-wisuda.id/res/images/1654820187-1915614883.jpeg', 'c3gaqjKfRxqazRXqYCmGZB:APA91bG1hF3UWjNAv9UQQE8ii5N_BCu30tlhnmbLqV5kGLvxCCFqH48a_Pvg1l3y5v4CuIUpKIYmOkMCMXhfOXWri56SIeYBuCaLuXvskdR5fRFYJX3ScKdo3DYhl0EO8SHoACK7wExP', 1, 1),
(23, NULL, '5100986547890984', 'Anak Agung Anom Ardana, SH', NULL, NULL, 'L', 'Staf Administrasi', '081213244321', 'Renon', 'Staf', 'anomardana@gmail.com', '$2y$10$Sl3BH4ShSU5JoW2uvbI7peH15rxDZODZz2hCI8fz4jLTSC0OXudH6', '/home/ingj4917/public_html/simpkd/res/images/avatar_male.png', NULL, 1, 1),
(24, NULL, '5102345640891232', 'I Wayan Dana', NULL, NULL, 'L', 'Staf OP Jaringan', '089765432123', 'Ubung kaja', 'Staf', 'wayandana@gmail.com', '$2y$10$Uio8X5TYhJ7ij8p6La7z3uIN/0d5S34h.T4S3KvVOizR16fydOwGS', 'http://simpkd.ingin-wisuda.id/res/images/1654820441-464827620.jpeg', NULL, 1, 1),
(25, NULL, '5108764352341092', 'I Made Ambara', NULL, NULL, 'L', 'Staf OP Keamanan', '087564321432', 'Kuta Utara', 'Staf', 'madeambara@gmail.com', '$2y$10$ZEo883psOYXOIQ9TABcGJOWG6DswBad4gwihlty3DWxwVoXpuFEZ2', 'http://simpkd.ingin-wisuda.id/res/images/1655033587-1409838192.jpeg', NULL, 1, 1),
(26, '197002102007012023', '5105054320981236', 'Ni Nyoman Budi Artini,STP,M.Si', 'VII', 'I', 'P', 'Pengelola Pemanfaatan Barang Milik Negara', '089123432156', 'Denpasar', 'Super Admin', 'budiartini@gmail.com', '$2y$10$ul7oI2zKFRLfs2Na.VvhceXYcgnEDhG7xia0gcYjU/YaJohnCDX8a', 'http://simpkd.ingin-wisuda.id/res/images/1654405685-1225458570.png', NULL, 0, 1),
(27, NULL, '5101209871843567', 'I Wayan Adi Wiratama,S.KOM', NULL, NULL, 'L', 'Pengadministrasian Penerimaan', '089123456732', 'Denpasar Timur', 'Staf', 'adiwiratama@gmail.com', '$2y$10$csZZ2blQRuMfvSJHhOCT1.plif0X1GU7rfX3ON99.pzUxWthlvZD.', '/home/ingj4917/public_html/simpkd/res/images/avatar_male.png', NULL, 0, 1),
(28, NULL, '5109875463214091', 'I Gusti Agung Oka Garjita,ST', NULL, NULL, 'L', 'Koordinator IPLT', '087123673213', 'Denpasar', 'Staf', 'okagarjita@gmail.com', '$2y$10$dV.e/EOCPK/z20NMCISdueAXktBL0H0itCxoaYTkxV3HdnNk8QhKu', 'https://simpkd.astungkarasarjana.com/res/images/1665030465-205638869.jpeg', 'fn4CTrlXSmKO0ZOwgnEXZg:APA91bHqkt68S-_iwXLvyR_BxvYXEv4ytxfI3BG6OTGfqGt_QdmgIx47cZQakxQdBdIzHwRZfuL40Vl5iJ0AThnDyzhTdWXsrSR9XOrdnnqljFP_NSUx7gAmez3qdkJY0dauOlN8YKux', 1, 1),
(30, NULL, '1089123456098675', 'Putu Gede Tri Utama', NULL, NULL, 'L', 'Administrasi', '089123453123', 'Jimbaran', 'Staf', 'putugede@gmail.com', '$2y$10$uA3DW9hq5.R3VURf45T0L./MjjChr6LL3erAEWIE2QvI4GG9y/L6q', 'http://simpkd.ingin-wisuda.id/res/images/1654412914-1309720529.jpeg', 'eXbUeJFNTiK5nFciUCfc8Q:APA91bGONWF5G2wERvfqSdx9bGWabOGbd7VY7hIJmz5iMOOCK-_crWKrdgHsjHYycEACd09qQtdiQiAiguqhwTI-eQ7-NRLrTByBn7_NVSLMLn1ZYz9n7VAGkSCoK4DDx79sC12wVhQZ', 1, 1),
(31, NULL, '5109091292315461', 'I Putu Mahendra', NULL, NULL, 'L', 'Staf IPAL', '0812109234567', 'Jimbaran', 'Staf', 'putumahendra@gmail.com', '$2y$10$iAL5sMdweZH64Jigodv/tOzwUa2QlJnhZ59gGaQ2jm.85vbjh2CHW', 'http://simpkd.ingin-wisuda.id/res/images/1654407552-2067013747.jpeg', NULL, 1, 1),
(33, NULL, '5100121234784567', 'I Made Surya', NULL, NULL, 'L', 'Staf IPAL', '081294678234', 'Jimbaran', 'Staf', 'madesurya@gmail.com', '$2y$10$kjzz3LnjtTa7yBSiY5plDe85g5CvpDZ4VSDXCZrHcgexBJVkVtlyu', '/home/ingj4917/public_html/simpkd/res/images/avatar_male.png', NULL, 1, 1),
(40, '', '5105551109123456', 'I Made Rendi', '', '', 'L', 'Staf IPAL', '089123465789', 'Jimbaran', 'Staf', 'maderendi@gmail.com', '$2y$10$18sJ0nJoH5SVhJ6.dZtHhuGsgj7hKhqO1Eq1Wim9ExYqDnvTwf6xW', '/home/ingj4917/public_html/simpkd/res/images/avatar_male.png', 'cWSYEAFkSaOxVKpauzYLj8:APA91bHMIZg-w2x1IOQDtALmdBHZC6eCVk9zvM5N8BsE9nCaLGc8Bo6kN6ot8OaQgiIZl6pzR3uFeLcrC-dG3OUQMwuPEQXVOQ07EnKlBBNGDvyEppaBXvxSOajmFQZb2r1a4Bqqn94y', 1, 1),
(41, NULL, '5101231234321236', 'I Gede Yoga', NULL, NULL, 'L', 'Staf IPLT', '085678432124', 'Jimbaran', 'Staf', 'gedeyoga@gmail.com', '$2y$10$32AU7XhwlIqf6pQmjzaUX.FCL4ZgyGL2Y0cwDIe4rR.VyktVCQKQe', '/home/astq3554/public_html/simpkd/res/images/avatar_male.png', 'eXbUeJFNTiK5nFciUCfc8Q:APA91bGONWF5G2wERvfqSdx9bGWabOGbd7VY7hIJmz5iMOOCK-_crWKrdgHsjHYycEACd09qQtdiQiAiguqhwTI-eQ7-NRLrTByBn7_NVSLMLn1ZYz9n7VAGkSCoK4DDx79sC12wVhQZ', 1, 1),
(42, NULL, '5101234125681230', 'I Gede Randu', NULL, NULL, 'L', 'Staf Administrasi', '08143578129', 'Denpasar', 'Staf', 'gederandu@gmail.com', '$2y$10$6soVhrNfVNXyhfxs0GepqORkH2ZibIxnxQ8fZGtTGO8.OK30RGBAi', '/home/astq3554/public_html/simpkd/res/images/avatar_male.png', 'c3gaqjKfRxqazRXqYCmGZB:APA91bG1hF3UWjNAv9UQQE8ii5N_BCu30tlhnmbLqV5kGLvxCCFqH48a_Pvg1l3y5v4CuIUpKIYmOkMCMXhfOXWri56SIeYBuCaLuXvskdR5fRFYJX3ScKdo3DYhl0EO8SHoACK7wExP', 1, 1),
(43, NULL, '5105550901232134', 'Gede Reza', NULL, NULL, 'L', 'Staf IPAL', '081267890123', 'Jimbaran', 'Staf', 'gedereza@gmail.com', '$2y$10$/MUZMNb/TvZ3NArpI2fW0uFvDGvAeqJXfzzwNJfhx5vC9oXl5kKtm', '/home/astq3554/public_html/simpkd/res/images/avatar_male.png', NULL, 0, 1),
(44, '', '5101234789012784', 'I Made Juni Kusuma', '', '', 'L', 'Staf IPAL', '089125678901', 'Denpasar', 'Staf', 'madejuni@gmail.com', '$2y$10$8ymdyZD9wmSYt4wCUvsTA.ZVksbofn37T3Fm153TKLPqEbIWbGezi', 'https://simpkd.astungkarasarjana.com/res/images/1667898433-73114727.jpeg', 'fn4CTrlXSmKO0ZOwgnEXZg:APA91bHqkt68S-_iwXLvyR_BxvYXEv4ytxfI3BG6OTGfqGt_QdmgIx47cZQakxQdBdIzHwRZfuL40Vl5iJ0AThnDyzhTdWXsrSR9XOrdnnqljFP_NSUx7gAmez3qdkJY0dauOlN8YKux', 1, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pemeliharaan_kendaraan`
--

CREATE TABLE `tb_pemeliharaan_kendaraan` (
  `id` int(11) UNSIGNED NOT NULL,
  `id_kepalaUPT` int(11) UNSIGNED NOT NULL,
  `id_kasubagTU` int(11) UNSIGNED NOT NULL,
  `id_pptk` int(11) UNSIGNED NOT NULL,
  `id_staf` int(11) UNSIGNED NOT NULL,
  `nomor_surat` varchar(255) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `status_kepalaUPT` tinyint(1) NOT NULL DEFAULT 0,
  `status_kasubagTU` tinyint(1) NOT NULL DEFAULT 0,
  `status_pptk` tinyint(1) NOT NULL DEFAULT 0,
  `status_aktif_surat` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_pemeliharaan_kendaraan`
--

INSERT INTO `tb_pemeliharaan_kendaraan` (`id`, `id_kepalaUPT`, `id_kasubagTU`, `id_pptk`, `id_staf`, `nomor_surat`, `tanggal`, `status_kepalaUPT`, `status_kasubagTU`, `status_pptk`, `status_aktif_surat`) VALUES
(30, 22, 17, 20, 24, '27/001/UPTD.PAL/2022', '2022-06-08', 1, 1, 1, 1),
(47, 22, 17, 20, 15, '27/003/UPTD.PAL/2022', '2022-09-09', 1, 1, 1, 1),
(51, 22, 17, 20, 40, '27/005/UPTD.PAL/2022', '2022-10-25', 1, 1, 1, 1),
(52, 22, 17, 20, 44, '27/006/UPTD.PAL/2022', '2022-11-07', 1, 1, 1, 1),
(55, 22, 17, 20, 15, '27/005/UPTD.PAL/2022', '2022-11-17', 0, 0, 0, 1),
(61, 22, 17, 20, 16, '27/006/UPTD.PAL/2022', '2022-12-05', 0, 0, 0, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_refresh_token`
--

CREATE TABLE `tb_refresh_token` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_pegawai` int(10) UNSIGNED NOT NULL,
  `token` varchar(255) NOT NULL,
  `platform` enum('0','1') DEFAULT NULL COMMENT '0 = Website, 1 = Mobile'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_refresh_token`
--

INSERT INTO `tb_refresh_token` (`id`, `id_pegawai`, `token`, `platform`) VALUES
(44, 15, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTUsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njk5MDQzMjN9.npMWOkNGanotplkzgFLeLBlj6Z2ZxYaSJUpCCYIHgQQ', '1'),
(45, 24, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjQsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTQ4MjE3MjV9.KUG5yNXITm1Rrtpzd9YAKptmg_B-Lc5L5-zccRB7Wto', '1'),
(46, 17, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTcsInJvbGUiOiJLYXN1YmFnIFRVIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njg5MzkwODV9.-G_rLaxQJq9bC-pnWDMQwm4BkhcpUqFGW3VMAWhpIqk', '1'),
(47, 25, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjUsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTUwMzkzMDh9.A-fh4TVIcbQojATph_Vm30uaDRXHGxFbbTsfnxxPzCk', '1'),
(48, 18, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTgsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NjQ3OTA1ODh9.f3aInf4EXaQJpiBQEt9BrUvMow_GezNsWXPx4-uFt8U', '1'),
(49, 20, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjAsInJvbGUiOiJQUFRLIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njc4OTg4NzN9.pjmTWwjA9cNLFYkNA-jmcamxY5ITR1L7gVY1Ax5ehS0', '1'),
(50, 28, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjgsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njg1ODM0MzR9.rNgyFRCd5nyfOo4PheBlQyk6_dE8Ndb9tqiV8xyzyjc', '1'),
(52, 19, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTksInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTQ2MDAyNzJ9.KOeb_XwqxWbNK7oQNDWTT_AYi6-w8BKEClmiT9ZFgP0', '1'),
(53, 26, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjYsInJvbGUiOiJTdXBlciBBZG1pbiIsInBsYXRmb3JtIjoiTW9iaWxlIiwiaWF0IjoxNjQzMjQ3MDc2LCJleHAiOjE2NDMyNTc4NzZ9._xo8D0f1ZePQxBb3kNBu7ajdfwtWD7jMc4PIPiHWhMA', '1'),
(54, 23, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjMsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTQ0OTYyNDh9.qc5rwlV7Rwo5p17Hl-b2bbYqGddifTC2heYqUgBfBz0', '1'),
(57, 22, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjIsInJvbGUiOiJLZXBhbGEgVVBUIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njc4OTg5MDB9.UyKwhnqh7YtOlVETsEZB7VOkxKhrD_98lrITsoaIgAE', '1'),
(58, 27, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjcsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTM1MjcyMTl9.ihVgvKXE8RK1TqRme7WcWswT16rLKCHpsDCXTh6p4a0', '1'),
(59, 30, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MzAsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NjcxODkyMTJ9.NnDMpdAElS-EeQuRgosHv-IpKwh5KCts5ql_wXnz9N8', '1'),
(63, 31, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MzEsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTQ0MzUyNjJ9.Ab_107tOb2zaktbVWYB32n84IH4aIq-e60VeTSpK9-U', '1'),
(65, 16, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MTYsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NzAyMTI0OTd9.0ppkJVgCw6qsu1WfGJ8UjlIygR9L40q-MbyiWIOWPGc', '1'),
(70, 33, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MzMsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NTQ3ODkzOTZ9.SfNfNKeQPopDZG0mm93eEDnkrbn4L18hsVGhYw4Mxbw', '1'),
(79, 40, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NDAsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NjczODU2NzN9.yIY1RfFK_zeYmEjd-sr8v1RGwahRrNHZEcQr8Mx7Bb8', '1'),
(103, 26, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjYsInJvbGUiOiJTdXBlciBBZG1pbiIsInBsYXRmb3JtIjoiV2Vic2l0ZSIsImlhdCI6MTY2ODczNTUzOSwiZXhwIjoxNjY4NzQ2MzM5fQ.sFvtowHt3FAQBOB4oo10udemwV4sGckP__ySmZnda-I', '0'),
(104, 44, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NDQsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NjgxNjAwNTl9.x2EYmUslhSt3WqPQXvrT1nQKWmhpsYXYzeFmgM1HVnQ', '1'),
(105, 42, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NDIsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2Njc4OTg2NTd9.cMbU8-bsUXSZhLmGPmfWRqfL1dq__cJ2LGz7LYPjlNw', '1'),
(106, 41, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NDEsInJvbGUiOiJTdGFmIiwicGxhdGZvcm0iOiJNb2JpbGUiLCJpYXQiOjE2NjkxMjUyNzh9.eD-O7GKoqWNZvDb6XOyLu3_Z41MOsanpFU5uVww_974', '1'),
(107, 1, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwicm9sZSI6IkFkbWluIiwicGxhdGZvcm0iOiJXZWJzaXRlIiwiaWF0IjoxNjcwMjIxMDA3LCJleHAiOjE2NzAyMzE4MDd9.WQK88ErPg7ghyF9o_kTvIvRMr0QtEjFN_nRcUzTqo1M', '0');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_surat_perintah_tugas`
--

CREATE TABLE `tb_surat_perintah_tugas` (
  `id` int(11) UNSIGNED NOT NULL,
  `id_dasar_surat` int(10) UNSIGNED NOT NULL,
  `id_pemberi_tugas` int(11) UNSIGNED NOT NULL,
  `id_kasubagTU` int(11) UNSIGNED NOT NULL,
  `id_kendaraan` int(11) UNSIGNED NOT NULL,
  `no_surat` varchar(255) NOT NULL,
  `tujuan_pemakaian` text NOT NULL,
  `tanggal_mulai` date NOT NULL,
  `tanggal_selesai` date NOT NULL,
  `surat_disposisi` varchar(255) NOT NULL,
  `status_pemberi_tugas` tinyint(1) NOT NULL,
  `status_kasubagTU` tinyint(1) NOT NULL DEFAULT 0,
  `status_aktif_surat` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_surat_perintah_tugas`
--

INSERT INTO `tb_surat_perintah_tugas` (`id`, `id_dasar_surat`, `id_pemberi_tugas`, `id_kasubagTU`, `id_kendaraan`, `no_surat`, `tujuan_pemakaian`, `tanggal_mulai`, `tanggal_selesai`, `surat_disposisi`, `status_pemberi_tugas`, `status_kasubagTU`, `status_aktif_surat`) VALUES
(43, 7, 28, 17, 13, '800/001/UPTD.PAL-DISPUPR', 'monitoring dinas', '2022-06-11', '2022-06-13', 'http://simpkd.ingin-wisuda.id/res/images/1654789829-924003152.pdf', 1, 1, 1),
(55, 8, 15, 17, 15, '800/009/UPTD.PAL-DISPUPR', 'Tugas pengolahan air limbah', '2022-10-31', '2022-11-02', 'https://simpkd.astungkarasarjana.com/res/images/1667189706-2055571464.png', 1, 1, 1),
(56, 8, 15, 17, 5, '800/010/UPTD.PAL-DISPUPR', 'Monitoring Air Limbah', '2022-11-08', '2022-11-11', 'https://simpkd.astungkarasarjana.com/res/images/1667899031-210967742.png', 1, 1, 1),
(73, 8, 15, 17, 4, '800/004/UPTD.PAL-DISPUPR', 'monitoring air limbah', '2022-12-05', '2022-12-06', 'https://simpkd.astungkarasarjana.com/res/images/1670212710-1292414133.jpeg', 0, 0, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_surat_tanggung_jawab`
--

CREATE TABLE `tb_surat_tanggung_jawab` (
  `id` int(11) UNSIGNED NOT NULL,
  `id_pemberi` int(11) UNSIGNED NOT NULL,
  `id_penerima` int(11) UNSIGNED NOT NULL,
  `id_admin` int(11) UNSIGNED NOT NULL,
  `no_surat` varchar(255) NOT NULL,
  `tanggal` date NOT NULL,
  `status_pemberi` tinyint(1) NOT NULL DEFAULT 0,
  `status_penerima` tinyint(1) NOT NULL DEFAULT 0,
  `status_aktif_surat` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data untuk tabel `tb_surat_tanggung_jawab`
--

INSERT INTO `tb_surat_tanggung_jawab` (`id`, `id_pemberi`, `id_penerima`, `id_admin`, `no_surat`, `tanggal`, `status_pemberi`, `status_penerima`, `status_aktif_surat`) VALUES
(39, 17, 24, 1, '024/008/UPTD.PAL-2022', '2022-06-09', 1, 1, 1),
(50, 17, 16, 1, '024/017/UPTD.PAL-2022', '2022-09-09', 1, 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_access_token`
--
ALTER TABLE `tb_access_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- Indeks untuk tabel `tb_berita_acara`
--
ALTER TABLE `tb_berita_acara`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kendaraan` (`id_kendaraan`),
  ADD KEY `id_kasubag` (`id_kasubagTU`),
  ADD KEY `id_staf` (`id_staf`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Indeks untuk tabel `tb_dasar`
--
ALTER TABLE `tb_dasar`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_detail_pemeliharaan`
--
ALTER TABLE `tb_detail_pemeliharaan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pemeliharaan` (`id_pemeliharaan`),
  ADD KEY `id_kendaraan` (`id_kendaraan`);

--
-- Indeks untuk tabel `tb_detail_surat_perintah_tugas`
--
ALTER TABLE `tb_detail_surat_perintah_tugas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_surat_perintah_tugas` (`id_surat_perintah_tugas`),
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- Indeks untuk tabel `tb_detail_surat_tanggung_jawab`
--
ALTER TABLE `tb_detail_surat_tanggung_jawab`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_surat_tanggung_jawab` (`id_surat_tanggung_jawab`),
  ADD KEY `id_kendaraan` (`id_kendaraan`);

--
-- Indeks untuk tabel `tb_kendaraan`
--
ALTER TABLE `tb_kendaraan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_model_kendaraan` (`id_model`);

--
-- Indeks untuk tabel `tb_lupa_password`
--
ALTER TABLE `tb_lupa_password`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- Indeks untuk tabel `tb_model_kendaraan`
--
ALTER TABLE `tb_model_kendaraan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_pegawai`
--
ALTER TABLE `tb_pegawai`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_pemeliharaan_kendaraan`
--
ALTER TABLE `tb_pemeliharaan_kendaraan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kasubagTU` (`id_kasubagTU`),
  ADD KEY `id_pptk` (`id_pptk`),
  ADD KEY `id_staf` (`id_staf`);

--
-- Indeks untuk tabel `tb_refresh_token`
--
ALTER TABLE `tb_refresh_token`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pegawai` (`id_pegawai`);

--
-- Indeks untuk tabel `tb_surat_perintah_tugas`
--
ALTER TABLE `tb_surat_perintah_tugas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kasubagTU` (`id_kasubagTU`),
  ADD KEY `id_kendaraan` (`id_kendaraan`),
  ADD KEY `id_dasar_surat` (`id_dasar_surat`),
  ADD KEY `id_pemberi_tugas` (`id_pemberi_tugas`);

--
-- Indeks untuk tabel `tb_surat_tanggung_jawab`
--
ALTER TABLE `tb_surat_tanggung_jawab`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pemberi` (`id_pemberi`),
  ADD KEY `id_penerima` (`id_penerima`),
  ADD KEY `id_admin` (`id_admin`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tb_access_token`
--
ALTER TABLE `tb_access_token`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=954;

--
-- AUTO_INCREMENT untuk tabel `tb_berita_acara`
--
ALTER TABLE `tb_berita_acara`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT untuk tabel `tb_dasar`
--
ALTER TABLE `tb_dasar`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `tb_detail_pemeliharaan`
--
ALTER TABLE `tb_detail_pemeliharaan`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT untuk tabel `tb_detail_surat_perintah_tugas`
--
ALTER TABLE `tb_detail_surat_perintah_tugas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT untuk tabel `tb_detail_surat_tanggung_jawab`
--
ALTER TABLE `tb_detail_surat_tanggung_jawab`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT untuk tabel `tb_kendaraan`
--
ALTER TABLE `tb_kendaraan`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT untuk tabel `tb_lupa_password`
--
ALTER TABLE `tb_lupa_password`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `tb_model_kendaraan`
--
ALTER TABLE `tb_model_kendaraan`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT untuk tabel `tb_pegawai`
--
ALTER TABLE `tb_pegawai`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT untuk tabel `tb_pemeliharaan_kendaraan`
--
ALTER TABLE `tb_pemeliharaan_kendaraan`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT untuk tabel `tb_refresh_token`
--
ALTER TABLE `tb_refresh_token`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT untuk tabel `tb_surat_perintah_tugas`
--
ALTER TABLE `tb_surat_perintah_tugas`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT untuk tabel `tb_surat_tanggung_jawab`
--
ALTER TABLE `tb_surat_tanggung_jawab`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_access_token`
--
ALTER TABLE `tb_access_token`
  ADD CONSTRAINT `tb_access_token_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `tb_pegawai` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_berita_acara`
--
ALTER TABLE `tb_berita_acara`
  ADD CONSTRAINT `tb_berita_acara_ibfk_1` FOREIGN KEY (`id_kendaraan`) REFERENCES `tb_kendaraan` (`id`),
  ADD CONSTRAINT `tb_berita_acara_ibfk_2` FOREIGN KEY (`id_kasubagTU`) REFERENCES `tb_pegawai` (`id`),
  ADD CONSTRAINT `tb_berita_acara_ibfk_3` FOREIGN KEY (`id_staf`) REFERENCES `tb_pegawai` (`id`),
  ADD CONSTRAINT `tb_berita_acara_ibfk_4` FOREIGN KEY (`id_admin`) REFERENCES `tb_pegawai` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_detail_pemeliharaan`
--
ALTER TABLE `tb_detail_pemeliharaan`
  ADD CONSTRAINT `tb_detail_pemeliharaan_ibfk_1` FOREIGN KEY (`id_pemeliharaan`) REFERENCES `tb_pemeliharaan_kendaraan` (`id`),
  ADD CONSTRAINT `tb_detail_pemeliharaan_ibfk_2` FOREIGN KEY (`id_kendaraan`) REFERENCES `tb_kendaraan` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_detail_surat_perintah_tugas`
--
ALTER TABLE `tb_detail_surat_perintah_tugas`
  ADD CONSTRAINT `tb_detail_surat_perintah_tugas_ibfk_1` FOREIGN KEY (`id_surat_perintah_tugas`) REFERENCES `tb_surat_perintah_tugas` (`id`),
  ADD CONSTRAINT `tb_detail_surat_perintah_tugas_ibfk_2` FOREIGN KEY (`id_pegawai`) REFERENCES `tb_pegawai` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_detail_surat_tanggung_jawab`
--
ALTER TABLE `tb_detail_surat_tanggung_jawab`
  ADD CONSTRAINT `tb_detail_surat_tanggung_jawab_ibfk_1` FOREIGN KEY (`id_surat_tanggung_jawab`) REFERENCES `tb_surat_tanggung_jawab` (`id`),
  ADD CONSTRAINT `tb_detail_surat_tanggung_jawab_ibfk_2` FOREIGN KEY (`id_kendaraan`) REFERENCES `tb_kendaraan` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_kendaraan`
--
ALTER TABLE `tb_kendaraan`
  ADD CONSTRAINT `tb_kendaraan_ibfk_1` FOREIGN KEY (`id_model`) REFERENCES `tb_model_kendaraan` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_lupa_password`
--
ALTER TABLE `tb_lupa_password`
  ADD CONSTRAINT `tb_lupa_password_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `tb_pegawai` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_pemeliharaan_kendaraan`
--
ALTER TABLE `tb_pemeliharaan_kendaraan`
  ADD CONSTRAINT `tb_pemeliharaan_kendaraan_ibfk_1` FOREIGN KEY (`id_kasubagTU`) REFERENCES `tb_pegawai` (`id`),
  ADD CONSTRAINT `tb_pemeliharaan_kendaraan_ibfk_2` FOREIGN KEY (`id_pptk`) REFERENCES `tb_pegawai` (`id`),
  ADD CONSTRAINT `tb_pemeliharaan_kendaraan_ibfk_3` FOREIGN KEY (`id_staf`) REFERENCES `tb_pegawai` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_refresh_token`
--
ALTER TABLE `tb_refresh_token`
  ADD CONSTRAINT `tb_refresh_token_ibfk_1` FOREIGN KEY (`id_pegawai`) REFERENCES `tb_pegawai` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_surat_perintah_tugas`
--
ALTER TABLE `tb_surat_perintah_tugas`
  ADD CONSTRAINT `tb_surat_perintah_tugas_ibfk_1` FOREIGN KEY (`id_kasubagTU`) REFERENCES `tb_pegawai` (`id`),
  ADD CONSTRAINT `tb_surat_perintah_tugas_ibfk_2` FOREIGN KEY (`id_kendaraan`) REFERENCES `tb_kendaraan` (`id`),
  ADD CONSTRAINT `tb_surat_perintah_tugas_ibfk_3` FOREIGN KEY (`id_dasar_surat`) REFERENCES `tb_dasar` (`id`),
  ADD CONSTRAINT `tb_surat_perintah_tugas_ibfk_4` FOREIGN KEY (`id_pemberi_tugas`) REFERENCES `tb_pegawai` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_surat_tanggung_jawab`
--
ALTER TABLE `tb_surat_tanggung_jawab`
  ADD CONSTRAINT `tb_surat_tanggung_jawab_ibfk_1` FOREIGN KEY (`id_pemberi`) REFERENCES `tb_pegawai` (`id`),
  ADD CONSTRAINT `tb_surat_tanggung_jawab_ibfk_2` FOREIGN KEY (`id_penerima`) REFERENCES `tb_pegawai` (`id`),
  ADD CONSTRAINT `tb_surat_tanggung_jawab_ibfk_3` FOREIGN KEY (`id_admin`) REFERENCES `tb_pegawai` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
