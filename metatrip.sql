-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 21 fév. 2022 à 17:54
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
  `Type` enum('NONPAYE','PAYE','ANNULE','') NOT NULL,
  `Prix_a` int(11) NOT NULL,
  `Date_achat` date NOT NULL,
  `Date_expiration` date NOT NULL,
  `Etat` varchar(20) NOT NULL,
  `Ref_paiement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `chambre`
--

CREATE TABLE `chambre` (
  `idc` int(11) NOT NULL,
  `numc` varchar(20) NOT NULL,
  `type` enum('DISPO','INDISPO','','') NOT NULL,
  `etat` varchar(20) NOT NULL,
  `idh` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `chauffeur`
--

CREATE TABLE `chauffeur` (
  `idch` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `photo` varchar(20) NOT NULL,
  `tel` double NOT NULL,
  `description` varchar(20) NOT NULL,
  `etatDispo` enum('DISPO','INDISPO') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `chauffeur`
--

INSERT INTO `chauffeur` (`idch`, `nom`, `prenom`, `photo`, `tel`, `description`, `etatDispo`) VALUES
(666, 'lam', 'fares', 'fares.png', 99999999, 'flam', 'DISPO');

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

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`Ide`, `Type_event`, `Chanteur`, `Adresse`, `Date_event`, `prix_e`) VALUES
(2, 'hhhuhonl', 'c', '7 rue 2938', '2020-09-01', 12),
(3, 'aaaaaaa', 'c', '7 rue 2938', '2020-09-01', 120);

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

--
-- Déchargement des données de la table `hotel`
--

INSERT INTO `hotel` (`Idh`, `Nom_hotel`, `Nb_etoiles`, `Adresse`) VALUES
(1, 'gulden tulip', 4, 'gammarth'),
(2, '4 seasons', 4, 'gammarth'),
(3, '4 seasons', 4, 'gammarth'),
(4, '4 seasons', 4, 'gammarth'),
(5, '4 seasons', 4, 'gammarth'),
(6, '4 seasons', 4, 'gammarth'),
(7, '4 seasons', 4, 'gammarth'),
(8, '4 seasons', 4, 'gammarth'),
(9, '4 seasons', 4, 'gammarth'),
(10, '4 seasons', 4, 'gammarth'),
(11, '4 seasons', 4, 'gammarth'),
(12, 'gulden tulip', 4, 'gammarth');

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

--
-- Déchargement des données de la table `reservation_event`
--

INSERT INTO `reservation_event` (`Idrev`, `Nb_pers`, `Ide`, `Idu`) VALUES
(6, 5, 2, 813),
(17, 5, 2, 813),
(20, 5, 2, 816),
(21, 5, 2, 817);

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
  `Idu` int(11) NOT NULL,
  `idc` int(11) NOT NULL,
  `Date_depart` date DEFAULT NULL,
  `Date_arrivee` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation_hotel`
--

INSERT INTO `reservation_hotel` (`Idrh`, `Type_room`, `Nb_nuitees`, `Nb_personnes`, `Prix`, `Idu`, `idc`, `Date_depart`, `Date_arrivee`) VALUES
(1, 'single', 2, 1, 2.2, 811, 0, NULL, NULL),
(3, 'single', 2, 1, 2.2, 811, 0, '2020-09-01', '2050-09-01');

-- --------------------------------------------------------

--
-- Structure de la table `reservation_voiture`
--

