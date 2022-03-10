-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 10 mars 2022 à 18:26
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `metatrip`
--

-- --------------------------------------------------------

--
-- Structure de la table `hotel`
--

CREATE TABLE `hotel` (
  `Idh` int(11) NOT NULL,
  `Nom_hotel` varchar(20) NOT NULL,
  `Nb_etoiles` int(11) NOT NULL,
  `Adresse` varchar(50) NOT NULL,
  `image` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hotel`
--

INSERT INTO `hotel` (`Idh`, `Nom_hotel`, `Nb_etoiles`, `Adresse`, `image`) VALUES
(12235, 'mouradi', 5, 'hammamet', 'C:\\Users\\Nayrouz\\Documents\\NetBeansProjects\\metaFinal\\src\\image\\hotel2.jpeg'),
(12236, 'movenpick ', 5, 'gammarth', 'C:UsersNayrouzDocumentsNetBeansProjectsmetaFinalsrcimagehotel3.jpg'),
(12238, 'la cigale ', 5, 'taba', 'C:\\Users\\Nayrouz\\Documents\\NetBeansProjects\\metaFinal\\src\\image\\cigale.jpg'),
(12239, 'golden tulip', 4, 'tunis', 'C:\\\\Users\\\\Nayrouz\\\\Documents\\\\NetBeansProjects\\\\metaFinal\\\\src\\\\image\\\\hotel4.jpg'),
(12240, 'movenpick ', 4, 'gammarth', 'C:UsersNayrouzDocumentsNetBeansProjectsmetaFinalsrcimagehotel3.jpg');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`Idh`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `Idh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12241;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
