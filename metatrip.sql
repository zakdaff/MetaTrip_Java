-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 17 fév. 2022 à 18:04
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
-- Structure de la table `abonnement`
--

CREATE TABLE `abonnement` (
  `Ida` int(11) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Prix_a` int(11) NOT NULL,
  `Date_achat` date NOT NULL,
  `Date_expiration` date NOT NULL,
  `Etat` varchar(20) NOT NULL,
  `Ref_paiement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `credit_card`
--

CREATE TABLE `credit_card` (
  `Id_c` int(11) NOT NULL,
  `Num_carte` double NOT NULL,
  `Code_tpe` int(11) NOT NULL,
  `Idu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `Ide` int(11) NOT NULL,
  `Type_event` varchar(20) NOT NULL,
  `Chanteur` varchar(20) NOT NULL,
  `Adresse` varchar(20) NOT NULL,
  `Date_event` date NOT NULL,
  `prix_e` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `hotel`
--

CREATE TABLE `hotel` (
  `Idh` int(11) NOT NULL,
  `Nom_hotel` varchar(20) NOT NULL,
  `Nb_etoiles` int(11) NOT NULL,
  `Adresse` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `Ref_paiement` int(11) NOT NULL,
  `Date_paiement` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`Ref_paiement`, `Date_paiement`) VALUES
(1, '2022-02-16');

-- --------------------------------------------------------

--
-- Structure de la table `reservation_event`
--

CREATE TABLE `reservation_event` (
  `Idrev` int(11) NOT NULL,
  `Nb_pers` int(11) NOT NULL,
  `Ide` int(11) NOT NULL,
  `Idu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation_hotel`
--

CREATE TABLE `reservation_hotel` (
  `Idrh` int(11) NOT NULL,
  `Type_room` varchar(20) NOT NULL,
  `Nb_nuitees` int(11) NOT NULL,
  `Nb_personnes` int(11) NOT NULL,
  `Prix` float NOT NULL,
  `Idh` int(11) NOT NULL,
  `Idu` int(11) NOT NULL,
  `Date_depart` date DEFAULT NULL,
  `Date_arrivee` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation_voiture`
--

CREATE TABLE `reservation_voiture` (
  `Idrvoit` int(11) NOT NULL,
  `prix_rent` float NOT NULL,
  `Chauffeur` varchar(20) NOT NULL,
  `Trajet` varchar(20) NOT NULL,
  `Idu` int(11) NOT NULL,
  `Idvoit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reservation_voyage`
--

CREATE TABLE `reservation_voyage` (
  `Idrv` int(11) NOT NULL,
  `Date_depart` date NOT NULL,
  `Date_arrivee` date NOT NULL,
  `etat` varchar(20) NOT NULL,
  `Idu` int(11) NOT NULL,
  `Idv` int(11) NOT NULL,
  `Ref_paiement` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation_voyage`
--

INSERT INTO `reservation_voyage` (`Idrv`, `Date_depart`, `Date_arrivee`, `etat`, `Idu`, `Idv`, `Ref_paiement`) VALUES
(7, '2022-02-16', '2022-02-16', 'Paye', 26, 97, 0),
(9, '2022-02-16', '2022-02-16', 'NonPaye', 26, 97, 0),
(10, '2020-09-01', '2050-09-01', 'Paye', 26, 97, 0),
(11, '2050-09-01', '2050-09-01', 'Paye', 26, 97, 0);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `Idu` int(11) NOT NULL,
  `Cin` double NOT NULL,
  `Nom` varchar(20) NOT NULL,
  `Prenom` varchar(20) NOT NULL,
  `Tel` double NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Image` varchar(20) NOT NULL,
  `Role` int(11) DEFAULT 0,
  `dateNaissance` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`Idu`, `Cin`, `Nom`, `Prenom`, `Tel`, `Email`, `Password`, `Image`, `Role`, `dateNaissance`) VALUES
(1, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(2, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(3, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(4, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(5, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(6, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(7, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(8, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(9, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(10, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(11, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(12, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(13, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(14, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(15, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(16, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(17, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(18, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(19, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(20, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(21, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(22, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(23, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(24, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(25, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(26, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(27, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(28, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(29, 1255, 'ssss', 'cxx', 256845, 'salma@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(30, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(31, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(32, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(33, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(34, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(35, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(36, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(37, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(38, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(39, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL),
(40, 195, 'ssss', 'cxx', 256845, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

CREATE TABLE `voiture` (
  `Idvoit` int(11) NOT NULL,
  `Matricule` varchar(50) NOT NULL,
  `Puissance_fiscalle` int(11) NOT NULL,
  `Image_v` varchar(50) NOT NULL,
  `Modele` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `voyage`
--

CREATE TABLE `voyage` (
  `Idv` int(11) NOT NULL,
  `Pays` varchar(20) NOT NULL,
  `Image_pays` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `voyage`
--

INSERT INTO `voyage` (`Idv`, `Pays`, `Image_pays`) VALUES
(97, 'istanbul', 'c://antalya.png'),
(98, 'tounis', 'c://maroc.png'),
(99, 'tounis', 'c://maroc.png'),
(100, 'tounis', 'c://maroc.png'),
(369, 'tounis', 'c://maroc.png');

-- --------------------------------------------------------

--
-- Structure de la table `voyage_organise`
--

CREATE TABLE `voyage_organise` (
  `Idvo` int(11) NOT NULL,
  `Prix_billet` float NOT NULL,
  `Airline` varchar(20) NOT NULL,
  `Nb_nuitees` int(11) NOT NULL,
  `Idv` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `voyage_organise`
--

INSERT INTO `voyage_organise` (`Idvo`, `Prix_billet`, `Airline`, `Nb_nuitees`, `Idv`) VALUES
(78, 170.6, 'nex', 3, 369),
(79, 990.6, 'sounay', 3, 369),
(80, 170.6, 'nex', 3, 369),
(81, 10.6, 'flam', 3, 369);

-- --------------------------------------------------------

--
-- Structure de la table `voyage_virtuel`
--

CREATE TABLE `voyage_virtuel` (
  `Idvv` int(11) NOT NULL,
  `Video` varchar(50) NOT NULL,
  `Image_v` varchar(50) NOT NULL,
  `Idv` int(11) NOT NULL,
  `Ida` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD PRIMARY KEY (`Ida`),
  ADD KEY `Ida` (`Ida`),
  ADD KEY `FK_pai` (`Ref_paiement`);

--
-- Index pour la table `credit_card`
--
ALTER TABLE `credit_card`
  ADD PRIMARY KEY (`Id_c`),
  ADD KEY `Id_c` (`Id_c`),
  ADD KEY `FKus` (`Idu`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`Ide`);

--
-- Index pour la table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`Idh`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`Ref_paiement`);

--
-- Index pour la table `reservation_event`
--
ALTER TABLE `reservation_event`
  ADD PRIMARY KEY (`Idrev`),
  ADD KEY `Idrev` (`Idrev`),
  ADD KEY `Fk_eve` (`Ide`),
  ADD KEY `Fk_usr` (`Idu`);

--
-- Index pour la table `reservation_hotel`
--
ALTER TABLE `reservation_hotel`
  ADD PRIMARY KEY (`Idrh`),
  ADD KEY `FK_h` (`Idh`),
  ADD KEY `FK_u` (`Idu`),
  ADD KEY `Idrh` (`Idrh`);

--
-- Index pour la table `reservation_voiture`
--
ALTER TABLE `reservation_voiture`
  ADD PRIMARY KEY (`Idrvoit`),
  ADD KEY `Idrvoit` (`Idrvoit`),
  ADD KEY `FK_resv` (`Idvoit`),
  ADD KEY `FK_resu` (`Idu`);

--
-- Index pour la table `reservation_voyage`
--
ALTER TABLE `reservation_voyage`
  ADD PRIMARY KEY (`Idrv`),
  ADD KEY `FK_resvoy` (`Idv`),
  ADD KEY `FK_reusr` (`Idu`),
  ADD KEY `Idrv` (`Idrv`),
  ADD KEY `FKPAY` (`Ref_paiement`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Idu`);

--
-- Index pour la table `voiture`
--
ALTER TABLE `voiture`
  ADD PRIMARY KEY (`Idvoit`),
  ADD KEY `Idvoit` (`Idvoit`);

--
-- Index pour la table `voyage`
--
ALTER TABLE `voyage`
  ADD PRIMARY KEY (`Idv`);

--
-- Index pour la table `voyage_organise`
--
ALTER TABLE `voyage_organise`
  ADD PRIMARY KEY (`Idvo`),
  ADD KEY `FK_vo` (`Idv`);

--
-- Index pour la table `voyage_virtuel`
--
ALTER TABLE `voyage_virtuel`
  ADD PRIMARY KEY (`Idvv`),
  ADD KEY `FK_vv` (`Idv`),
  ADD KEY `FK_abb` (`Ida`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `abonnement`
--
ALTER TABLE `abonnement`
  MODIFY `Ida` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `Ide` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `Idh` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation_event`
--
ALTER TABLE `reservation_event`
  MODIFY `Idrev` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation_hotel`
--
ALTER TABLE `reservation_hotel`
  MODIFY `Idrh` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation_voiture`
--
ALTER TABLE `reservation_voiture`
  MODIFY `Idrvoit` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation_voyage`
--
ALTER TABLE `reservation_voyage`
  MODIFY `Idrv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `Idu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT pour la table `voiture`
--
ALTER TABLE `voiture`
  MODIFY `Idvoit` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `voyage`
--
ALTER TABLE `voyage`
  MODIFY `Idv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=370;

--
-- AUTO_INCREMENT pour la table `voyage_organise`
--
ALTER TABLE `voyage_organise`
  MODIFY `Idvo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT pour la table `voyage_virtuel`
--
ALTER TABLE `voyage_virtuel`
  MODIFY `Idvv` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `abonnement`
--
ALTER TABLE `abonnement`
  ADD CONSTRAINT `FK_pai` FOREIGN KEY (`Ref_paiement`) REFERENCES `paiement` (`Ref_paiement`);

--
-- Contraintes pour la table `credit_card`
--
ALTER TABLE `credit_card`
  ADD CONSTRAINT `FKus` FOREIGN KEY (`Idu`) REFERENCES `user` (`Idu`);

--
-- Contraintes pour la table `reservation_event`
--
ALTER TABLE `reservation_event`
  ADD CONSTRAINT `Fk_eve` FOREIGN KEY (`Ide`) REFERENCES `evenement` (`Ide`),
  ADD CONSTRAINT `Fk_usr` FOREIGN KEY (`Idu`) REFERENCES `user` (`Idu`);

--
-- Contraintes pour la table `reservation_hotel`
--
ALTER TABLE `reservation_hotel`
  ADD CONSTRAINT `FK_h` FOREIGN KEY (`Idh`) REFERENCES `hotel` (`Idh`),
  ADD CONSTRAINT `FK_u` FOREIGN KEY (`Idu`) REFERENCES `user` (`Idu`);

--
-- Contraintes pour la table `reservation_voiture`
--
ALTER TABLE `reservation_voiture`
  ADD CONSTRAINT `FK_resu` FOREIGN KEY (`Idu`) REFERENCES `user` (`Idu`),
  ADD CONSTRAINT `FK_resv` FOREIGN KEY (`Idvoit`) REFERENCES `voiture` (`Idvoit`);

--
-- Contraintes pour la table `reservation_voyage`
--
ALTER TABLE `reservation_voyage`
  ADD CONSTRAINT `FK_resvoy` FOREIGN KEY (`Idv`) REFERENCES `voyage` (`Idv`),
  ADD CONSTRAINT `FK_reusr` FOREIGN KEY (`Idu`) REFERENCES `user` (`Idu`);

--
-- Contraintes pour la table `voyage_organise`
--
ALTER TABLE `voyage_organise`
  ADD CONSTRAINT `FK_vo` FOREIGN KEY (`Idv`) REFERENCES `voyage` (`Idv`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `voyage_virtuel`
--
ALTER TABLE `voyage_virtuel`
  ADD CONSTRAINT `FK_abb` FOREIGN KEY (`Ida`) REFERENCES `abonnement` (`Ida`),
  ADD CONSTRAINT `FK_vv` FOREIGN KEY (`Idv`) REFERENCES `voyage` (`Idv`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
