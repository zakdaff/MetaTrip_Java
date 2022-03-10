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
-- Structure de la table `chambre`
--

CREATE TABLE `chambre` (
  `idc` int(11) NOT NULL,
  `numc` int(20) NOT NULL,
  `image` varchar(1000) NOT NULL,
  `type` varchar(20) NOT NULL,
  `etat` varchar(40) NOT NULL,
  `idh` int(11) NOT NULL,
  `prixc` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `chambre`
--

INSERT INTO `chambre` (`idc`, `numc`, `image`, `type`, `etat`, `idh`, `prixc`) VALUES
(139, 122, 'C:\\\\Users\\\\Nayrouz\\\\Documents\\\\NetBeansProjects\\\\metaFinal\\\\src\\\\image\\\\hotel1.jpg', 'Single', 'Disponible', 12236, 0),
(140, 5, 'C:\\\\Users\\\\Nayrouz\\\\Documents\\\\NetBeansProjects\\\\metaFinal\\\\src\\\\image\\\\cigale.jpg', 'Double', 'Disponible', 12235, 70),
(141, 20, 'ImageView[id=image_view, styleClass=image-view]', 'Double', 'Non Disponible', 12236, 80),
(143, 17, 'C:\\\\Users\\\\Nayrouz\\\\Documents\\\\NetBeansProjects\\\\metaFinal\\\\src\\\\image\\\\hotel4.jpg', 'Single', 'Disponible', 12235, 500);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD PRIMARY KEY (`idc`),
  ADD KEY `idc` (`idc`),
  ADD KEY `idh` (`idh`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `chambre`
--
ALTER TABLE `chambre`
  MODIFY `idc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=144;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD CONSTRAINT `fk_hot` FOREIGN KEY (`idh`) REFERENCES `hotel` (`Idh`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
