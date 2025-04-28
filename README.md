# Simulation d'un Écosystème Numérique

Ce projet consiste en une simulation d'écosystème dynamique et interactif, modélisant les interactions biologiques et écologiques entre des espèces vivantes et leur environnement. Développé en Java, le projet intègre des principes avancés de programmation orientée objet et offre une visualisation intuitive grâce à JavaFX.

---

## Table des Matières :
1. [Introduction](#introduction)
2. [Fonctionnalités Principales](#fonctionnalités-principales)
3. [Technologies Utilisées](#technologies-utilisées)
4. [Architecture](#architecture)
5. [Installation et Démarrage](#installation-et-démarrage)
6. [Guide d'Utilisation](#guide-dutilisation)
7. [Tests et Qualité](#tests-et-qualité)
8. [Auteurs et Contributions](#auteurs-et-contributions)
9. [Licence](#licence)

--- 

## Introduction

Ce projet vise à modéliser un écosystème comprenant des espèces vivantes et des ressources, tout en simulant des interactions dynamiques sous l’influence de facteurs environnementaux tels que les saisons. L'objectif est de permettre une meilleure compréhension des dynamiques écologiques et d’offrir un outil pédagogique interactif.

Les utilisateurs peuvent interagir avec la simulation à travers :
- Une interface graphique intuitive.
- Une interface en ligne de commande pour le débogage et les tests.

---

## Fonctionnalités Principales

### Simulation Dynamique
- Gestion des cycles saisonniers (printemps, été, automne, hiver).
- Adaptation des espèces aux changements climatiques.
- Évolution des ressources naturelles (herbe, algues).

### Modélisation des Espèces Vivantes
- **Espèces terrestres :** Ours, loups, humains.
- **Espèces aquatiques :** Requins, poissons.
- **Espèces volantes :** Canaris, aigles.
- Les espèces disposent de comportements spécifiques : déplacement, reproduction, détection des prédateurs, sommeil.

### Interfaces Utilisateurs
- **Interface graphique (JavaFX) :** Visualisation en temps réel des interactions.
- **Interface ligne de commande :** Tests et personnalisation des scénarios.

---

## Technologies Utilisées

1. **Langages et Outils :**
   - Java avec Maven pour la gestion des dépendances.
   - IntelliJ IDEA comme environnement de développement.
2. **Frameworks :**
   - JavaFX pour l’interface graphique.
3. **Qualité Logicielle :**
   - SonarQube pour l’analyse statique du code.
   - Javadoc pour la documentation technique.
4. **Gestion Collaborative :**
   - GitHub pour la gestion du code source et des versions.

---

## Architecture

Le projet est organisé en plusieurs packages :
- **Ecosystem :** Gestion des environnements (forêt, océan, air).
- **Species :** Gestion des espèces vivantes et de leurs comportements.
- **Resources :** Modélisation des ressources naturelles (herbe, algues).
- **Interfaces :** Définition des comportements génériques (déplacement, consommation, reproduction).

### Diagrammes
- **Diagramme UML :** Représentation des relations entre classes.
- **Diagramme de Gantt :** Visualisation de la planification temporelle.

---

## Installation et Démarrage

### Prérequis
Assurez-vous d'avoir les éléments suivants installés sur votre machine :
- **Java JDK** (version 8 ou supérieure)
- **Maven** pour la gestion des dépendances
- **IntelliJ IDEA** ou un autre IDE compatible
- **Git** (pour cloner le dépôt)

### Étapes d'Installation

1. **Cloner le dépôt**
   Ouvrez un terminal et exécutez la commande suivante pour cloner le dépôt Git :
   ```bash
   git clone https://github.com/shakib-svg/Ecosyteme_java.git
## Installation et Démarrage

### Importer le projet
- Ouvrez le projet dans IntelliJ IDEA.
- Configurez Maven pour télécharger les dépendances automatiquement.

### Exécuter le projet
- Naviguez vers le fichier `Main.java`.
- Cliquez sur `Run` pour lancer la simulation.

---

## Guide d'Utilisation

### Interface Graphique
- Visualisez les interactions en temps réel.
- Modifiez les paramètres environnementaux (ressources, cycles saisonniers).

### Interface Ligne de Commande
- Testez des scénarios personnalisés en ajustant les paramètres directement dans le code source.
- Observez les impacts des interactions biologiques et écologiques.

---

## Tests et Qualité

### Tests Unitaires
- Couverture de 90 % des fonctionnalités avec JUnit.

### Qualité du Code
- Respect des principes SOLID et DRY.
- Analyse de qualité avec SonarQube pour détecter et corriger les erreurs.

### Optimisations
- Réduction des redondances et amélioration de la performance.

---

## Auteurs et Contributions

### Équipe
- **Shakib Youssef**
- **Safia Khalil**
- **Ali Mantach**
- **Soulayman Chedid**

### Contributions
- **Ali Mantach,Shakib Youssef:** Modélisation des espèces et interactions.
- **Safia Khalil :** Développement de l’interface graphique.
- **Shakib Youssef ,Soulayman Chedid :** Gestion des environnements et des ressources.

---