CREATE TABLE `reservation_voiture` (
  `Idrvoit` int(11) NOT NULL,
  `prix_rent` float NOT NULL,
  `Trajet` varchar(20) NOT NULL,
  `Idu` int(11) NOT NULL,
  `Idvoit` int(11) NOT NULL,
  `idch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation_voiture`
--

INSERT INTO `reservation_voiture` (`Idrvoit`, `prix_rent`, `Trajet`, `Idu`, `Idvoit`, `idch`) VALUES
(10, 5.5, 'jandouba', 41, 55, 666);

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
(9, '2022-02-16', '2022-02-16', 'NonPaye', 26, 18, 0),
(10, '2020-09-01', '2050-09-01', 'Paye', 26, 97, 0),
(11, '2050-09-01', '2050-09-01', 'Paye', 26, 97, 0);

-- --------------------------------------------------------

--
-- Structure de la table `sponsor`
--

CREATE TABLE `sponsor` (
  `ids` int(11) NOT NULL,
  `nomsponsor` varchar(20) NOT NULL,
  `tel` double NOT NULL,
  `email` varchar(20) NOT NULL,
  `date_sp` date NOT NULL,
  `prix_sp` float NOT NULL,
  `ide` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(41, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(42, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(43, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(44, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(45, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(46, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(47, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(48, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(49, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(50, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(51, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(52, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(53, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(54, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(55, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(56, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(57, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(58, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(59, 196525, 'ssss', 'cxx', 2568435, 'fares@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(60, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(61, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(62, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(63, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(64, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(65, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(66, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(67, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(68, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(69, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(70, 199525, 'ssss', 'cxx', 2568435, 'nex@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(71, 199525, 'ssss', 'cxx', 2568435, 'nex@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(72, 199525, 'ssss', 'cxx', 2568435, 'nex@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(73, 199525, 'ssss', 'cxx', 2568435, 'nex@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(74, 199525, 'ssss', 'cxx', 2568435, 'nex@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(75, 199525, 'ssss', 'cxx', 2568435, 'nex@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(76, 199525, 'ssss', 'cxx', 2568435, 'nex@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(77, 199525, 'ssss', 'cxx', 2568435, 'nex@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(78, 199525, 'ssss', 'cxx', 2568435, 'nex@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(811, 199525, 'ssss', 'cxx', 2568435, 'nex@live.fr', '25d55ad283aa400af464c76d713c07ad', 'image', 0, '2011-10-01'),
(812, 195, 'flam', 'fares', 256845, 'flam@live.fr', '0000', 'image', 0, '2010-09-11'),
(813, 195, 'flam', 'fares', 256845, 'flam@live.fr', '0000', 'image', 0, '2010-09-11'),
(814, 5866, 'dafdouf', 'zakzouk', 5895, 'zak@live.fr', '0000', 'image', 0, '2011-10-01'),
(815, 195, 'nex', 'nex', 256845, 'nex@live.fr', 'aaaa', 'image', 0, '2010-09-11'),
(816, 195, 'nex', 'nex', 256845, 'nex@live.fr', 'aaaa', 'image', 0, '2010-09-11'),
(817, 195, 'nex', 'nex', 256845, 'nex@live.fr', 'aaaa', 'image', 0, '2010-09-11');

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

CREATE TABLE `voiture` (
  `Idvoit` int(11) NOT NULL,
  `Matricule` varchar(50) NOT NULL,
  `Puissance_fiscalle` int(11) NOT NULL,
  `Image_v` varchar(50) NOT NULL,
  `Modele` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `voiture`
--

INSERT INTO `voiture` (`Idvoit`, `Matricule`, `Puissance_fiscalle`, `Image_v`, `Modele`, `type`) VALUES
(1, '120TU120', 12, 'image', 'Mercedes', ''),
(2, '120TU120', 12, 'image', 'Mercedes', ''),
(3, '220TU120', 12, 'image', 'bmw', ''),
(55, '220TU120', 12, 'image', 'Mercedes', ''),
(663, '220TU120', 12, 'image', 'bmw', ''),
(669, '220TU120', 12, 'image', 'bmw', ''),
(2000, '220TU120', 12, 'image', 'bmw', ''),
(2001, '220TU120', 12, 'image', 'bmw', ''),
(6390, '220TU120', 12, 'image', 'bmw', ''),
(6600, '220TU120', 12, 'image', 'bmw', ''),
(6690, '220TU120', 12, 'image', 'bmw', ''),
(6890, '220TU120', 12, 'image', 'bmw', '');

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
(18, 'espagne', 'c://espagne.png'),
(97, 'istanbul', 'c://antalya.png'),
(98, 'tounis', 'c://maroc.png'),
(99, 'tounis', 'c://maroc.png'),
(100, 'tounis', 'c://maroc.png'),
(169, 'espagne', 'c://espagne.png'),
(198, 'espagne', 'c://espagne.png'),
(199, 'espagne', 'c://espagne.png'),
(369, 'tounis', 'c://maroc.png'),
(399, 'tounis', 'c://berlin.png'),
(499, 'tounis', 'c://berlin.png'),
(501, 'tounis', 'c://berlin.png'),
(509, 'tounis', 'c://berlin.png'),
(599, 'tounis', 'c://berlin.png'),
(600, 'allemagne', 'c://berlin.png'),
(601, 'gafsa', 'c://beja.png'),
(602, 'gafsa', 'c://beja.png'),
(603, 'gafsa', 'c://beja.png'),
(604, 'gafsa', 'c://beja.png'),
(605, 'gafsa', 'c://beja.png'),
(606, 'gafsa', 'c://beja.png');

-- --------------------------------------------------------

--
-- Structure de la table `voyage_organise`
--

CREATE TABLE `voyage_organise` (
  `Idvo` int(11) NOT NULL,
  `Prix_billet` float NOT NULL,
  `Airline` varchar(20) NOT NULL,
  `Nb_nuitees` int(11) NOT NULL,
  `etatVoyage` enum('DISPO','INDISPO') NOT NULL,
  `Idv` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `voyage_organise`
--

INSERT INTO `voyage_organise` (`Idvo`, `Prix_billet`, `Airline`, `Nb_nuitees`, `etatVoyage`, `Idv`) VALUES
(78, 170.6, 'nex', 3, 'DISPO', 18),
(79, 990.6, 'sounay', 3, '', 369),
(80, 170.6, 'nex', 3, '', 369),
(81, 10.6, 'flam', 3, '', 369),
(82, 170.6, 'nex', 3, '', 600),
(84, 170.6, 'nex', 3, 'INDISPO', 605),
(85, 170.6, 'nexdd', 3, 'DISPO', 97),
(86, 170.6, 'nexdd', 3, 'INDISPO', 605),
(87, 170.6, 'nexdd', 3, 'INDISPO', 605);

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
-- Index pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD PRIMARY KEY (`idc`),
  ADD KEY `chambre_ibfk_1` (`idh`),
  ADD KEY `idc` (`idc`);

--
-- Index pour la table `chauffeur`
--
ALTER TABLE `chauffeur`
  ADD PRIMARY KEY (`idch`),
  ADD KEY `idch` (`idch`);

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
  ADD KEY `Idrh` (`Idrh`),
  ADD KEY `FK_u` (`Idu`),
  ADD KEY `fk_chh` (`idc`);

--
-- Index pour la table `reservation_voiture`
--
ALTER TABLE `reservation_voiture`
  ADD PRIMARY KEY (`Idrvoit`),
  ADD KEY `Idrvoit` (`Idrvoit`),
  ADD KEY `FK_resu` (`Idu`),
  ADD KEY `FK_resv` (`Idvoit`),
  ADD KEY `FK_CHAUFF` (`idch`);

--
-- Index pour la table `reservation_voyage`
--
ALTER TABLE `reservation_voyage`
  ADD PRIMARY KEY (`Idrv`),
  ADD KEY `FK_reusr` (`Idu`),
  ADD KEY `Idrv` (`Idrv`),
  ADD KEY `FKPAY` (`Ref_paiement`),
  ADD KEY `FK_resvoy` (`Idv`);

--
-- Index pour la table `sponsor`
--
ALTER TABLE `sponsor`
  ADD PRIMARY KEY (`ids`),
  ADD KEY `sponsor_ibfk_1` (`ide`);

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
  ADD KEY `FK_abb` (`Ida`),
  ADD KEY `FK_vv` (`Idv`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `abonnement`
--
ALTER TABLE `abonnement`
  MODIFY `Ida` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `chambre`
--
ALTER TABLE `chambre`
  MODIFY `idc` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `chauffeur`
--
ALTER TABLE `chauffeur`
  MODIFY `idch` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=667;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `Ide` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `Idh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `reservation_event`
--
ALTER TABLE `reservation_event`
  MODIFY `Idrev` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `reservation_hotel`
--
ALTER TABLE `reservation_hotel`
  MODIFY `Idrh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `reservation_voiture`
--
ALTER TABLE `reservation_voiture`
  MODIFY `Idrvoit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pour la table `reservation_voyage`
--
ALTER TABLE `reservation_voyage`
  MODIFY `Idrv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `sponsor`
--
ALTER TABLE `sponsor`
  MODIFY `ids` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `Idu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=818;

--
-- AUTO_INCREMENT pour la table `voiture`
--
ALTER TABLE `voiture`
  MODIFY `Idvoit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6891;

--
-- AUTO_INCREMENT pour la table `voyage`
--
ALTER TABLE `voyage`
  MODIFY `Idv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55556;

--
-- AUTO_INCREMENT pour la table `voyage_organise`
--
ALTER TABLE `voyage_organise`
  MODIFY `Idvo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

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
-- Contraintes pour la table `reservation_event`
--
ALTER TABLE `reservation_event`
  ADD CONSTRAINT `Fk_eve` FOREIGN KEY (`Ide`) REFERENCES `evenement` (`Ide`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Fk_usr` FOREIGN KEY (`Idu`) REFERENCES `user` (`Idu`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservation_hotel`
--
ALTER TABLE `reservation_hotel`
  ADD CONSTRAINT `FK_u` FOREIGN KEY (`Idu`) REFERENCES `user` (`Idu`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservation_voiture`
--
ALTER TABLE `reservation_voiture`
  ADD CONSTRAINT `FK_CHAUFF` FOREIGN KEY (`idch`) REFERENCES `chauffeur` (`idch`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_resu` FOREIGN KEY (`Idu`) REFERENCES `user` (`Idu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_resv` FOREIGN KEY (`Idvoit`) REFERENCES `voiture` (`Idvoit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reservation_voyage`
--
ALTER TABLE `reservation_voyage`
  ADD CONSTRAINT `FK_resvoy` FOREIGN KEY (`Idv`) REFERENCES `voyage` (`Idv`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `sponsor`
--
ALTER TABLE `sponsor`
  ADD CONSTRAINT `sponsor_ibfk_1` FOREIGN KEY (`ide`) REFERENCES `evenement` (`Ide`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `voyage_organise`
--
ALTER TABLE `voyage_organise`
  ADD CONSTRAINT `FK_vo` FOREIGN KEY (`Idv`) REFERENCES `voyage` (`Idv`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `voyage_virtuel`
--
ALTER TABLE `voyage_virtuel`
  ADD CONSTRAINT `FK_abb` FOREIGN KEY (`Ida`) REFERENCES `abonnement` (`Ida`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_vv` FOREIGN KEY (`Idv`) REFERENCES `voyage` (`Idv`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
