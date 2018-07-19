-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Lun 21 Mai 2018 à 22:00
-- Version du serveur :  10.1.21-MariaDB
-- Version de PHP :  5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `vva2`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

CREATE TABLE `activite` (
  `idAnimation` int(11) NOT NULL,
  `libelleJour` char(8) NOT NULL,
  `heureDeb` time NOT NULL,
  `heureFin` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Contenu de la table `activite`
--

INSERT INTO `activite` (`idAnimation`, `libelleJour`, `heureDeb`, `heureFin`) VALUES
(170, 'Mercredi', '07:00:00', '08:00:00'),
(170, 'Dimanche', '08:00:00', '09:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `animation`
--

CREATE TABLE `animation` (
  `idAnim` int(11) NOT NULL,
  `idCompte` int(11) NOT NULL,
  `idTypeAnim` int(11) DEFAULT NULL,
  `libAnim` char(30) NOT NULL,
  `descriptionAnim` text NOT NULL,
  `nombreInscrit` int(100) DEFAULT NULL,
  `limiteInscrit` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `animation`
--

INSERT INTO `animation` (`idAnim`, `idCompte`, `idTypeAnim`, `libAnim`, `descriptionAnim`, `nombreInscrit`, `limiteInscrit`) VALUES
(170, 1, 4, 'Randonnée Hebdo', 'Belle randonnée sportive avec du dénivelé, par des sentiers et sentes, à l\'écart des grosses allées forestières. Bon parcours d\'entrainement.', NULL, 30),
(174, 1, 3, 'Fête du village', 'Venez fêter l\'inauguration du village ! ', NULL, 30),
(175, 1, 4, 'Passage du Tour De France', 'Comme chaque année, une étape du Tour De France passe par les Alpes, nous vous invitons à assiter à l\'étape ensemble.', NULL, 30);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `idCompte` int(11) NOT NULL,
  `login` char(30) NOT NULL,
  `nomCompte` char(15) NOT NULL,
  `prenomCompte` char(15) NOT NULL,
  `typeCompte` int(15) NOT NULL,
  `mdp` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`idCompte`, `login`, `nomCompte`, `prenomCompte`, `typeCompte`, `mdp`) VALUES
(1, 'nomTest.prenomTest', 'nomTest', 'prenomTest', 1, 'mdpTest');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `idAnim` int(11) NOT NULL,
  `date` date NOT NULL,
  `heureDeb` time NOT NULL,
  `heureFin` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `evenement`
--

INSERT INTO `evenement` (`idAnim`, `date`, `heureDeb`, `heureFin`) VALUES
(174, '2018-07-07', '20:00:00', '22:30:00'),
(175, '2018-07-31', '12:00:00', '14:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `idInscription` int(11) NOT NULL,
  `idCompte` int(11) NOT NULL,
  `idAnimation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `typeanimation`
--

CREATE TABLE `typeanimation` (
  `idTypeAnim` int(11) NOT NULL,
  `libTypAnim` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `typeanimation`
--

INSERT INTO `typeanimation` (`idTypeAnim`, `libTypAnim`) VALUES
(3, 'Musical'),
(4, 'Sport');

-- --------------------------------------------------------

--
-- Structure de la table `typecompte`
--

CREATE TABLE `typecompte` (
  `idType` int(11) NOT NULL,
  `libelleType` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `typecompte`
--

INSERT INTO `typecompte` (`idType`, `libelleType`) VALUES
(1, 'gestionnaire'),
(2, 'vacancier');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `animation`
--
ALTER TABLE `animation`
  ADD PRIMARY KEY (`idAnim`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`idCompte`);

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`idInscription`);

--
-- Index pour la table `typeanimation`
--
ALTER TABLE `typeanimation`
  ADD PRIMARY KEY (`idTypeAnim`);

--
-- Index pour la table `typecompte`
--
ALTER TABLE `typecompte`
  ADD PRIMARY KEY (`idType`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `animation`
--
ALTER TABLE `animation`
  MODIFY `idAnim` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=176;
--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `idCompte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `inscription`
--
ALTER TABLE `inscription`
  MODIFY `idInscription` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `typeanimation`
--
ALTER TABLE `typeanimation`
  MODIFY `idTypeAnim` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `typecompte`
--
ALTER TABLE `typecompte`
  MODIFY `idType` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
