# 🌍💳 Système de Gestion des Comptes Bancaires

## 📌 À propos du projet
Ce projet est un **Système de Gestion des Comptes Bancaires** développé en **Java 8** sous forme d’une **application console**.  
Il simule les principales opérations bancaires réelles tout en respectant les **principes de l’architecture propre (Clean Architecture)**.

Le système inclut :
- Une conception orientée objet (POO)
- Une séparation claire des responsabilités entre les couches
- Une base de données pour la persistance des données
- Des opérations bancaires pratiques (dépôt, retrait, transfert, gestion des comptes)

---

## 🚀 Fonctionnalités
- 🔑 **Authentification des utilisateurs** – Inscription et connexion sécurisée.
- 🏦 **Gestion des comptes** – Création et gestion de plusieurs types de comptes (courant, épargne, etc.).
- 💵 **Dépôts et retraits** – Réaliser les opérations bancaires de base.
- 🔄 **Transferts** – Envoyer de l’argent entre différents comptes.
- 📜 **Historique des transactions** – Suivi de toutes les opérations avec horodatage.
- 🗄️ **Intégration Base de Données** – Persistance des utilisateurs, comptes et opérations.
- 🌐 **Documentation multilingue** – Disponible en anglais, français et arabe.

---

## 🎯 Pourquoi ce projet ?
La banque est l’un des exemples les plus concrets pour comprendre comment **les données et la logique interagissent**.  
Au lieu d’exemples abstraits, ce projet se base sur des concepts connus de tous :  
**dépôt, retrait, solde et transferts** — ce qui rend l’apprentissage de **Java, la POO et l’architecture logicielle** plus concret et accessible.

---

## 🛠️ Technologies utilisées
- **Java 8** – Langage principal
- **JDBC** – Connexion avec la base de données
- **PostgreSQL / MySQL** – Base de données relationnelle
- **Interface Console** – Légère et portable
- **Architecture Propre** – Séparation des préoccupations pour une meilleure maintenabilité

---

## 📂 Structure du projet
- **abstracts/** → Classes abstraites (`Account`, `Transfer`)
- **models/** → Modèles métiers (`CheckingAccount`, `SavingAccount`, `Deposit`, `Withdrawal`, etc.)
- **controllers/** → Logique métier (`AccountController`, `TransferController`)
- **utils/** → Classes utilitaires (`DatabaseConnection`, validateurs, etc.)
- **Main.java** → Point d’entrée avec menus et interaction utilisateur

---

## 🔑 Cas d’utilisation
1. Un utilisateur s’inscrit et crée un compte bancaire.
2. Il effectue un dépôt d’argent sur son compte.
3. Il retire des fonds (après vérification du solde).
4. Il transfère de l’argent vers un autre compte.
5. Il consulte l’historique complet de ses transactions.
6. Toutes les données sont sauvegardées et récupérées depuis la base de données.

---

## 🏦 Améliorations possibles
- 📈 Calcul des intérêts pour les comptes épargne
- 👨‍💼 Tableau de bord administrateur pour gérer utilisateurs et comptes
- 💻 Migration vers une interface graphique ou une version web

---

## 📜 Licence
Ce projet est sous licence **MIT**.  
Vous êtes libre de l’utiliser, le modifier et le distribuer.

---

<p align="center">
  💳 <strong>La banque en version console — gérez vos comptes, suivez vos opérations et explorez une architecture propre avec base de données.</strong>
</p>
