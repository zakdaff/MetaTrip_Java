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
-- Structure de la table `reservation_hotel`
--

CREATE TABLE `reservation_hotel` (
  `Idrh` int(11) NOT NULL,
  `Nb_nuitees` int(11) NOT NULL,
  `Nb_personnes` int(11) NOT NULL,
  `Prix` float NOT NULL,
  `Idu` int(11) NOT NULL,
  `idh` int(11) NOT NULL,
  `Date_depart` date DEFAULT NULL,
  `Date_arrivee` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation_hotel`
--

INSERT INTO `reservation_hotel` (`Idrh`, `Nb_nuitees`, `Nb_personnes`, `Prix`, `Idu`, `idh`, `Date_depart`, `Date_arrivee`) VALUES
(24, 5, 10, 3000, 41, 12236, '2022-03-15', '2022-03-17'),
(25, 2, 2, 240, 41, 12235, '2022-03-08', '2022-03-08'),
(26, 4, 2, 0, 41, 12236, '2022-03-03', '2022-03-17'),
(29, 5, 5, 1500, 41, 12236, '2022-03-09', '2022-03-24'),
(31, 5, 5, 1500, 41, 12236, '2022-03-17', '2022-03-18'),
(32, 5, 2, 600, 41, 12235, '2022-03-09', '2022-03-16'),
(33, 5, 4, 1200, 41, 12239, '2022-03-23', '2022-03-27'),
(34, 5, 2, 600, 41, 12235, '2022-03-10', '2022-03-14'),
(35, 2, 2, 240, 41, 12235, '2022-03-01', '2022-03-08'),
(36, 2, 2, 240, 41, 12235, '2022-03-01', '2022-03-15');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `reservation_hotel`
--
ALTER TABLE `reservation_hotel`
  ADD PRIMARY KEY (`Idrh`),
  ADD KEY `Idrh` (`Idrh`),
  ADD KEY `FK_u` (`Idu`),
  ADD KEY `kk_h` (`idh`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `reservation_hotel`
--
ALTER TABLE `reservation_hotel`
  MODIFY `Idrh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reservation_hotel`
--
ALTER TABLE `reservation_hotel`
  ADD CONSTRAINT `FK_u` FOREIGN KEY (`Idu`) REFERENCES `user` (`Idu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kk_h` FOREIGN KEY (`idh`) REFERENCES `hotel` (`Idh`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
